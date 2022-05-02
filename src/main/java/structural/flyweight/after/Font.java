package structural.flyweight.after;


import lombok.Getter;

/**
 * flyWeight 클래스
 * 불변객체여야함
 */
@Getter
public final class Font {

    // 모든 필드를 final로 생성해야댐

    final String family;

    final int size;

    public Font(String family, int size) {
        this.family = family;
        this.size = size;
    }

}
