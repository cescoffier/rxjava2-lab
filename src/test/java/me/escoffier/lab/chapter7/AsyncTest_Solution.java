package me.escoffier.lab.chapter7;

import io.reactivex.subscribers.TestSubscriber;
import me.escoffier.superheroes.Helpers;
import me.escoffier.superheroes.Character;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsyncTest_Solution {

    @Test
    public void theRightWayToTest() throws TimeoutException {
        TestSubscriber<Character> testSubscriber = Helpers.heroes().test();
        if (!testSubscriber.awaitTerminalEvent(5, TimeUnit.SECONDS)) {
            throw new TimeoutException("It timed out!");
        }
        testSubscriber
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(520);
    }
}
