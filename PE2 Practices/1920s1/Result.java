import java.util.function.*;

public abstract class Result<T> {
    private static class Success<T> extends Result<T> {
        T value;

        private Success(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Success: " + String.valueOf(this.value);
        }

        @Override
        public Result<T> filter(Predicate<? super T> predicate, String errorMsg) {
            if (predicate.test(this.value)) {
                return this;
            } else {
                return error(errorMsg);
            }
        }

        @Override
        public <U> Result<?> flatMap(Function<? super T, ? extends Result<? extends U>> f) {
            try {
                return f.apply(this.value);
            } catch (Exception e) {
                return error(e.toString());
            }
        }

        @Override
        public Result<T> orElseThrow(Exception e) throws Exception {
            return this;
        }
    }

    private static class Error<T> extends Result<T> {
        String errorMsg;

        private Error(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        @Override
        public String toString() {
            return "Error: " + this.errorMsg;
        }

        @Override
        public Result<T> filter(Predicate<? super T> predicate, String errorMsg) {
            return this;
        }

        @Override
        public <U> Result<?> flatMap(Function<? super T, ? extends Result<? extends U>> f) {
            return this;
        }

        @Override
        public Result<T> orElseThrow(Exception e) throws Exception {
            throw e;
        }
    }

    public static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    public static <T> Result<T> error(String errorMessage) {
        return new Error<>(errorMessage);
    }

    public abstract Result<T> filter(Predicate<? super T> predicate, String errorMsg);

    public abstract <U> Result<?> flatMap(Function<? super T, ? extends Result<? extends U>> f);

    public abstract Result<T> orElseThrow(Exception e) throws Exception;
}