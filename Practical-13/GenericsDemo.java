class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericsDemo {
    public static <T> T first(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        return array[0];
    }

    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.set(100);

        System.out.println("Box value: " + box.get());

        Pair<String, Integer> pair =
                new Pair<>("Age", 21);

        System.out.println("First: " + pair.getFirst());
        System.out.println("Second: " + pair.getSecond());

        String[] names = {"Amit", "Neha", "Rahul"};

        System.out.println("First element: " + first(names));

        Integer[] numbers = {10, 20, 30};

        System.out.println("First number: " + first(numbers));
    }
}
