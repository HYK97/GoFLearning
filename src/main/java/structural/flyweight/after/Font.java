package structural.flyweight.after;


import lombok.Getter;
import lombok.ToString;

/**
 * flyWeight Concrete 클래스
 * 불변객체여야함
 */
@Getter
@ToString
public final class Font implements Element{

    // 모든 필드를 final로 생성해야댐

    final String family;

    final int size;

    public Font(String family, int size) {
        this.family = family;
        this.size = size;
    }


    @Override
    public void getInformation() {
         System.out.println("FontFamily : "+ family + ", size : "+ size);
    }
}
