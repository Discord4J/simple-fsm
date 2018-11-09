package com.darichey.simplefsm;

public class UnhandledTransitionException extends RuntimeException {

    UnhandledTransitionException(Object state, Object event) {
        super("Unhandled transition with no fallback handler: " + state + " on " + event);
    }
}
