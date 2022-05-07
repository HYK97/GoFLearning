package behavioral.command.after;

import java.util.Stack;

/**
 * invoker -> 코드는 바뀌지 않음
 */
public class Button {
    private Command command;

    private Stack<Command> commands = new Stack<Command>();

    public Button() {


    }

    public void undo() {
        if (!commands.isEmpty()) {
            commands.pop().undo();
        }
    }

    public void press(Command command) {
        command.execute();
        commands.push(command);
    }
}
