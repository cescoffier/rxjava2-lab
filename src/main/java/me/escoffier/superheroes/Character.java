package me.escoffier.superheroes;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Character {

    private final static AtomicInteger ID = new AtomicInteger();

    private String name;

    private List<String> superpowers = new ArrayList<>();

    private boolean villain;

    private int id;

    public Character(String name, List<String> list, boolean isHeroes) {
        this(ID.getAndIncrement(), name, list, isHeroes);
    }

    public Character(int id, String name, List<String> list, boolean isHeroes) {
        this.id = id;
        this.name = name;
        this.superpowers.addAll(list);
        this.villain = !isHeroes;
    }

    public Character() {
        // Used by mapper.
    }

    @Override
    public String toString() {
        return name + " (" + (villain ? "villain" : "hero") + ") : " + superpowers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(List<String> superpowers) {
        this.superpowers = superpowers;
    }

    public boolean isVillain() {
        return villain;
    }

    public void setVillain(boolean villain) {
        this.villain = villain;
    }

    public JsonObject toJson() {
        return new JsonObject()
            .put("id", id)
            .put("name", name)
            .put("superpowers", superpowers.stream().collect((Supplier<JsonArray>) JsonArray::new, JsonArray::add, JsonArray::addAll))
            .put("villain", villain);
    }

    public Character(JsonObject json) {
        this.id = json.getInteger("id");
        this.name = json.getString("name");
        this.villain = json.getBoolean("villain");
        JsonArray powers = json.getJsonArray("superpowers");
        this.superpowers = powers.stream().map(Object::toString).collect(Collectors.toList());

    }
}
