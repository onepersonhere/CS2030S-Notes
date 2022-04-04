/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
public class ArrayStack<T> implements Stack<T> {
  private T[] arr;
  private int maxDepth;
  private int top = -1;

  public ArrayStack(int maxDepth) {
    this.maxDepth = maxDepth;

    //the only way we can put an object into the array is through the method push
    //we only put objects of T inside.
    //So it is safe to cast Object[] to T[].
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Object[maxDepth];
    this.arr = a;
  }

  @Override
  public T pop() {
    if (top >= 0) {
      T element = arr[this.top];
      this.top--;
      return element;
    } else {
      return null;
    }
  }

  @Override
  public void push(T t) {
    if (top <= maxDepth - 2) {
      this.top++;
      arr[this.top] = t;  
    }
  }

  @Override
  public int getStackSize() {
    return this.top + 1;
  }

  @Override
  public String toString() {
    String str = "Stack: ";
    for (int i = 0; i <= top; i++) {
      str += arr[i] + " ";
    }
    return str;
  }

  public static <T> ArrayStack<T> of(T[] arr, int maxDepth) {
    ArrayStack<T> newStack = new ArrayStack<>(maxDepth);
    
    for (int i = 0; i < Math.min(maxDepth, arr.length); i++) {
      newStack.push(arr[i]);
    }

    return newStack;
  }

  public void pushAll(ArrayStack<? extends T> stack) {
    for (int i = 0; i < stack.getStackSize(); i++) {
      this.push(stack.pop());
    }
  }

  public void popAll(ArrayStack<? super T> stack) {
    for (int i = 0; i < this.getStackSize(); i++) {
      stack.push(this.pop());
    }
  }
}
