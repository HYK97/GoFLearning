package structural.adapter.before.security;

public class LoginHandler { //클라이언트가 됌

    UserDetailsService userDetailsService; //타겟 인터페이스

    public LoginHandler(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUser(username);//타겟 인터페이스
        if (userDetails.getPassword().equals(password)) {
            return userDetails.getUsername();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
