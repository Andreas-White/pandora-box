package pattern_matching;

import java.util.Objects;

public class PatternMatchingDemo {

    private sealed interface Animal permits Dog, Cat{
        int weight();
        String sound();
    }

    private record Dog(int age, String name) implements Animal {
        @Override
        public int weight() {
            return 10 * age();
        }

        @Override
        public String sound() {
            return "Woof";
        }

        @Override
        public String toString() {
            return String.format("Dog [age=%d, name=%s] has weight %s and it makes a sound as %s", age(), name, weight(), sound());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dog dog = (Dog) o;
            return age == dog.age && Objects.equals(name, dog.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }

    private record Cat(int age, String name) implements Animal {

        public void random() {
            System.out.println("random");
        }

        @Override
        public int weight() {
            return 5 * age();
        }

        @Override
        public String sound() {
            return "Meow";
        }

        @Override
        public String toString() {
            return String.format("Cat [age=%d, name=%s] has weight %s and it makes a sound as %s", age(), name, weight(), sound());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return age == cat.age && Objects.equals(name, cat.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }

    public static void main(String[] args) {
        Animal dog = new Dog(6, "Dog");

        String sound = switch (dog) {
            case Dog d when d.weight() > 50 -> String.format("Heavy %s", d.sound());
            case Dog d -> d.sound();
            case Cat c -> c.sound();
        };

        System.out.println(sound);
    }
}
