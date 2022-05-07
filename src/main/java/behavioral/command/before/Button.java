package behavioral.command.before;

public class Button {

    private Light light;

    public Button(Light light) {
        this.light = light;
    }

    public static void main(String[] args) {
        Button button = new Button(new Light());
        button.press();
        button.press();
        button.press();
        button.press();
    }

    public void press() {
        light.off();
    }
}
