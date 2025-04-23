package alebarre.picpay.service;

import alebarre.picpay.DTO.TransferDTO;
import alebarre.picpay.entity.Transfer;
import alebarre.picpay.entity.Wallet;
import alebarre.picpay.exception.InsuficientBalanceException;
import alebarre.picpay.exception.TransferNotAllowedForWalletTypeException;
import alebarre.picpay.exception.TransferNotAuthorizedException;
import alebarre.picpay.exception.WalletNotFoundException;
import alebarre.picpay.repository.TransferRespository;
import alebarre.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRespository transferRespository;
    private final AuthorizationService authorizationService;
    private final NotificatinService notificatinService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRespository transferRespository, AuthorizationService authorizationService, NotificatinService notificatinService, WalletRepository walletRepository) {
        this.transferRespository = transferRespository;
        this.authorizationService = authorizationService;
        this.notificatinService = notificatinService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {

        var sender = walletRepository.findById(transferDTO.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

        var receiver = walletRepository.findById(transferDTO.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payee()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        var tranfer = new Transfer(sender, receiver, transferDTO.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRespository.save(tranfer);

        CompletableFuture.runAsync(() -> notificatinService.sendNotification(transferResult));

        return transferResult;

    }

    private void validateTransfer(@Valid TransferDTO transferDTO, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreatherThan(transferDTO.value())){
            throw new InsuficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDTO)){
            throw new TransferNotAuthorizedException();
        }

    }
}
