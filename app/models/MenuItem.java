package models;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

//import java.util.UUID;

@Entity("menuitems")
public class MenuItem {
    @Id
    private String id;
    private String name;
    private float price;
    private int qty;
    private String restaurant;
    private String image;
    private String description;

    public MenuItem(){}

    public MenuItem(String id, String name, int price, int qty, String restaurant, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.restaurant = restaurant;
        this.image = image;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //public String generateId() {
      //  return UUID.randomUUID().toString().replaceAll("-", "");
    //}
}
/*

      {
          Id: 2,
          name: "pizza",
          price: 16.95,
          qty:1,
          restaurant: 'Domino',
          image: "img/2.jpg",
          //image: "http://via.placeholder.com/400x300",
          description: "????"
      },
 */