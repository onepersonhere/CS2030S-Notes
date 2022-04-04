package cs2030s.fp;
/*
 * CS2030S PE2 Question 1
 * AY20/21 Semester 2
 *
 * @author zhongfu
 */
public abstract class Try<T> {
  public static <T> Try<T> of(Producer<? extends T> p) {
    try {
      return Try.success(p.produce());
    } catch (Throwable t) {
      return Try.failure(t);
    }
  }

  public static <T> Try<T> success(T val) {
    return new Success<>(val);
  }

  public static <T> Try<T> failure(Throwable t) {
    // this is ok because Failure implements every method that Try implements
    // and the type means nothing in the context of Failure;
    // we're not using the type var T in any way
    @SuppressWarnings("unchecked")
    Try<T> failure = (Try<T>) new Failure(t);
    return failure;
  }

  public abstract T get() throws Throwable;

  public abstract <U> Try<U> map(Transformer<? super T, ? extends U> t);

  public abstract <U> Try<U> flatMap(
      Transformer<? super T, ? extends Try<? extends U>> t);

  public abstract Try<T> onFailure(Consumer<? super Throwable> c);

  public abstract Try<T> recover(Transformer<? super Throwable, ? extends T> t);

  private static class Success<T> extends Try<T> {
    T val;

    private Success(T val) {
      this.val = val;
    }

    @Override
    public T get() throws Throwable {
      return this.val;
    }

    @Override
    public <U> Try<U> map(Transformer<? super T, ? extends U> t) {
      return Try.of(() -> t.transform(this.get()));
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> t) {
      return Try.of(() -> t.transform(this.get()).get());
    }

    @Override
    public Try<T> onFailure(Consumer<? super Throwable> c) {
      return this;
    }

    @Override
    public Try<T> recover(Transformer<? super Throwable, ? extends T> t) {
      return this;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (!(o instanceof Success<?>)) {
        return false;
      }

      Success<?> s = (Success<?>) o;

      return this.val == s.val || this.val != null && this.val.equals(s.val);
    }
  }

  private static class Failure extends Try<Object> {
    Throwable err;

    private Failure(Throwable err) {
      this.err = err;
    }

    @Override
    public Object get() throws Throwable {
      throw err;
    }

    @Override
    public <U> Try<U> map(Transformer<? super Object, ? extends U> t) {
      return Try.failure(this.err);
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super Object, ? extends Try<? extends U>> t) {
      return Try.failure(this.err);
    }

    @Override
    public Try<Object> onFailure(Consumer<? super Throwable> c) {
      try {
        c.consume(this.err);
        return this;
      } catch (Throwable t) {
        return Try.failure(t);
      }
    }

    @Override
    public Try<Object> recover(Transformer<? super Throwable, ? extends Object> t) {
      return Try.of(() -> t.transform(this.err));
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (!(o instanceof Failure)) {
        return false;
      }

      Failure f = (Failure) o;

      return this.err == f.err || String.valueOf(this.err).equals(String.valueOf(f.err));
    }
  }
}
