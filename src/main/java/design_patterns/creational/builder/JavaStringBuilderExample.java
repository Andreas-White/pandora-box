package design_patterns.creational.builder;

public class JavaStringBuilderExample {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello");
        stringBuilder.append(" ");
        stringBuilder.append("World");
        stringBuilder.append("!");
        stringBuilder.append("!");
        System.out.println(stringBuilder);
    }
}
