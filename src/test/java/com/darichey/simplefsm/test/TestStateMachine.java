package com.darichey.simplefsm.test;

import com.darichey.simplefsm.FiniteStateMachine;
import com.darichey.simplefsm.test.Event.*;

import static com.darichey.simplefsm.test.States.*;

class TestStateMachine extends FiniteStateMachine<States, Event> {

    {
        startWith(A);

        when(A)
                .on(Event1.class, event1 -> event1.data > 0, event1 -> B)
                .on(Event1.class, event1 -> event1.data < 0, event1 -> C);

        when(B).on(Event2.class, event2 -> A);
        when(C).on(Event2.class, event2 -> A);
    }
}
