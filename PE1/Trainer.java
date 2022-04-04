/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
public class Trainer implements Person {
  private String name;
  private Customer customer;
  private Equipment equipment;

  public Trainer(String name) {
    this.name = name;
  }
  
  public void startTraining(Customer customer, Equipment equipment) throws CannotTrainException {
    this.equipment = equipment;

    if (!this.equipment.isInUse() && this.customer == null) {
      this.customer = customer;
      this.equipment.setInUse(true);
    } else {
      throw new CannotTrainException();
    }
  }

  public void stopTraining() {
    this.customer = null;
    this.equipment.setInUse(false);
  }

  public String getStatus() {
    return  this.customer == null
      ? this.toString() + " not training"
      : this.toString() + " training " + this.customer.toString();
  }

  @Override
  public String toString() {
    return "Trainer: " + this.name;
  }
}
