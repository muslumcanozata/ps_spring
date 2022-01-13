package net.productsellint.business.abstracts;

import net.productsellint.dataTransferObjects.concretes.UserDto;
import net.productsellint.dataTransferObjects.concretes.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    void save(UserRequest userRequest);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void delete(Integer id);
    void disable(Integer id);
    void activate(Integer id);

    UserDto getUser(String username);

    List<UserDto> getUsers();
    void saveRole(String roleName);
    void addRoleToUser(String username, String roleName);

}
