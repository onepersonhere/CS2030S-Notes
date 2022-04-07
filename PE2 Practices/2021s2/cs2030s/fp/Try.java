package cs2030s.fp;

/**
 * CS2030S PE2 Question 1.
 * AY20/21 Semester 2
 *
 * @author REDACTED
 */
public abstract class Try<T> {
  private static class Success<T> extends Try<T> {
    private final T value;

    private Success(T value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Success<?>) {
        Success<?> s = (Success<?>) obj;
        return this.value == s.get()
          ? true
          : this.value == null || s.get() == null
          ? false
          : this.value.equals(s.get());
      }
      return this == obj;
    }

    @Override
    public T get() {
      return this.value;
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
    public Try<T> onFailure(Consumer<? super Throwable> consumer) {
      return this;
    }

    @Override
    public Try<T> recover(Transformer<? super Throwable, ? extends T> transformer) {
      return this;
    }
  }

  private static class Failure extends Try<Object> {
    Throwable t;

    private Failure(Throwable t) {
      this.t = t;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Failure)) {
        return false;
      }

      Failure f = (Failure) obj;

      return this.t == f.t || String.valueOf(this.t).equals(String.valueOf(f.t));
    }

    @Override
    public Object get() throws Throwable {
      throw t;
    }

    @Override
    public <U> Try<U> map(Transformer<? super Object, ? extends U> t) {
      @SuppressWarnings("unchecked")
      Try<U> newTry = (Try<U>) this;
      return newTry;
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super Object, ? extends Try<? extends U>> t) {
      @SuppressWarnings("unchecked")
      Try<U> newTry = (Try<U>) this;
      return newTry;
    }

    @Override
    public Try<Object> onFailure(Consumer<? super Throwable> consumer) {
      try {
        consumer.consume(this.t);
        return this;
      } catch (Throwable t) {
        return Try.failure(t);
      }
    }

    @Override
    public Try<Object> recover(Transformer<? super Throwable, ? extends Object> transformer) {
      Object u = transformer.transform(this.t);
      return Try.of(() -> u);
    }
  }

  public static <T> Try<T> of(Producer<? extends T> producer) {
    try {
      T val = producer.produce();
      return success(val);
    } catch (Throwable t) {
      @SuppressWarnings("unchecked")
      Try<T> trf = (Try<T>) failure(t);
      return trf;
    }
  }

  public static <T> Try<T> success(T value) {
    return new Success<>(value);
  }

  public static Try<Object> failure(Throwable throwable) {
    return new Failure(throwable);
  }

  public abstract T get() throws Throwable;

  public abstract <U> Try<U> map(Transformer<? super T, ? extends U> t);

  public abstract <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> t);

  public abstract Try<T> onFailure(Consumer<? super Throwable> consumer);

  public abstract Try<T> recover(Transformer<? super Throwable, ? extends T> transformer);
}

