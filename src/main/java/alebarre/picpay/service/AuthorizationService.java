package alebarre.picpay.service;

import alebarre.picpay.DTO.TransferDTO;
import alebarre.picpay.client.AuthorizatinClient;
import alebarre.picpay.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizatinClient authorizatinClient;

    public AuthorizationService(AuthorizatinClient authorizatinClient) {
        this.authorizatinClient = authorizatinClient;
    }

    public boolean isAuthorized(TransferDTO transfer) {
        var resp = authorizatinClient.isAutorized();
        if (resp.getStatusCode().isError()) {
            throw new PicPayException();
        }
        return resp.getBody().authorized();
    }

}
