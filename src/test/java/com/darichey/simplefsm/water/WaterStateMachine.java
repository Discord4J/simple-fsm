package com.darichey.simplefsm.water;

import com.darichey.simplefsm.FiniteStateMachine;

import static com.darichey.simplefsm.water.WaterStates.*;
import static com.darichey.simplefsm.water.Event.*;

class WaterStateMachine extends FiniteStateMachine<WaterStates, Event> {

    {
        startWith(LIQUID);

        when(SOLID)
                .on(Melted.class, melted -> {
                    System.out.println("Solid -> Liquid");
                    return LIQUID;
                });

        when(LIQUID)
                .on(Frozen.class, frozen -> {
                    System.out.println("Liquid -> Solid");
                    return SOLID;
                })
                .on(Vaporized.class, vaporized -> {
                    System.out.println("Liquid -> Gas");
                    return GAS;
                });

        when(GAS)
                .on(Condensed.class, condensed -> {
                    System.out.println("Gas -> Liquid");
                    return LIQUID;
                });
    }
}
