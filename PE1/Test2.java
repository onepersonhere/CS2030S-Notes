import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class Test2 {
  /**
   * Main method for Test2.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    i.expect("new Customer(\"Bob\").toString() returns \"Customer: Bob\"", 
             new Customer("Bob").toString(), 
             "Customer: Bob");

    i.expect("new Customer(\"Sally\").toString() returns \"Customer: Sally\"", 
             new Customer("Sally").toString(), 
             "Customer: Sally");

    i.expect("new Trainer(\"Frank\").toString() returns \"Trainer: Frank\"", 
             new Trainer("Frank").toString(), 
             "Trainer: Frank");

    Trainer t1 = new Trainer("Frank");
    i.expect("Trainer t1 = new Trainer(\"Frank\");\nt1.getStatus(); "
             + "returns \"Trainer: Frank not training\"", t1.getStatus(), 
             "Trainer: Frank not training");

    Trainer t2 = new Trainer("Sam");
    i.expect("Trainer t2 = new Trainer(\"Sam\");\nt2.getStatus(); "
             + "returns \"Trainer: Sam not training\"", t2.getStatus(), 
             "Trainer: Sam not training");

    i.expect("Test new CannotTrainException(); exception", (new CannotTrainException()).toString(), 
             "CannotTrainException: Cannot Train!");

    i.expectCompile("Testing for Checked Exception\nCheck RuntimeException e = new "
                    + "CannotTrainException(); does not compile", 
                    "RuntimeException e = new CannotTrainException();", false);

    Customer c1 = new Customer("Bob");
    Customer c2 = new Customer("Sally");
    Treadmill treadmill1 = new Treadmill();
    Treadmill treadmill2 = new Treadmill();
    boolean ok = false;
    try {
      t1.startTraining(c1, treadmill1);
    } catch (CannotTrainException e) {
      ok = (e instanceof CannotTrainException);
    }
    i.expect("Customer c1 = new Customer(\"Bob\");\n"
             + "Customer c2 = new Customer(\"Sally\")\n"
             + "Treadmill treadmill1 = new Treadmill();\n" 
             + "Treadmill treadmill2 = new Treadmill();\n" 
             + "t1.startTraining(c1, treadmill1);\nCheck CannotTrainException is not thrown",
             ok, false);

    i.expect("t1.getStatus(); returns \"Trainer: Frank training Customer: Bob\"",  
            t1.getStatus(), "Trainer: Frank training Customer: Bob");

    ok = false;
    try {
      t1.startTraining(c2, treadmill1);
    } catch (Exception e) {
      ok = (e instanceof CannotTrainException);
    }
    i.expect("t1.startTraining(c2, treadmill1);\nCheck CannotTrainException is thrown",
        ok, true);

    i.expect("t1.getStatus(); returns \"Trainer: Frank training Customer: Bob\"", t1.getStatus(), 
             "Trainer: Frank training Customer: Bob");

    t1.stopTraining();
    i.expect("t1.stopTraining();\nt1.getStatus(); "
             + "returns \"Trainer: Frank not training\"", t1.getStatus(), 
             "Trainer: Frank not training");

    ok = false;
    try {
      t1.startTraining(c2, treadmill1);
    } catch (CannotTrainException e) {
      ok = (e instanceof CannotTrainException);
    }

    i.expect("t1.startTraining(c2, treadmill1);\nCheck CannotTrainException is not thrown",
              ok, false);
    i.expect("t1.getStatus(); returns \"Trainer: Frank training Customer: Sally\"",  
            t1.getStatus(), "Trainer: Frank training Customer: Sally");


    ok = false;
    try {
      t1.startTraining(c1, treadmill2);
    } catch (Exception e) {
      ok = (e instanceof CannotTrainException);
    }
    i.expect("t1.startTraining(c1, treadmill2);\nCheck CannotTrainException is thrown",
        ok, true);
    i.expect("t1.getStatus(); returns \"Trainer: Frank training Customer: Sally\"",  
        t1.getStatus(), "Trainer: Frank training Customer: Sally");
          

    ok = false;
    try {
      t2.startTraining(c2, treadmill2);
    } catch (CannotTrainException e) {
      ok = (e instanceof CannotTrainException);
    }

    i.expect("t2.startTraining(c2, treadmill2);\nCheck CannotTrainException is not thrown",
              ok, false);
    i.expect("t1.getStatus(); returns \"Trainer: Sam training Customer: Sally\"",  
            t2.getStatus(), "Trainer: Sam training Customer: Sally");

    t1.stopTraining();
    i.expect("t1.stopTraining();;\nt1.getStatus(); returns \"Trainer: Frank not training\"", 
             t1.getStatus(), "Trainer: Frank not training");

    t2.stopTraining();
    i.expect("t2.stopTraining();;\nt2.getStatus(); returns \"Trainer: Sam not training\"", 
             t2.getStatus(), "Trainer: Sam not training");
   




    Gym gym = new Gym(2);
    i.expect("Gym gym = new Gym(2);\ngym; returns \"Gym Capacity: 0/2\"", 
             gym.toString(), "Gym Capacity: 0/2");

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(old);
    System.setOut(ps);
    gym.enter(c1);
    System.out.flush();
    System.setOut(old);
    i.expect("Checking gym.enter(c1); prints out Customer: Bob can enter", 
             baos.toString().trim(), "Customer: Bob can enter");
    i.expect("gym; returns \"Gym Capacity: 1/2\"", gym.toString(), "Gym Capacity: 1/2");

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    gym.enter(t1);
    System.out.flush();
    System.setOut(old);
    i.expect("Checking gym.enter(t1); prints out Trainer: Frank can enter", 
             baos.toString().trim(), "Trainer: Frank can enter");
    i.expect("gym; returns \"Gym Capacity: 2/2\"", gym.toString(), "Gym Capacity: 2/2");

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    gym.enter(c2);
    System.out.flush();
    System.setOut(old);
    i.expect("Checking gym.enter(c2); prints out Customer: Sally cannot enter", 
             baos.toString().trim(), "Customer: Sally cannot enter");

    i.expect("gym; returns \"Gym Capacity: 2/2\"", gym.toString(), "Gym Capacity: 2/2");
  }
}
