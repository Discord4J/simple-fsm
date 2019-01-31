package com.discord4j.fsm.water;

import com.discord4j.fsm.UnhandledTransitionException;
import com.discord4j.fsm.water.Event.*;
import org.junit.jupiter.api.Test;

import static com.discord4j.fsm.water.WaterStates.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WaterTest {

    @Test
    public void testInitial() {
        WaterStateMachine fsm = new WaterStateMachine();

        assertEquals(Liquid.INSTANCE, fsm.getCurrentState());
    }

    @Test
    public void testTransition() {
        WaterStateMachine fsm = new WaterStateMachine();
        fsm.onEvent(new Frozen());

        assertEquals(Solid.INSTANCE, fsm.getCurrentState());
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

        assertEquals(Liquid.INSTANCE, fsm.getCurrentState());
    }
}
