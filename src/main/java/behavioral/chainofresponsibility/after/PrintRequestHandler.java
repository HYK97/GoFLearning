package behavioral.chainofresponsibility.after;

import behavioral.chainofresponsibility.before.Request;

public class PrintRequestHandler extends RequestHandler {


    public PrintRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }


    public void handle(Request request) {
        System.out.println(request.getBody());
        super.handle(request);
    }
}
