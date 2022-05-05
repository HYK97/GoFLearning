package structural.proxy.apply;


import structural.proxy.after.DefaultGameService;
import structural.proxy.after.GameService;

import java.lang.reflect.Proxy;

public class ProxyInJava {

    public static void main(String[] args) {
        ProxyInJava proxyInJava = new ProxyInJava();
        proxyInJava.dynamicProxy();
    }

    private void dynamicProxy() {
        GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
        gameServiceProxy.startGame();
        gameServiceProxy.endGame();
    }


    /**
     * 공통 부가기능은 모든메서드를 오버라이드할 필요없이 reflection을 이용해서 사용하면 간단하게 만들수 있다.
     *
     * @param target
     * @return
     */
    private GameService getGameServiceProxy(GameService target) {
        return (GameService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{GameService.class}, (proxy, method, args) -> {
                    System.out.println("start");
                    method.invoke(target, args);
                    System.out.println("end");
                    return null;
                });
    }
}
