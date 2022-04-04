/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
public class Gym {
  private int capacity = 0;
  private int currSize = 0;
  
  public Gym(int capacity) {
    this.capacity = capacity;
  }

  public void enter(Person person) {
    if (this.currSize < this.capacity) {
      System.out.println(person.toString() + " can enter");
      this.currSize++;
    } else {
      System.out.println(person.toString() + " cannot enter");
    }
  }

  @Override
  public String toString() {
    return "Gym Capacity: " + this.currSize + "/" + this.capacity;
  }
}
