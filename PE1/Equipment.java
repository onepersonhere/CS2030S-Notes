/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
abstract class Equipment {
  private boolean inUse = false;

  public void setInUse(boolean b) {
    this.inUse = b;
  }

  public boolean isInUse() {
    return inUse;
  }

  public abstract void repair();
}
