package ea.ke3._03;

public class Boat extends Vehicle implements Maneuverable, Floatable {
  private boolean positionIsFixed = true;

  public Boat(int passengerCapacity) {
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
    positionIsFixed = true;
  }

  @Override
  public void hoistAnchor() {
    positionIsFixed = false;
  }

  @Override
  public void dock() {
    positionIsFixed = true;
  }

  @Override
  public void castOff() {

  }

  @Override
  public void sink() {

  }
}
