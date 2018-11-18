package com.darichey.simplefsm.test;

import com.darichey.simplefsm.UnhandledTransitionException;
import com.darichey.simplefsm.test.Event.Event1;
import com.darichey.simplefsm.test.Event.Event3;
import org.junit.jupiter.api.Test;

import static com.darichey.simplefsm.test.States.A;
import static com.darichey.simplefsm.test.States.B;
import static com.darichey.simplefsm.test.States.C;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @Test
    public void testInitial() {
        TestStateMachine fsm = new TestStateMachine();

        assertEquals(A, fsm.getCurrentState());
    }

    @Test
    public void testTransitionWithPositiveValue() {
        TestStateMachine fsm = new TestStateMachine();
        fsm.onEvent(new Event1(1));

        assertEquals(B, fsm.getCurrentState());
    }

    @Test
    public void testTransitionWithNegativeValue() {
        TestStateMachine fsm = new TestStateMachine();
        fsm.onEvent(new Event1(-1));

        assertEquals(C, fsm.getCurrentState());
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

        assertEquals(A, fsm.getCurrentState());
    }

}
