package com.darichey.simplefsm;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FiniteStateMachine<S, E> {

    private final Map<HandlerKey<S>, List<EventHandler<S, E>>> transitionHandlers = new HashMap<>();
    private Function<? super E, ? extends S> fallbackHandler;
    private S currentState;

    public void startWith(S initialState) {
        this.currentState = initialState;
    }

    public StateMachineDSL<S, E> when(S fromState) {
        return new StateMachineDSL<>(this, fromState);
    }

    public StateMachineDSL<S, E> whenAny() {
        return new StateMachineDSL<>(this, null);
    }

    public void onUnhandled(Function<? super E, ? extends S> fallbackHandler) {
        this.fallbackHandler = fallbackHandler;
    }

    public void onEvent(E event) {
        if (currentState == null) {
            throw new IllegalStateException("Attempt to trigger event before setting initial state");
        }

        List<EventHandler<S, E>> handlers = transitionHandlers.getOrDefault(new HandlerKey<>(currentState, event.getClass()), Collections.emptyList());
        for (EventHandler<S, E> handler : handlers) {
            if (handler.canHandle(event)) {
                currentState = handler.handle(event);
                return;
            }
        }

        List<EventHandler<S, E>> anyHandlers = transitionHandlers.getOrDefault(new HandlerKey<S>(null, event.getClass()), Collections.emptyList());
        for (EventHandler<S, E> handler : anyHandlers) {
            if (handler.canHandle(event)) {
                currentState = handler.handle(event);
                return;
            }
        }

        if (fallbackHandler != null) {
            currentState = fallbackHandler.apply(event);
        } else {
            throw new UnhandledTransitionException(currentState, event);
        }
    }

    public S getCurrentState() {
        return currentState;
    }

    @SuppressWarnings("unchecked")
    <C extends E> void addHandler(S from, Class<C> eventType, Predicate<? super C> canHandle, Function<? super C, ? extends S> handler) {
        transitionHandlers
                .computeIfAbsent(new HandlerKey<>(from, eventType), key -> new ArrayList<>())
                .add((EventHandler<S, E>) new EventHandler<S, C>(handler, canHandle));
    }
}
