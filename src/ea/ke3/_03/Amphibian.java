package ea.ke3._03;

public class Amphibian extends Vehicle implements Maneuverable, Driveable, Floatable {
  private boolean wheelsExtended = true;

  public Amphibian(int passengerCapacity) {
    super(passengerCapacity);
  }

  @Override
  public void stop() {

  }

  @Override
  public void start() {

  }

  @Override
  public void accelerate() {

  }

  @Override
  public void decelerate() {

  }

  @Override
  public void anchor() {

  }

  @Override
  public void hoistAnchor() {

  }

  @Override
  public void dock() {

  }

  @Override
  public void castOff() {

  }

  @Override
  public void sink() {

  }

  public void water() {
    wheelsExtended = false;
  }

  @Override
  public void park() {

  }
}
