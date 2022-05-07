package behavioral.command.before;

public class MyApp {

    private Game game;

    public MyApp(Game game) {
        this.game = game;
    }

    public static void main(String[] args) {
        Button button = new Button(new Light());
        button.press();
        button.press();
        button.press();
        button.press();
    }

    public void press() {
        game.start();
    }
}
