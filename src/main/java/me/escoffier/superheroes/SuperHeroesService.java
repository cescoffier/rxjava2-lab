package me.escoffier.superheroes;

import io.reactivex.Completable;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

import java.util.*;
import java.util.stream.Collectors;

public class SuperHeroesService {

    public static void main(String[] args) {
        SuperHeroesService service = new SuperHeroesService(true);
        service.start().subscribe();
    }

    public static void run() {
        new SuperHeroesService(true).start().blockingAwait();
    }

    public static void run(boolean verbose) {
        new SuperHeroesService(verbose).start().blockingAwait();
    }

    private final boolean verbose;
    private Random random = new Random();
    private Map<Integer, Character> villains;
    private Map<Integer, Character> heroes;

    public SuperHeroesService(boolean verbose) {
        this.verbose = verbose;
    }

    public Completable start() {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);
        router.get("/heroes").handler(this::getAllHeroes);
        router.get("/villains").handler(this::getAllVillains);
        router.get("/heroes/random").handler(this::getRandomHero);
        router.get("/heroes/:id").handler(this::getHeroById);
        router.get("/villains/random").handler(this::getRandomVillain);
        router.get("/villains/:id").handler(this::getVillainById);

        return vertx.fileSystem().rxReadFile("src/main/resources/characters.json")
            .map(buffer -> buffer.toJsonArray().stream().map(o -> new Character((JsonObject) o)).collect(Collectors.toList()))
            .doOnSuccess(list -> {
                if (verbose) {
                    System.out.println("Loaded " + list.size() + " heroes and villains");
                }
            })
            .doOnSuccess(list -> {
                this.villains = list.stream().filter(Character::isVillain).collect(
                    HashMap::new, (map, superStuff) -> map.put(superStuff.getId(), superStuff), HashMap::putAll);
                this.heroes = list.stream().filter(e -> ! e.isVillain()).collect(
                    HashMap::new, (map, superStuff) -> map.put(superStuff.getId(), superStuff), HashMap::putAll);
            })
            .flatMap(x -> vertx.createHttpServer()
                .requestHandler(router::accept)
                .rxListen(8080))
            .toCompletable();

    }

    private void getAllHeroes(RoutingContext rc) {
        rc.response().end(heroes.values().stream()
            .collect(JsonObject::new,
                (json, superStuff) -> json.put(Integer.toString(superStuff.getId()), superStuff.getName()),
                JsonObject::mergeIn)
            .encodePrettily());
    }

    private void getAllVillains(RoutingContext rc) {
        rc.response().end(villains.values().stream()
            .collect(JsonObject::new,
                (json, superStuff) -> json.put(Integer.toString(superStuff.getId()), superStuff.getName()),
                JsonObject::mergeIn)
            .encodePrettily());
    }

    private void getHeroById(RoutingContext rc) {
        getById(rc, heroes);
    }

    private void getById(RoutingContext rc, Map<Integer, Character> map) {
        String id = rc.pathParam("id");
        try {
            Integer value = Integer.valueOf(id);
            Character character = map.get(value);
            if (character == null) {
                rc.response().setStatusCode(404).end("Unknown hero " + id);
            } else {
                rc.response().end(character.toJson().encodePrettily());
            }
        } catch (NumberFormatException e) {
            rc.response().setStatusCode(404).end("Unknown hero " + id);
        }
    }

    private void getVillainById(RoutingContext rc) {
        getById(rc, villains);
    }

    private void getRandomHero(RoutingContext rc) {
        List<Character> h = new ArrayList<>(heroes.values());
        int index = random.nextInt(h.size());
        Character hero = h.get(index);
        if (verbose) {
            System.out.println("Selected hero " + hero);
        }
        rc.response().end(hero.toJson().encodePrettily());
    }

    private void getRandomVillain(RoutingContext rc) {
        List<Character> h = new ArrayList<>(villains.values());
        int index = random.nextInt(h.size());
        Character villain = h.get(index);
        if (verbose) {
            System.out.println("Selected villain " + villain);
        }
        rc.response().end(villain.toJson().encodePrettily());
    }


}
