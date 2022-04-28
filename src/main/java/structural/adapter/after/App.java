package structural.adapter.after;

import structural.adapter.after.security.LoginHandler;
import structural.adapter.after.security.UserDetailsService;

public class App {
    public static void main(String[] args) {
        UserDetailsService userDetailsService = new AccountUserDetailsService(new AccountService());
        LoginHandler loginHandler = new LoginHandler(userDetailsService);
        String login = loginHandler.login("hy", "hy");
        System.out.println("login = " + login);


    }
}
