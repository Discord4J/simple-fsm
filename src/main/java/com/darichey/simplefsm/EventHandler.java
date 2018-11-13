package com.darichey.simplefsm;

import java.util.function.Function;
import java.util.function.Predicate;

class EventHandler<S, E> {

    private final Function<? super E, ? extends S> handle;
    private final Predicate<? super E> canHandle;

    EventHandler(Function<? super E, ? extends S> handle, Predicate<? super E> canHandle) {
        this.handle = handle;
        this.canHandle = canHandle;
    }

    S handle(E event) {
        return handle.apply(event);
    }

    boolean canHandle(E event) {
        return canHandle.test(event);
    }
}
