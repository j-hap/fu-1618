package ea.ke3._03;

class VehicleFullException extends RuntimeException {
}

class VehicleEmptyException extends RuntimeException {
}

public class Vehicle {
  // properties that all vehicle have in common
  private int passengerCapacity; // can't be changed after creation
  private int nPassengers;

  public Vehicle(int passengerCapacity) {
    this.passengerCapacity = passengerCapacity;
  }

  public void addPassenger() throws VehicleFullException {
    if (nPassengers < passengerCapacity) {
      ++nPassengers;
    } else {
      throw new VehicleFullException();
    }
  }

  public void removePassenger() throws VehicleEmptyException {
    if (nPassengers > 0) {
      --nPassengers;
    } else {
      throw new VehicleEmptyException();
    }
  }

  public boolean isEmpty() {
    return nPassengers == 0;
  }
}

