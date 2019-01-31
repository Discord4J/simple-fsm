package com.discord4j.fsm;

import java.util.Objects;

class HandlerKey<S, E> {
    private final Class<?> state;
    private final Class<?> eventType;

    HandlerKey(Class<? extends S> state, Class<? extends E> eventType) {
        this.state = state;
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HandlerKey<?, ?> key = (HandlerKey<?, ?>) o;
        return Objects.equals(state, key.state) &&
                Objects.equals(eventType, key.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, eventType);
    }
}
