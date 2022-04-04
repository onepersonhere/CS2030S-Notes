class Test4 {
  /**
   * Main method for Test4.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    i.expect("ArrayStack.of(new Integer[] {1, 2, 3}, 10); \nreturns \"Stack: 1 2 3\"", 
             ArrayStack.of(new Integer[] {1, 2, 3}, 10).toString().trim(), "Stack: 1 2 3");

    i.expect("ArrayStack.of(new Object[] {1, \"foo\", \"bar\"n}, 10); \n"
             + "returns \"Stack: 1, foo, bar\"", 
             ArrayStack.of(new Object[] {1, "foo", "bar"}, 10).toString().trim(), 
            "Stack: 1 foo bar");
    
    ArrayStack<Integer> as0 = ArrayStack.of(new Integer[] {1, 2, 3, 4}, 2);
    ArrayStack<Integer> as1 = ArrayStack.of(new Integer[] {4, 5, 6}, 10);
    ArrayStack<Integer> as2 = ArrayStack.of(new Integer[] {1, 2, 3}, 10);
    i.expect("ArrayStack<Integer> as0 = ArrayStack.of(new Integer[] {1, 2, 3, 4}, 2);\n"
             + "as0; returns \"Stack: 1 2\"",
              as0.toString().trim(), "Stack: 1 2");
    i.expect("ArrayStack<Integer> as1 = ArrayStack.of(new Integer[] {4, 5, 6}, 10);\n"
             + "as1; returns \"Stack: 4 5 6\"",
              as1.toString().trim(), "Stack: 4 5 6");
    i.expect("ArrayStack<Integer> as2 = ArrayStack.of(new Integer[] {1, 2, 3}, 10);\n"
             + "as2; returns \"Stack: 1 2 3\"",
              as2.toString().trim(), "Stack: 1 2 3");

    as2.pushAll(as1);
    i.expect("as2.pushAll(as1);\nas2; returns \"Stack: 1 2 3 6 5 4\"", as2.toString().trim(), 
             "Stack: 1 2 3 6 5 4");

    i.expect("as1; returns \"Stack:\"", as1.toString().trim(), "Stack:");

    as1 = ArrayStack.of(new Integer[] {4, 5, 6}, 10);
    ArrayStack<Integer> as3 = ArrayStack.of(new Integer[] {1, 2, 3}, 5);
    i.expect("as1 = ArrayStack.of(new Integer[] {4, 5, 6}, 10);\n"
             + "ArrayStack<Integer> as3 = ArrayStack.of(new Integer[] {1, 2, 3}, 5);\n"
             + "as3; returns \"Stack: 1 2 3\"",
              as3.toString().trim(), "Stack: 1 2 3");

    as3.pushAll(as1);
    i.expect("as3.pushAll(as1);\nas3; returns \"Stack: 1 2 3 6 5\"", as3.toString().trim(), 
             "Stack: 1 2 3 6 5");   
    
    ArrayStack<Number> asn = new ArrayStack<>(10);
    asn.pushAll(as2);

    i.expect("ArrayStack<Number> asn = new ArrayStack<>(10);\nasn.pushAll(as2);\n"
             + "asn; returns \"Stack: 4 5 6 3 2 1\"",
              asn.toString().trim(), "Stack: 4 5 6 3 2 1");


    ArrayStack<String> as4 = ArrayStack.of(new String[] {"d", "e", "f"}, 10);
    ArrayStack<String> as5 = ArrayStack.of(new String[] {"a", "b", "c"}, 10);
    i.expect("ArrayStack<String> as4 = ArrayStack.of(new String[] "
             + "{\"d\", \"e\", \"f\"}, 10);\nas4; returns \"Stack: d e f\"",
              as4.toString().trim(), "Stack: d e f");
    i.expect("ArrayStack<String> as5 = ArrayStack.of(new String[] "
             + "{\"a\", \"b\", \"c\"}, 10);\nas5; returns \"Stack: a b c\"",
              as5.toString().trim(), "Stack: a b c");

    as4.popAll(as5);
    i.expect("as4.popAll(as5);\nas5; returns \"Stack: a b c f e d\"", as5.toString().trim(), 
             "Stack: a b c f e d");

    as4 = ArrayStack.of(new String[] {"d", "e", "f"}, 10);
    ArrayStack<String> as6 = ArrayStack.of(new String[] {"a", "b", "c"}, 5);
    i.expect("as4 = ArrayStack.of(new String[] {\"d\", \"e\", \"f\"}, 10);\n"
             + "ArrayStack<String> as6 = ArrayStack.of(new String[] {\"a\", \"b\", \"c\"}, 5);"
             + "\nas6; returns \"Stack: a b c\"",
              as6.toString().trim(), "Stack: a b c");

    as4.popAll(as6);
    i.expect("as4.popAll(as6);\nas6; returns \"Stack: a b c f e\"", as6.toString().trim(), 
             "Stack: a b c f e");

    ArrayStack<Integer> as7 = ArrayStack.of(new Integer[] {7, 8, 9}, 5);
    i.expect("ArrayStack<Integer> as7 = ArrayStack.of(new Integer[] {7, 8, 9}, 5);\n"
             + "as7; returns \"Stack: 7 8 9\"",
              as7.toString().trim(), "Stack: 7 8 9");
    as7.popAll(asn);
    i.expect("as7.popAll(asn);\nasn; returns \"Stack: 4 5 6 3 2 1 9 8 7\"", asn.toString().trim(), 
             "Stack: 4 5 6 3 2 1 9 8 7");   

  }
}