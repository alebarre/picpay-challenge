package alebarre.picpay.controller;

import alebarre.picpay.DTO.TransferDTO;
import alebarre.picpay.entity.Transfer;
import alebarre.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO dto){
        var transf = transferService.transfer(dto);
        return ResponseEntity.ok(transf);
    }

}
