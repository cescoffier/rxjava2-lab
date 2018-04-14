package me.escoffier.lab.chapter5;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import me.escoffier.superheroes.Character;

/**
 * An API to retrieve Super heroes and villains
 */
public interface SuperAPI {

    /**
     * @return a random hero
     */
    Single<Character> hero();

    /**
     * @return a random villain
     */
    Single<Character> villain();

    /**
     * @return all heroes
     */
    Flowable<Character> heroes();

    /**
     * @return all villains
     */
    Flowable<Character> villains();

    /**
     * Looks for a character with the given name.
     *
     * @param name the name of the character. Must not be {@code null}
     * @return a {@code Maybe} completed with the found character, empty otherwise
     */
    Maybe<Character> findByName(String name);

    /**
     * Looks for a character with the given name.
     *
     * @param name the name of the character. Must not be {@code null}
     * @return a {@code Single} completed with the found character, or failed if not found
     */
    Single<Character> findByNameOrError(String name);
}
