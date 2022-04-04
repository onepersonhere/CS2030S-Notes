/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author REDACTED
 */
public interface Stack<T> { 
  T pop();

  void push(T t);
  
  int getStackSize();
}
