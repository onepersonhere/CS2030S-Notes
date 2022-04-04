/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
public class Treadmill extends Equipment {
  private double speed = 0.0;

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getSpeed() {
    return this.speed;
  }

  @Override
  public void repair() {
    this.setSpeed(0.0);
  }

  @Override
  public String toString() {
    return "Treadmill: " + this.speed + " km/h";
  }
}
