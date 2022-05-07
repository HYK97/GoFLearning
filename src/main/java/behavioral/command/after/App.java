package behavioral.command.after;

import behavioral.command.before.Game;
import behavioral.command.before.Light;

public class App {

    public static void main(String[] args) {
        Button button = new Button();
        button.press(new LightOnCommand(new Light()));
        button.press(new GameStartCommand(new Game()));
        button.undo();
        button.undo();

    }

}
