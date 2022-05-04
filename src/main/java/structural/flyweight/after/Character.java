package structural.flyweight.after;

import lombok.ToString;

/**
 * 자주 바뀌는 속성
 */
@ToString
public class Character {

    private char value;
    private String color;
    private Element font;

    public Character(char value, String color, Element font) {
        this.value = value;
        this.color = color;
        this.font = font;
    }
}
