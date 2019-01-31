package com.discord4j.fsm.test;

import com.discord4j.fsm.UnhandledTransitionException;
import com.discord4j.fsm.test.Event.Event1;
import com.discord4j.fsm.test.Event.Event3;
import org.junit.jupiter.api.Test;

import static com.discord4j.fsm.test.States.A;
import static com.discord4j.fsm.test.States.B;
import static com.discord4j.fsm.test.States.C;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @Test
    public void testInitial() {
        TestStateMachine fsm = new TestStateMachine();

        assertEquals(A.INSTANCE, fsm.getCurrentState());
    }

    @Test
    public void testTransitionWithPositiveValue() {
        TestStateMachine fsm = new TestStateMachine();
        fsm.onEvent(new Event1(1));

        assertEquals(B.INSTANCE, fsm.getCurrentState());
    }

    @Test
    public void testTransitionWithNegativeValue() {
        TestStateMachine fsm = new TestStateMachine();
        fsm.onEvent(new Event1(-1));

        assertEquals(C.INSTANCE, fsm.getCurrentState());
    }

    @Test
    public void testTransitionWithZero() {
        TestStateMachine fsm = new TestStateMachine();

        assertThrows(UnhandledTransitionException.class, () -> {
            fsm.onEvent(new Event1(0));
        });
    }

    @Test
    public void testAnyHandler() {
        TestStateMachine fsm = new TestStateMachine();
        fsm.onEvent(new Event3());

        assertEquals(A.INSTANCE, fsm.getCurrentState());
    }

}
