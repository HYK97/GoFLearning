package behavioral.command.after;


import behavioral.command.before.Game;

/**
 * command 코드는 구체적으로 사용하는 코드를 알고있어야하기때문에 바뀐다.
 */
public class GameStartCommand implements Command {

    private Game game;

    public GameStartCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.start();
    }

    @Override
    public void undo() {
        new GameEndCommand(new Game()).execute();
    }
}
