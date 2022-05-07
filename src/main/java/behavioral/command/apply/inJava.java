package behavioral.command.apply;

import behavioral.command.before.Game;
import behavioral.command.before.Light;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * runnable 이 여기서 command interFace
 */
public class inJava {
    public static void main(String[] args) {
        Light light = new Light();
        Game game = new Game();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        /**
         * runnable 이 여기서 command interFace
         * functional InterFace
         * */
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService.submit(light::off); //Method Reference
        executorService.submit(() -> light.on());// Lambda Expressions
        executorService.shutdown();
    }
}
