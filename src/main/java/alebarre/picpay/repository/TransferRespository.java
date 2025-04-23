package alebarre.picpay.repository;

import alebarre.picpay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRespository extends JpaRepository<Transfer, UUID> {
}
