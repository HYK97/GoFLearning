package structural.proxy.after;

public class DefaultGameService implements GameService {
    @Override
    public void startGame() {
        System.out.println("이 자리에 오신 여러분들을 환영합니다.");
    }
}
