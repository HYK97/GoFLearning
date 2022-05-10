package behavioral.chain_of_responsibility.after;

import behavioral.chain_of_responsibility.before.Request;

public class AuthRequestHandler extends RequestHandler {


    public AuthRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }


    public void handle(Request request) {
        System.out.println("인증");
        super.handle(request);
    }
}
