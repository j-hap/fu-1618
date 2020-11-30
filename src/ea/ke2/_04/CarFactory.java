package ea.ke2._04;

class CarFactory {
  public Car giveMeACar() {
    return new RealCar(98);
  }
}
