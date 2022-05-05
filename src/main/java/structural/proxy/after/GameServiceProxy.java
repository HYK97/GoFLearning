package structural.proxy.after;

/**
 * 인터페이스가 있는 경우(Proxy)
 */
public class GameServiceProxy implements GameService {

    private GameService gameService;

    public GameServiceProxy(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();
        gameService.startGame();
        System.out.println("시간 = " + (System.currentTimeMillis() - before));
    }

    @Override
    public void endGame() {
        long before = System.currentTimeMillis();
        gameService.endGame();
        System.out.println("시간 = " + (System.currentTimeMillis() - before));
    }

    /**
     * Lazy initialization( 지연 초기화 )
     * 이때는 생성자가 없어야댐
     * */
    /*@Override
    public void startGame() {
        long before = System.currentTimeMillis();
        if (this.gameService == null) {
            this.gameService =new DefaultGameService();
        }
        gameService.startGame();
        System.out.println("시간 = " + (System.currentTimeMillis() - before));
    }*/
}
