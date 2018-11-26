package com.darichey.simplefsm.test;

import com.darichey.simplefsm.FiniteStateMachine;
import com.darichey.simplefsm.test.Event.*;

import static com.darichey.simplefsm.test.States.*;

class TestStateMachine extends FiniteStateMachine<States, Event> {

    {
        startWith(A.INSTANCE);

        when(A.class)
                .on(Event1.class, event1 -> event1.data > 0, (curState, event1) -> B.INSTANCE)
                .on(Event1.class, event1 -> event1.data < 0, (curState, event1) -> C.INSTANCE);

        when(B.class).on(Event2.class, (curState, event2) -> A.INSTANCE);
        when(C.class).on(Event2.class, (curState, event2) -> A.INSTANCE);

        whenAny().on(Event3.class, (curState, event3) -> getCurrentState());
    }
}
