package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import dao.OrderDao;
import models.Order;
import play.libs.Json;
import services.OrderService;

import java.util.NoSuchElementException;

public class WebSocketActor extends AbstractActor {

    //@Inject
    //private OrderService orderService;

    public static Props props(ActorRef out) {
        return Props.create(WebSocketActor.class, out);
    }

    private final ActorRef out;

    public WebSocketActor(ActorRef out) {
        this.out = out;
    }


    @Override
    public Receive createReceive() {
        OrderDao orderDao = new OrderDao();
        return receiveBuilder()
                .match(String.class, (message) -> {
                            //lookup message
                    String output = "";
                    if(!message.equals("--heartbeat--")) {
                        try {
                            Order order = orderDao.get(message).orElseThrow(NoSuchElementException::new);
                            output = "order status: " + order.getStatus(); //jsonResponse("order", order).toString();
                        } catch (Exception e) {
                            output = jsonResponse("error", e.getMessage()).toString();
                        }
                    } else {
                        output = "--heartbeat--";
                    }
                    out.tell( output, self());
                }
                )
                .build();
    }

    private ObjectNode jsonResponse(String key, Object obj) {
        ObjectNode objectNode = Json.newObject();
        objectNode.set(key, Json.toJson(obj));
        return objectNode;
    }

}
