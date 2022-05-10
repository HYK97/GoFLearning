package behavioral.chain_of_responsibility.after;

import behavioral.chain_of_responsibility.before.Request;

public class PrintRequestHandler extends RequestHandler {


    public PrintRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }


    public void handle(Request request) {
        System.out.println(request.getBody());
        super.handle(request);
    }
}
