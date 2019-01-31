package com.discord4j.fsm.test;

import com.discord4j.fsm.FiniteStateMachine;
import com.discord4j.fsm.test.Event.*;

import static com.discord4j.fsm.test.States.*;

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
