package me.escoffier.lab.chapter8;

import io.reactivex.subscribers.TestSubscriber;
import me.escoffier.superheroes.Helpers;
import me.escoffier.superheroes.SuperStuff;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AsyncTest_Solution {

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
    public void theRightWayToTest() throws TimeoutException {
        TestSubscriber<SuperStuff> testSubscriber = Helpers.heroes().test();
        if (!testSubscriber.awaitTerminalEvent(5, TimeUnit.SECONDS)) {
            throw new TimeoutException("It timed out!");
        }
        testSubscriber
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(520);
    }
}
