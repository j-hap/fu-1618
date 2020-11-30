package ea.ke2._04;

public class CarFactoryTest {
  public static void main(String args[]) {
    CarFactory factory = new CarFactory();
    Car car = factory.giveMeACar();
    System.out.println(car.readMotorPowerInKiloWatt());
    // car.tunePower() does not work, because tunePower is not defined in the "interface Car"
    RealCar tunedCar = (RealCar)car; // casts into implemented object, bypasses interface
    tunedCar.tunePower();
    System.out.println(car.readMotorPowerInKiloWatt());
  }
}

