package com.discord4j.fsm.test;

class Event {
    static class Event1 extends Event {
        int data;
        public Event1(int data) { this.data = data; }
    }
    static class Event2 extends Event {}
    static class Event3 extends Event {}
}
