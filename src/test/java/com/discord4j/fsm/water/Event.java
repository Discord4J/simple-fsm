package com.discord4j.fsm.water;

class Event {
    static class Condensed extends Event {}
    static class Frozen extends Event {}
    static class Melted extends Event {}
    static class Vaporized extends Event {}
}