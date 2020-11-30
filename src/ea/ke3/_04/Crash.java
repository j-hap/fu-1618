package ea.ke3._04;

class Vehicle {
}

class PassengerCar extends Vehicle {
}

class Smart extends PassengerCar {
}

class Truck extends Vehicle {
}

class Mercedes extends Truck {
}

class Crash {
  void adac_testen(Vehicle a, Truck d) { /* ... */
  } // Deklaration #1

  void adac_testen(PassengerCar b, Vehicle a) { /* ... */
  } // Deklaration #2

  void adac_testen(Smart c, Truck d) { /* ... */
  } // Deklaration #3

  void CrashTest() {
    Vehicle v = new Vehicle();
    PassengerCar p = new PassengerCar();
    Smart s = new Smart();
    Truck t = new Truck();
    Mercedes m = new Mercedes();

    adac_testen(v, t); // Aufruf #1
    adac_testen(s, v); // Aufruf #2
    adac_testen(s, m); // Aufruf #3
    // adac_testen(p, t); // Aufruf #4 // does not work
  }
}
