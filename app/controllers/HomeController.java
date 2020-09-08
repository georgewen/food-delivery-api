package controllers;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /*
    private  void initDatastore() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.baeldung.morphia");  //this line breaks
        Datastore datastore = morphia.createDatastore(new MongoClient(), "library");
        datastore.ensureIndexes();  //this line breaks too
    } */

    public Result index() {
        return ok(views.html.index.render());
    }

}
