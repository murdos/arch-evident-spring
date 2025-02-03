package example.notifications;

import example.order.Order;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendPendingShipmentMail(Order order) {
        // send email to customer
    }
}
