package com.darichey.simplefsm;

import java.util.function.Function;
import java.util.function.Predicate;

public class StateMachineDSL<S, E> {

    private final FiniteStateMachine<S, E> owner;
    private final S fromState;

    StateMachineDSL(FiniteStateMachine<S, E> owner, S fromState) {
        this.owner = owner;
        this.fromState = fromState;
    }

    public <C extends E> StateMachineDSL<S, E> on(Class<C> eventType, Function<? super C, ? extends S> handler) {
        return on(eventType, e -> true, handler);
    }

    public <C extends E> StateMachineDSL<S, E> on(Class<C> eventType, Predicate<? super C> canHandle, Function<? super C, ? extends S> handler) {
        owner.addHandler(fromState, eventType, canHandle, handler);
        return this;
    }
}
