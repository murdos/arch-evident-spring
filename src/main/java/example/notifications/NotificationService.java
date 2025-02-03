package example.notifications;

import example.order.OrderCompleted;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class NotificationService {

    @Async
    @TransactionalEventListener
    public void sendPendingShipmentMail(OrderCompleted order) {
        // send email to customer
    }
}
