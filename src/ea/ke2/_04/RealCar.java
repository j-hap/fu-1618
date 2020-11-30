package ea.ke2._04;

class RealCar implements Car {
  private double power;

  public RealCar(double power) {
    this.power = power;
  }

  public String readMotorPowerInKiloWatt() {
    return "Car: My power is " + power + " KW!";
  }

  // method for tuning the car
  public void tunePower() {
    power = power * 1.2;
  }
}
