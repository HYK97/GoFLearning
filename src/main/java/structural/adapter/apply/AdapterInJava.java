package structural.adapter.apply;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class AdapterInJava {
    public static void main(String[] args) {


        //collections
        // 배열 -> list로 바꿔주는 Adapter
        List<String> strings = Arrays.asList("1", "2", "3");
        //-> list- >Enumeration 변경
        //Enumeration<String> enumeration ->이부분이 target =Collections.enumeration-> 이부분은 adapter(strings-> 이부분은 adaptee)
        Enumeration<String> enumeration = Collections.enumeration(strings);

        //io
        try (InputStream is = new FileInputStream("input.txt");
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr);) {
            while (reader.ready()) {
                System.out.println("reader = " + reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;


    }
}
