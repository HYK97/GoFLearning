package structural.proxy.after;

/**
 * RealSubject
 */
public class DefaultGameService implements GameService {

    @Override
    public void startGame() {
        System.out.println("이 자리에 오신 여러분들을 환영합니다.");
    }

    public void endGame() {
        System.out.println("안녕히 가세요");
    }

}
