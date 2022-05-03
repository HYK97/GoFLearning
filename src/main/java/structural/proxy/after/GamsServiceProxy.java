package structural.proxy.after;

import structural.proxy.before.GameService;


/**
 * 인터페이스가 없는경우
 */
public class GamsServiceProxy extends GameService {

    @Override
    public void startGame() throws InterruptedException {
        long before = System.currentTimeMillis();
        super.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}
