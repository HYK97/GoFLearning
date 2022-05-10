package behavioral.chain_of_responsibility.after;

import behavioral.chain_of_responsibility.before.Request;

public class Client {
    private RequestHandler requestHandler;

    public Client(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    //요청 받는쪽 - > 클라이언트 코드아님
    public static void main(String[] args) {//받는쪽
        RequestHandler handler = new AuthRequestHandler(new LoggingRequestHandler(new PrintRequestHandler(null)));
        Client client = new Client(handler);
        client.doWork();
    }

    //요청 하는쪽 -> 실제 클라이언트
    public void doWork() {//
        Request request = new Request("Request Body");
        requestHandler.handle(request);
    }

}
