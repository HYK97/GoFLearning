package structural.proxy.apply;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    public void startGame() {
        System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
    }

    public void endGame() {
        System.out.println("안녕히 가세요");
    }


}
