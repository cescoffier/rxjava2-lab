package me.escoffier.lab.chapter8;

import com.sun.xml.internal.ws.policy.AssertionSet;
import io.reactivex.functions.Consumer;
import me.escoffier.superheroes.Helpers;
import me.escoffier.superheroes.SuperStuff;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AsyncTest {

    private static class TBox {
        private Throwable t;

        void set(Throwable t) {
            this.t = t;
        }
    }

    @Test
    public void theWrongWayToTest() throws Throwable {
        TBox box = new TBox();
        ArrayList<SuperStuff> stuffs = new ArrayList<>();

        Helpers.heroes()
                .subscribe(stuffs::add, box::set, () -> System.out.println("[ done ]"));

        Thread.sleep(5000);

        if (box.t != null) {
            throw box.t;
        }
        assertThat(stuffs.size(), equalTo(520));
    }

    @Test
    public void theRightWayToTest() {
        Helpers.heroes()
        // complete here
        ;
    }
}
