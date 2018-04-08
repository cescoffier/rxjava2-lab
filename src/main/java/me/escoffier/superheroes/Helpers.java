package me.escoffier.superheroes;


import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.file.FileSystem;
import io.vertx.reactivex.ext.web.client.WebClient;

public class Helpers {

    public static FileSystem fs() {
        return Vertx.vertx().fileSystem();
    }

    public static WebClient client() {
        Vertx vertx = Vertx.vertx();
        return WebClient.create(vertx,
            new WebClientOptions().setDefaultPort(8080).setDefaultHost("localhost")
        );
    }


}
