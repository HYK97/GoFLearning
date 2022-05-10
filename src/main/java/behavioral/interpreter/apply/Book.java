package behavioral.interpreter.apply;

public class Book {
    public String name;

    public Book(String name) {
        this.name = name;
    }

    public String testMethod() {
        return "호출성공";
    }
}
