package com.darichey.simplefsm;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class EventHandler<S, E, U extends S> {

    private final BiFunction<? super U, ? super E, ? extends S> handle;
    private final Predicate<? super E> canHandle;

    EventHandler(BiFunction<? super U, ? super E, ? extends S> handle, Predicate<? super E> canHandle) {
        this.handle = handle;
        this.canHandle = canHandle;
    }

    S handle(U curState, E event) {
        return handle.apply(curState, event);
    }

    boolean canHandle(E event) {
        return canHandle.test(event);
    }
}
