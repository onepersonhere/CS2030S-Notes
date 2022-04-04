class Test3 {
  /**
   * Main method for Test3.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    i.expect("new ArrayStack<>(3).toString() returns \"Stack:\"", 
             new ArrayStack<>(3).toString().trim(), 
             "Stack:");

    Stack<Integer> st = new ArrayStack<>(3);
    st.push(1);
    i.expect("Stack<Integer> st = new ArrayStack<>(3);\nst.push(1);\nst; "
             + "returns \"Stack: 1\"", 
             st.toString().trim(), "Stack: 1");

    st.push(1);
    i.expect("st.push(1);\nst; returns \"Stack: 1 1\"", st.toString().trim(), "Stack: 1 1");

    st.push(2);
    i.expect("st.push(2);\nst; returns \"Stack: 1 1 2\"", st.toString().trim(), "Stack: 1 1 2");

    i.expect("st.getStackSize(); returns 3", st.getStackSize(), 3);

    st.push(3);
    i.expect("st.push(3);\nst; returns \"Stack: 1 1 2\"", st.toString().trim(), "Stack: 1 1 2");

    i.expect("st.pop(); returns 2", st.pop(), 2);
 
    i.expect("st; returns \"Stack: 1 1\"", st.toString().trim(), "Stack: 1 1");

    i.expect("st.pop(); returns 1", st.pop(), 1);

    i.expect("st; returns \"Stack: 1\"", st.toString().trim(), "Stack: 1");

    i.expect("st.pop(); returns 1", st.pop(), 1);

    i.expect("st; returns \"Stack:\"", st.toString().trim(), "Stack:");

    i.expect("st.pop(); returns null", st.pop(), null);

    i.expect("st; returns \"Stack:\"", st.toString().trim(), "Stack:");

    i.expect("st.pop(); returns null", st.pop(), null);

    i.expect("st; returns \"Stack:\"", st.toString().trim(), "Stack:");

    st.push(2);
    i.expect("st.push(2);\nst; returns \"Stack: 2\"", st.toString().trim(), "Stack: 2");

    Stack<String> st2 = new ArrayStack<>(10);
    st2.push("Hello");
    i.expect("Stack<String> st2 = new ArrayStack<>(10);\nst2.push(\"Hello\");\nst2;"
             + " returns \"Stack: Hello\"", 
             st2.toString().trim(), "Stack: Hello");

    st2.push("World");
    i.expect("st2.push(\"World\");\nst2; returns returns \"Stack: Hello World\"", 
             st2.toString().trim(), "Stack: Hello World");

    i.expect("st2.pop(); returns \"World\"", st2.pop(), "World");

    i.expect("st2.pop(); returns \"Hello\"", st2.pop(), "Hello");
   
  }
}