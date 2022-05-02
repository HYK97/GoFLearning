package structural.flyweight.apply;

public class FlyWeightInJava {
    public static void main(String[] args) {
        Integer i1 = Integer.valueOf(10000);
        Integer i2 = Integer.valueOf(10000);
        System.out.println(i1.equals(i2));
    }
}
