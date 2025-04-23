package alebarre.picpay.DTO;

import alebarre.picpay.entity.Wallet;
import alebarre.picpay.entity.WalletType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record CreateWalletDTO(@NotBlank(message = "Name cannot be empty") String fullName,
                              @NotBlank(message = "CPF / CNPJ cannot be empty") String cpfCnpj,
                              @NotBlank(message = "E-mail cannot be empty") String email,
                              @NotBlank(message = "Password cannot be empty") String password,
                              @NotNull WalletType.Enum walletType){

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
