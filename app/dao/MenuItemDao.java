package dao;

import dev.morphia.query.Query;
import models.MenuItem;
import org.bson.types.ObjectId;
import services.MongoService;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;

public class MenuItemDao  implements  Dao<MenuItem> {

    @Override
    public Collection<MenuItem> getAll() {
        final Query<MenuItem> query = MongoService.datastore().createQuery(MenuItem.class);
        return query.asList();
    }

    @Override
    public Collection<MenuItem> search(String fieldName, String value) {
        return null;
    }

    @Override
    public Optional<MenuItem> get(String id) {
        return Optional.ofNullable(MongoService.datastore().createQuery(MenuItem.class).field("_id").equal(new ObjectId(id)).first());
    }

    @Override
    public void save(MenuItem menuItem) {

    }

    @Override
    public void update(String id, MenuItem menuItem) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    }

    @Override
    public void delete(String id) {

    }
}
