/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
public class Customer implements Person {
  private String name;

  public Customer(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Customer: " + this.name;
  }
}
