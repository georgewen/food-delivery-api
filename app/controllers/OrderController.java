package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import models.MenuItem;
import models.Order;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.OrderService;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderController extends Controller {

    @Inject
    private OrderService orderService;

    public Result index() {
        return ok(jsonResponse("data", orderService.getAll()));
    }

    public Result getMyOrders(String username) {
        return ok(jsonResponse("data", orderService.getMyOrders("username",username)));
    }

    public Result getOrder(String id) {
        try {
            return ok(jsonResponse("data", orderService.get(id)));
        } catch (NoSuchElementException e) {
            return notFound(jsonResponse("message", "item doesn not exists"));
        } catch (Exception e) {
            return badRequest(jsonResponse("message", "error: " + e.getMessage()));
        }
    }

    public Result createOrder(Http.Request request) {
        try {
            Optional<Order> item = request.body().parseJson(Order.class);
            orderService.save(item.orElseThrow(IllegalArgumentException::new));
            return created(jsonResponse("data", item));
        } catch	(IllegalArgumentException e) {
            return badRequest(jsonResponse("message", "expecting JSON data"));
        } catch (Exception e) {
            return badRequest(jsonResponse("message", "error: " + e.getMessage()));
        }
    }

    public Result modifyOrder(Http.Request request, String id) {
        String name = "";
        try {
            JsonNode json = request.body().asJson();
            name = json.toString(); //json.findPath("username").textValue();
            Optional<Order> item = request.body().parseJson(Order.class);
            orderService.update(id,item.orElseThrow(IllegalArgumentException::new));
            return ok(jsonResponse("message", "the item is modified"));
        } catch	(NoSuchElementException e) {
            return badRequest(jsonResponse("message", "the item does not exist"));
        } catch	(IllegalArgumentException e) {
            return badRequest(jsonResponse("message", "Expecting JSON data" + name ));
        } catch (NoSuchMethodException e) {
            return badRequest(jsonResponse("message", "THe key does not exist" + name));
        } catch (Exception e) {
            return badRequest(jsonResponse("message", "ERROR: " + e.getMessage()));
        }
    }

    public Result deleteOrder(String id) {
        try {
            orderService.delete(id);
            return ok(jsonResponse("message", "the item is deleted"));
        } catch(NoSuchElementException e) {
            return badRequest(jsonResponse("message", "the Item does not exist"));
        }
    }

    private ObjectNode jsonResponse(String key, Object obj) {
        ObjectNode objectNode = Json.newObject();
        objectNode.set(key, Json.toJson(obj));
        return objectNode;
    }    
    

}
