package com.discord4j.fsm.water;

class WaterStates {
    static final class Solid extends WaterStates {
        static final Solid INSTANCE = new Solid();
        private Solid() {}
    }
    static final class Liquid extends WaterStates {
        static final Liquid INSTANCE = new Liquid();
        private Liquid() {}
    }
    static final class Gas extends WaterStates {
        static final Gas INSTANCE = new Gas();
        private Gas() {}
    }
}
