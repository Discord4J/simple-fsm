package com.darichey.simplefsm;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class StateMachineDSL<S, E, U extends S> {

    private final FiniteStateMachine<S, E> owner;
    private final Class<U> fromState;

    StateMachineDSL(FiniteStateMachine<S, E> owner, Class<U> fromState) {
        this.owner = owner;
        this.fromState = fromState;
    }

    public <C extends E> StateMachineDSL<S, E, U> on(Class<C> eventType, BiFunction<? super U, ? super C, ? extends S> handler) {
        return on(eventType, e -> true, handler);
    }

    public <C extends E> StateMachineDSL<S, E, U> on(Class<C> eventType, Predicate<? super C> canHandle, BiFunction<? super U, ? super C, ? extends S> handler) {
        owner.addHandler(fromState, eventType, canHandle, handler);
        return this;
    }
}
