package controllers;
import java.awt.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.fasterxml.jackson.databind.node.ObjectNode;

import com.google.inject.Inject;
import models.*;
import models.MenuItem;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.MenuItemService;

public class MenuItemController extends Controller {

    @Inject
    private MenuItemService menuItemService;
    // api/menuitems
    public Result index() {
        //ArrayList<MenuItem> items = MenuItemList.getInstance().getMenuItemList();
        //return ok(jsonResponse("data", items));
        return ok(jsonResponse("data", menuItemService.getAll()));
    }

    public Result getMenuItem(String id) {
        try {
            return ok(jsonResponse("data", menuItemService.get(id)));
        } catch (NoSuchElementException e) {
            return notFound(jsonResponse("message", "item doesn not exists"));
        } catch (Exception e) {
            return badRequest(jsonResponse("message", "error: " + e.getMessage()));
        }
    }

    public Result createMenuItem(Http.Request request) {
        try {
            Optional<MenuItem> item = request.body().parseJson(MenuItem.class);
            menuItemService.save(item.orElseThrow(IllegalArgumentException::new));
            return created(jsonResponse("data", item));
        } catch	(IllegalArgumentException e) {
            return badRequest(jsonResponse("message", "expecting JSON data"));
        } catch (Exception e) {
            return badRequest(jsonResponse("message", "error: " + e.getMessage()));
        }
    }

    public Result modifyMenuItem(Http.Request request, String id) {
        try {
            Optional<MenuItem> expense = request.body().parseJson(MenuItem.class);
            menuItemService.update(id,expense.orElseThrow(IllegalArgumentException::new));
            return ok(jsonResponse("message", "the item is modified"));
        } catch	(NoSuchElementException e) {
            return badRequest(jsonResponse("message", "the item does not exist"));
        } catch	(IllegalArgumentException e) {
            return badRequest(jsonResponse("message", "Expecting JSON data"));
        } catch (NoSuchMethodException e) {
            return badRequest(jsonResponse("message", "THe key does not exist"));
        } catch (Exception e) {
            return badRequest(jsonResponse("message", "ERROR: " + e.getMessage()));
        }
    }

    public Result deleteMenuItem(String id) {
        try {
            menuItemService.delete(id);
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
