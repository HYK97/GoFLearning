package behavioral.chainofresponsibility.apply;


import javax.servlet.*;
import java.io.IOException;

public class InJava {
    public static void main(String[] args) {
        Filter filter = new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                // 전처리
                chain.doFilter(request, response);
                //후처리
            }
        };
    }
}
