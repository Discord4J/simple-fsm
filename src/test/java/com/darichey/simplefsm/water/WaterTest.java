package com.darichey.simplefsm.water;

import com.darichey.simplefsm.UnhandledTransitionException;
import com.darichey.simplefsm.water.Event.*;
import org.junit.jupiter.api.Test;

import static com.darichey.simplefsm.water.WaterStates.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WaterTest {

    @Test
    public void testInitial() {
        WaterStateMachine fsm = new WaterStateMachine();

        assertEquals(LIQUID, fsm.getCurrentState());
    }

    @Test
    public void testTransition() {
        WaterStateMachine fsm = new WaterStateMachine();
        fsm.onEvent(new Frozen());

        assertEquals(SOLID, fsm.getCurrentState());
    }

    @Test
    public void testUnhandledTransitionWithoutHandler() {
        WaterStateMachine fsm = new WaterStateMachine();

        assertThrows(UnhandledTransitionException.class, () -> {
            fsm.onEvent(new Melted());
        });
    }

    @Test
    public void testUnhandledTransitionWithHandler() {
        WaterStateMachine fsm = new WaterStateMachine();
        fsm.onUnhandled(event -> fsm.getCurrentState()); // essentially ignore
        fsm.onEvent(new Melted());

        assertEquals(LIQUID, fsm.getCurrentState());
    }
}
