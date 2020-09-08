package services;

import com.google.inject.Inject;
import dao.Dao;
import dao.MenuItemDao;
import models.MenuItem;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;

public class MenuItemService {
    private final Dao<MenuItem> menuItemDao;

    @Inject
    public MenuItemService(MenuItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }
    public List<MenuItem> getAll() {
        return (List<MenuItem>) menuItemDao.getAll();
    }
    public MenuItem get(String id) {
        return menuItemDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    public void save(MenuItem menuItem) {
        menuItemDao.save(menuItem);
    }

    public void update(String id, MenuItem menuItem) throws  NoSuchElementException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        menuItemDao.update(id,menuItem);
    }

    public void delete(String id) { menuItemDao.delete(id);}
}
