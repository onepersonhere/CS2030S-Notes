/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
public class Dumbbell extends Equipment {
  private final double weight;
  private int repairCount = 0;
  
  public Dumbbell(double weight) {
    this.weight = weight;
  }

  public double getWeight() {
    return this.weight;
  }

  public int getRepairCount() {
    return this.repairCount;
  }

  @Override
  public void repair() {
    this.repairCount++;
  }

  @Override
  public String toString() {
    return "Dumbbell: " + this.weight + " kg";
  }
}
