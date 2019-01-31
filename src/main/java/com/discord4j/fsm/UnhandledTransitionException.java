package com.discord4j.fsm;

public class UnhandledTransitionException extends RuntimeException {

    UnhandledTransitionException(Object state, Object event) {
        super("Unhandled transition with no fallback handler: " + state + " on " + event);
    }
}
