package structural.adapter.after.security;

public interface UserDetailsService {

    UserDetails loadUser(String username);

}
