package alebarre.picpay.service;

import alebarre.picpay.DTO.CreateWalletDTO;
import alebarre.picpay.entity.Wallet;
import alebarre.picpay.exception.WalletDataAlreadyExistsException;
import alebarre.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDTO dto) {
        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.cpfCnpj());
        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }
        return walletRepository.save(dto.toWallet());
    }
}
