package me.escoffier.superheroes;

import io.reactivex.Completable;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class SuperHeroesService {

    public static void main(String[] args) {
        SuperHeroesService service = new SuperHeroesService();
        service.start().subscribe();
    }

    public static void run() {
        new SuperHeroesService().start().blockingAwait();
    }

    private Random random = new Random();
    private Map<Integer, Entity> villains;
    private Map<Integer, Entity> heroes;

    public Completable start() {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);
        router.get("/heroes").handler(this::getAllHeroes);
        router.get("/villains").handler(this::getAllVillains);
        router.get("/heroes/random").handler(this::getRandomHero);
        router.get("/heroes/:id").handler(this::getHeroById);
        router.get("/heroes").handler(this::getAllVillains);
        router.get("/villains/random").handler(this::getRandomVillain);
        router.get("/villains/:id").handler(this::getVillainById);

        return vertx.fileSystem().rxReadFile("src/main/resources/entities.json")
            .map(buffer -> buffer.toJsonArray().stream().map(o -> new Entity((JsonObject) o)).collect(Collectors.toList()))
            .doOnSuccess(list -> System.out.println("Loaded " + list.size() + " heroes and villains"))
            .doOnSuccess(list -> {
                this.villains = list.stream().filter(Entity::isVillain).collect(
                    HashMap::new, (map, entity) -> map.put(entity.getId(), entity), HashMap::putAll);
                this.heroes = list.stream().filter(e -> ! e.isVillain()).collect(
                    HashMap::new, (map, entity) -> map.put(entity.getId(), entity), HashMap::putAll);
            })
            .flatMap(x -> vertx.createHttpServer()
                .requestHandler(router::accept)
                .rxListen(8080))
            .toCompletable();

    }

    private void getAllHeroes(RoutingContext rc) {
        rc.response().end(heroes.values().stream()
            .collect(JsonObject::new,
                (json, entity) -> json.put(Integer.toString(entity.getId()), entity.getName()),
                JsonObject::mergeIn)
            .encodePrettily());
    }

    private void getAllVillains(RoutingContext rc) {
        rc.response().end(villains.values().stream()
            .collect(JsonObject::new,
                (json, entity) -> json.put(Integer.toString(entity.getId()), entity.getName()),
                JsonObject::mergeIn)
            .encodePrettily());
    }

    private void getHeroById(RoutingContext rc) {
        getById(rc, heroes);
    }

    private void getById(RoutingContext rc, Map<Integer, Entity> map) {
        String id = rc.pathParam("id");
        try {
            Integer value = Integer.valueOf(id);
            Entity entity = map.get(value);
            if (entity == null) {
                rc.response().setStatusCode(404).end("Unknown hero " + id);
            } else {
                rc.response().end(entity.toJson().encodePrettily());
            }
        } catch (NumberFormatException e) {
            rc.response().setStatusCode(404).end("Unknown hero " + id);
        }
    }

    private void getVillainById(RoutingContext rc) {
        getById(rc, villains);
    }

    private void getRandomHero(RoutingContext rc) {
        int index = random.nextInt(heroes.size());
        rc.response().end(heroes.get(index).toJson().encodePrettily());
    }

    private void getRandomVillain(RoutingContext rc) {
        int index = random.nextInt(villains.size());
        rc.response().end(villains.get(index).toJson().encodePrettily());
    }


}
