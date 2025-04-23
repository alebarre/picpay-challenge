package alebarre.picpay.service;

import alebarre.picpay.client.NotificationClient;
import alebarre.picpay.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificatinService {

    private final NotificationClient notificationClient;

    private static final Logger logger = LoggerFactory.getLogger(NotificatinService.class);

    public NotificatinService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer) {

        try {
            logger.info("Sending notification...");
            var response = notificationClient.sendNotification(transfer);
            if(response.getStatusCode().isError()){
                logger.error("Error while sending notification. Status code is not OK.");
            }
            notificationClient.sendNotification(transfer);
        }catch (Exception e){
            logger.error("Error while sending notification",e);
        }
    }

}
