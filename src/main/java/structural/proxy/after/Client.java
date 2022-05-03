package structural.proxy.after;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        GameService gameService = new GameServiceProxy(new DefaultGameService());
        gameService.startGame();
    }
}
