package com.darichey.simplefsm.water;

import com.darichey.simplefsm.FiniteStateMachine;

import static com.darichey.simplefsm.water.WaterStates.*;
import static com.darichey.simplefsm.water.Event.*;

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
