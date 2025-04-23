package alebarre.picpay.client;

import alebarre.picpay.DTO.AuthorizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "AuthorizatinClient",
        url = "${client.authorization-service.url}"
)
public interface AuthorizatinClient {

    @GetMapping
    ResponseEntity<AuthorizationResponseDTO> isAutorized();

}
