package dao;

import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import models.MenuItem;
import models.Order;
import org.bson.types.ObjectId;
import scala.collection.View;
import services.MongoService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;

public class OrderDao implements  Dao<Order> {

    @Override
    public Collection<Order> getAll() {
        final Query<Order> query = MongoService.datastore().createQuery(Order.class);
        return query.asList();
    }

    @Override
    public Optional<Order> get(String id) {
        return Optional.ofNullable(MongoService.datastore().createQuery(Order.class).field("_id").equal(new ObjectId(id)).first());
    }

    public Collection<Order> search(String fieldName, String value) {
        return Optional.ofNullable(MongoService.datastore().createQuery(Order.class).field(fieldName).equal(value)).get().asList();
    }

    @Override
    public void save(Order order) {
        MongoService.datastore().save(order);
    }

    @Override
    public void update(String id, Order order) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Query<Order> modifyQuery = MongoService.datastore().createQuery(Order.class)
                .field("_id").equal(new ObjectId(id));
        UpdateOperations<Order> updateOperations = MongoService.datastore().createUpdateOperations(Order.class);
        for(Field f : Order.class.getDeclaredFields()) {
            String fieldName = f.getName();
            if(fieldName.equals("id")) continue;
            Method m = Order.class.getMethod("get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1));
            //check if value is null
            if(m.invoke(order) == null) continue;

            updateOperations.set(fieldName,m.invoke(order));
        }
        MongoService.datastore().update(modifyQuery, updateOperations);
    }

    @Override
    public void delete(String id) {
        Query<Order> deleteQuery = MongoService.datastore().createQuery(Order.class)
                .field("_id").equal(new ObjectId(id));
        MongoService.datastore().delete(deleteQuery);
    }
}
