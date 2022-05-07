package behavioral.chainofresponsibility.before;

public class Client {

    public static void main(String[] args) {
        Request request = new Request("무궁화 꽃이 피었습니다.");
        /**
         * 사용자가 사용해야할 핸들러를 알아야하는 점이 문제
         * */
        RequestHandler requestHandler = new AuthRequestHandler();
        requestHandler.handler(request);
    }
}
