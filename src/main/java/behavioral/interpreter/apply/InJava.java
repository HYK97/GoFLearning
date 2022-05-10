package behavioral.interpreter.apply;

import java.util.regex.Pattern;

public class InJava {

    public static void main(String[] args) {
        System.out.println(Pattern.matches(".pr...", "spring"));
        System.out.println(Pattern.matches("[a-z]{6}", "spring"));
        System.out.println(Pattern.matches("kkk[a-z]{6}[0-9]{4}", "kkkspring1234"));

    }
}
