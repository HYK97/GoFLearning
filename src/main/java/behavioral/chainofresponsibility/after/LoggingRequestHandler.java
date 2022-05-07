package behavioral.chainofresponsibility.after;

import behavioral.chainofresponsibility.before.Request;

public class LoggingRequestHandler extends RequestHandler {


    public LoggingRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }


    public void handle(Request request) {
        System.out.println("로깅");
        super.handle(request);
    }
}
