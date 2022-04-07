import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    String pattern(int n) {
        return Stream.iterate(String.valueOf(n), x -> String.valueOf(Integer.parseInt(x) - 1))
                .limit(n)
                .flatMap(x -> Stream
                        .iterate(String.valueOf(n), y -> String.valueOf(Integer.parseInt(y) - 1))
                        .map(y -> Integer.parseInt(y) < Integer.parseInt(x) ? y : ".")
                        .limit(n))
                .reduce((x, y) -> x + y)
                .orElse("");
    }

    static class IFL<T> {
        Supplier<T> head;
        Supplier<IFL<T>> tail;

        // Fill in any additional fields and methods (if any) in the
        // answer box for Question 2 on Examplify
        IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
            IFL.this.head = head;
            IFL.this.tail = tail;
        }

        static <T> IFL<T> of(List<? extends T> list) {
            // Fill in the body of this method (and only the body)
            // in the answer box for Question 4 on Examplify

            List<T> newList = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                newList.add(list.get(i));
            }
            if (list.size() < 1) {
                return new IFL<>(null, null);
            }
            return new IFL<>(() -> list.get(0), () -> IFL.of(newList));
        }

        Optional<T> findMatch(Predicate<? super T> predicate) {
            // Fill in the body of this method (and only the body)
            // in the answer box for Question 5 on Examplify
            return Optional.ofNullable(IFL.this.head).map(x -> predicate.test(x.get())
                    ? IFL.this.head.get()
                    : IFL.this.tail.get().findMatch(predicate).orElse(null)
            );
        }
    }

    public static void main(String[] args) {
        IFL<String> list = IFL.of(Arrays.asList("three", "little", "pigs"));
        list.findMatch(str -> str.length() == 6);
        list.findMatch(str -> str.length() < 4);
    }
}
