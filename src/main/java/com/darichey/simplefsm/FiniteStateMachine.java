package com.darichey.simplefsm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FiniteStateMachine<S, E> {

    private final Map<HandlerKey<S>, Function<? super E, ? extends S>> transitionHandlers = new HashMap<>();
    private Function<? super E, ? extends S> fallbackHandler;
    private S currentState;

    public void on(E event) {
        Function<? super E, ? extends S> handler = transitionHandlers.get(new HandlerKey<>(currentState, event.getClass()));
        if (handler != null) {
            currentState = handler.apply(event);
        } else if (fallbackHandler != null) {
            currentState = fallbackHandler.apply(event);
        } else {
            throw new UnhandledTransitionException(currentState, event);
        }
    }

    public void initial(S initialState) {
        this.currentState = initialState;
    }

    public StateMachineDSL<S, E> when(S fromState) {
        return new StateMachineDSL<>(this, fromState);
    }

    public void onUnhandled(Function<? super E, ? extends S> fallbackHandler) {
        this.fallbackHandler = fallbackHandler;
    }

    public S getCurrentState() {
        return currentState;
    }

    void addHandler(S from, Class<? extends E> eventType, Function<? super E, ? extends S> transitionHandler) {
        transitionHandlers.put(new HandlerKey<>(from, eventType), transitionHandler);
    }
}
