package alebarre.picpay.DTO;

import alebarre.picpay.entity.Wallet;
import alebarre.picpay.entity.WalletType;

public record CreateWalletDTO(String fullName,
                              String cpfCnpj,
                              String email,
                              String password,
                              WalletType.Enum walletType){

    public Wallet toWallet(){
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }

}
