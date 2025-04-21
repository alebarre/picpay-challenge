package alebarre.picpay.repository;

import alebarre.picpay.DTO.CreateWalletDTO;
import alebarre.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
