package controllers;
import akka.actor.Cancellable;
import akka.stream.javadsl.Source;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.EventSource;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

public class NotificationController  extends Controller {

    private EventSource.Event event(String data) {

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
        int random_number = ThreadLocalRandom.current().nextInt(1,3);
        String name;

        switch (random_number) {
            case 1:
                name = "promotion";
                break;
            case 2:
                name = "event";
                break;
            case 3:
                name = "alert";
                break;
            default:
                name = "unknown";
                break;
        }

        ObjectNode result = Json.newObject();
        result.put("time", df.format(ZonedDateTime.now()));
        result.put("data", data);

        return new EventSource.Event(
                result.toString(),
                null,
                name
        );
    }


    public Result notification() {
        Source<String, Cancellable> tickSource =  Source.tick(Duration.ofSeconds(2),Duration.ofSeconds(5),"TICK");
        Source<EventSource.Event, ?> eventSource;
        eventSource = tickSource.map(data -> event(data));
        return  ok().chunked(eventSource.via(EventSource.flow())).as("text/event-stream");

    }

}
