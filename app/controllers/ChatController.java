package controllers;

import actors.WebSocketActor;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import com.google.inject.Inject;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.WebSocket;

public class ChatController extends Controller {

    private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public ChatController(ActorSystem actorSystem, Materializer materializer){
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }

    public WebSocket websocket(){
        return WebSocket.Text.accept(
                request -> ActorFlow.actorRef(WebSocketActor::props, actorSystem, materializer)
        );
    }

}
