package me.escoffier.superheroes;


import io.reactivex.Flowable;
import io.reactivex.Single;
import io.vertx.core.json.Json;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Scraper {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.superherodb.com/characters/").get();
        System.out.println("Scraping " + doc.title());
        Elements links = doc.select("a[title]");

        Map<String, String> names = new LinkedHashMap<>();
        links.forEach(element -> {
            String name = element.attr("title");
            String href = element.attr("href");
            if (name != null && !name.trim().isEmpty()  && ! isExcluded(name)) {
                names.put(name, href);
            }
        });

        System.out.println(names.size() + " superheros and villains found");

        Vertx vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        
        AtomicInteger counter = new AtomicInteger();

        Flowable.fromIterable(names.entrySet())
            .flatMapSingle(entry -> scrap(client, entry.getKey(), "https://www.superherodb.com" + entry.getValue()))
            .doOnNext(superStuff -> System.out.println("Retrieved " + superStuff + " (" + counter.incrementAndGet() + " / " +
                names.size() + ")"))
            .toList()
            .flatMapCompletable(list -> vertx.fileSystem()
                .rxWriteFile("src/main/resources/characters.json", new Buffer(Json.encodeToBuffer(list)))
            )
            .subscribe(
                () -> System.out.println("Written " + names.size() + " super heroes and villains"),
                Throwable::printStackTrace
            );
    }

    private static boolean isExcluded(String name) {
        return name.contains("All comic book")
            || name.startsWith("List of")
            || name.contains("Superhero species and types");
    }

    private static Single<Character> scrap(WebClient client, String name, String url) {
        return client.getAbs(url)
            .rxSend()
            .map(HttpResponse::bodyAsString)
            .map(Jsoup::parse)
            .map(document -> {
                // We need to skip the first one, it's a title.
                Elements powers = document.select("ul a[href^='/powers/']");
                List<String> list = powers.stream().skip(1).map(Element::text).collect(Collectors.toList());
                boolean isHero = ! isVillain(document);
                return new Character(name, list, isHero);
            });
    }

    private static boolean isVillain(Document document) {
        Elements elements = document.select(".td-wide");
        return elements.stream().map(Element::text).anyMatch(s -> s.equalsIgnoreCase("bad"));
    }

}
