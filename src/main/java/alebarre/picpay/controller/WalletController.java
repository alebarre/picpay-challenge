package alebarre.picpay.controller;

import alebarre.picpay.DTO.CreateWalletDTO;
import alebarre.picpay.entity.Wallet;
import alebarre.picpay.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletDTO dto) {
        var wallet = walletService.createWallet(dto);
        return ResponseEntity.ok(wallet);
    }

}
