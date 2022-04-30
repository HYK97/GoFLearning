package structural.decorator.apply;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class inJava {

    public static void main(String[] args) {
        //Collections 데코레이터 메소드
        ArrayList list = new ArrayList<>();
        list.add(new Book());

        List books = Collections.checkedList(list, Book.class);
        list.add(new Item());//book이외에 저장 불가능 casting 오류
        books.add(new Item());
        List list1 = Collections.synchronizedList(books);//동기화
        List list2 = Collections.unmodifiableList(books);//불변객체
        list2.add(new Book()); // 불변객체가 불가능


    }

    private static class Book {
    }

    private static class Item {
    }
}
