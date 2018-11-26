package com.darichey.simplefsm;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FiniteStateMachine<S, E> {

    private final Map<HandlerKey<S, E>, List<EventHandler<S, E, S>>> transitionHandlers = new HashMap<>();
    private Function<? super E, ? extends S> fallbackHandler;
    private S currentState;

    public void startWith(S initialState) {
        this.currentState = initialState;
    }

    public <U extends S> StateMachineDSL<S, E, U> when(Class<U> fromState) {
        return new StateMachineDSL<>(this, fromState);
    }

    public StateMachineDSL<S, E, ?> whenAny() {
        return new StateMachineDSL<>(this, null);
    }

    public void onUnhandled(Function<? super E, ? extends S> fallbackHandler) {
        this.fallbackHandler = fallbackHandler;
    }

    @SuppressWarnings("unchecked")
    public void onEvent(E event) {
        if (currentState == null) {
            throw new IllegalStateException("Attempt to trigger event before setting initial state");
        }

        HandlerKey<S, E> key = new HandlerKey<>((Class<S>) currentState.getClass(), (Class<E>) event.getClass());
        List<EventHandler<S, E, S>> handlers = transitionHandlers.getOrDefault(key, Collections.emptyList());
        for (EventHandler<S, E, S> handler : handlers) {
            if (handler.canHandle(event)) {
                currentState = handler.handle(getCurrentState(), event);
                return;
            }
        }

        key = new HandlerKey<>(null, (Class<E>) event.getClass());
        List<EventHandler<S, E, S>> anyHandlers = transitionHandlers.getOrDefault(key, Collections.emptyList());
        for (EventHandler<S, E, S> handler : anyHandlers) {
            if (handler.canHandle(event)) {
                currentState = handler.handle(getCurrentState(), event);
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
    <C extends E, U extends S> void addHandler(Class<U> from, Class<C> eventType, Predicate<? super C> canHandle, BiFunction<? super U, ? super C, ? extends S> handler) {
        transitionHandlers
                .computeIfAbsent(new HandlerKey<>(from, eventType), key -> new ArrayList<>())
                .add((EventHandler<S, E, S>) new EventHandler<S, C, U>(handler, canHandle));
    }
}
