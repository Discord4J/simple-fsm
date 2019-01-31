package com.discord4j.fsm.water;

import com.discord4j.fsm.FiniteStateMachine;

import static com.discord4j.fsm.water.WaterStates.*;
import static com.discord4j.fsm.water.Event.*;

class WaterStateMachine extends FiniteStateMachine<WaterStates, Event> {

    {
        startWith(Liquid.INSTANCE);

        when(Solid.class)
                .on(Melted.class, (curState, melted) -> {
                    System.out.println("Solid -> Liquid");
                    return Liquid.INSTANCE;
                });

        when(Liquid.class)
                .on(Frozen.class, (curState, frozen) -> {
                    System.out.println("Liquid -> Solid");
                    return Solid.INSTANCE;
                })
                .on(Vaporized.class, (curState, vaporized) -> {
                    System.out.println("Liquid -> Gas");
                    return Gas.INSTANCE;
                });

        when(Gas.class)
                .on(Condensed.class, (curState, condensed) -> {
                    System.out.println("Gas -> Liquid");
                    return Liquid.INSTANCE;
                });
    }
}
