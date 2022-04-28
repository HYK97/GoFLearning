package create.prototype.apply;

import java.util.ArrayList;
import java.util.List;

public class JavaCollectionExample {

    public static void main(String[] args) {
        Student student1 = new Student("keesun");
        Student Student2 = new Student("whiteship");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(Student2);

        List<Student> clone = new ArrayList<>(students);
        System.out.println(clone);


    }
}
