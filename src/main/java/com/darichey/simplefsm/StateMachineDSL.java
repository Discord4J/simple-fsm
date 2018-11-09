package com.darichey.simplefsm;

import java.util.function.Function;

public class StateMachineDSL<S, E> {
    private final FiniteStateMachine<S, E> owner;
    private final S from;

    StateMachineDSL(FiniteStateMachine<S, E> owner, S from) {
        this.owner = owner;
        this.from = from;
    }

    public StateMachineDSL<S, E> on(Class<? extends E> eventType, Function<? super E, ? extends S> transitionHandler) {
        owner.addHandler(from, eventType, transitionHandler);
        return this;
    }
}
