class Test1 {
  /**
   * Main method for Test1.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expectCompile("Checking Equipment e = new Equipment(); does not compile", 
                    "Equipment e = new Equipment();", false);

    i.expectCompile("Checking if Equipment e = new Treadmill();"
                    + " compiles", "Equipment e = new Treadmill();", true);

    i.expectCompile("Checking if Equipment e = new Dumbbell(2.5); compiles", 
                    "Equipment e = new Dumbbell(2.5);", true);

    i.expect("new Treadmill().toString() returns \"Treadmill: 0.0 km/h\"", 
                    new Treadmill().toString(), "Treadmill: 0.0 km/h");

    i.expect("new Dumbbell(2.5).toString() returns \"Dumbbell: 2.5 kg\"", 
             new Dumbbell(2.5).toString(), 
             "Dumbbell: 2.5 kg");

    Equipment e = new Dumbbell(2.5);
    i.expect("Equipment e = new Dumbbell(2.5);\ne.isInUse() returns false", e.isInUse(), false);

    e.setInUse(true);
    i.expect("e.setInUse(true);\ne.isInUse() returns true", e.isInUse(), true);

    e.setInUse(false);
    i.expect("e.setInUse(false);\ne.isInUse() returns false", e.isInUse(), false);

    e.repair();

    Dumbbell d = new Dumbbell(2.5);
    i.expect("Dumbbell d = new Dumbbell(2.5);\nd.getWeight(); returns 2.5", d.getWeight(), 2.5);

    i.expect("d.getRepairCount(); returns 0", d.getRepairCount(), 0);

    d.repair();
    i.expect("d.repair();\nd.getRepairCount(); returns 1", d.getRepairCount(), 1);

    Treadmill t = new Treadmill();
    t.setSpeed(3.0);
    i.expect("Treadmill t = new Treadmill();\nt.setSpeed(3.0);\nt.getSpeed(); returns 3.0", 
             t.getSpeed(), 3.0);

    i.expect("t; returns \"Treadmill: 3.0 km/h\"", t.toString(), "Treadmill: 3.0 km/h");

    t.repair();
    i.expect("t.repair();\nt.getSpeed(); returns 0.0", t.getSpeed(), 0.0);

    i.expectCompile("Checking e.getWeight() does not compile", 
                    "Equipment e = new Dumbbell(0.5); e.getWeight();", false);

    i.expectCompile("Checking e.setSpeed(4.5) does not compile", 
                    "Equipment e = new Treadmill(); e.setSpeed(4.5);", false);

    i.expectCompile("Checking e.getSpeed() does not compile", 
                    "Equipment e = new Treadmill(); e.getSpeed();", false); 
  }
}