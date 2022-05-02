package structural.flyweight.after;

/**
 * 자주 바뀌는 속성
 */
public class Character {

    private char value;
    private String color;
    private Font font;

    public Character(char value, String color, Font font) {
        this.value = value;
        this.color = color;
        this.font = font;
    }
}
