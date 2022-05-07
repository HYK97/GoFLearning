package behavioral.chainofresponsibility.after;

import behavioral.chainofresponsibility.before.Request;

public class AuthRequestHandler extends RequestHandler {


    public AuthRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }


    public void handle(Request request) {
        System.out.println("인증");
        super.handle(request);
    }
}
