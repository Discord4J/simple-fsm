package com.discord4j.fsm.test;

class States {
    static final class A extends States {
        static final A INSTANCE = new A();
        private A() {}
    }
    static final class B extends States {
        static final B INSTANCE = new B();
        private B() {}
    }
    static final class C extends States {
        static final C INSTANCE = new C();
        private C() {}
    }
}
