package com.darichey.simplefsm.water;

import com.darichey.simplefsm.UnhandledTransitionException;
import com.darichey.simplefsm.water.Event.*;
import org.junit.jupiter.api.Test;

import static com.darichey.simplefsm.water.WaterStates.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WaterTest {

    @Test
    public void testInitialSet() {
        WaterStateMachine fsm = new WaterStateMachine();

        assertEquals(SOLID, fsm.getCurrentState());
    }

    @Test
    public void testTransition() {
        WaterStateMachine fsm = new WaterStateMachine();
        fsm.on(new Melted());

        assertEquals(LIQUID, fsm.getCurrentState());
    }

    @Test
    public void testUnhandledTransitionWithoutHandler() {
        WaterStateMachine fsm = new WaterStateMachine();

        assertThrows(UnhandledTransitionException.class, () -> {
            fsm.on(new Frozen());
        });
    }

    @Test
    public void testUnhandledTransitionWithHandler() {
        WaterStateMachine fsm = new WaterStateMachine();
        fsm.onUnhandled(event -> GAS);
        fsm.on(new Frozen());

        assertEquals(GAS, fsm.getCurrentState());
    }
}
