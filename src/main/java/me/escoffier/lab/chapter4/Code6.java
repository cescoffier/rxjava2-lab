package me.escoffier.lab.chapter4;


import static me.escoffier.superheroes.Helpers.heroes;

public class Code6 {

    public static void main(String[] args) {
        heroes()
            // Build a set containing all the super power from the heroes
            // This exercise uses the `scan` method

            
            .doOnNext(System.out::println)
            .count()
            .subscribe(
                number -> System.out.println("Heroes have " + number + " unique super powers")
            );
    }

}
