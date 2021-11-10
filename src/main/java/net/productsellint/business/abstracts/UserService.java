package net.productsellint.business.abstracts;

import net.productsellint.dataTransferObjects.concretes.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    void save(UserRequest userRequest);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void delete(Integer id);
    void disable(Integer id);
    void activate(Integer id);
}
