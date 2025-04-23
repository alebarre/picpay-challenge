package alebarre.picpay.client;

import alebarre.picpay.entity.Transfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "NotificationClient",
        url = "${https://run.mocky.io/v3/84a53a8c-6e55-4c1c-8272-206cd40103e8}"
)
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotification(@RequestBody Transfer transfer);

}
