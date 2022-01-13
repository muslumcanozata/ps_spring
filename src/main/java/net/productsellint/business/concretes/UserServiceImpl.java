package net.productsellint.business.concretes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.productsellint.business.abstracts.UserService;
import net.productsellint.dataAccess.abstracts.RoleDao;
import net.productsellint.dataAccess.abstracts.UserDao;
import net.productsellint.dataTransferObjects.concretes.UserDto;
import net.productsellint.dataTransferObjects.concretes.UserRequest;
import net.productsellint.entities.concretes.Role;
import net.productsellint.entities.concretes.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final ModelMapper mapper;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void save(UserRequest userRequest) {
        log.info("saving new user to the database");
        UserEntity userEntity = this.mapper.map(userRequest, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        this.userDao.save(userEntity);
    }

    @Override
    public void delete(Integer id) {
        this.userDao.delete(id);
    }

    @Override
    public void disable(Integer id) {
        this.userDao.disable(id);
    }

    @Override
    public void activate(Integer id) {
        this.userDao.activate(id);
    }

    @Override
    public UserDto getUser(String username) {
        log.info("getting {} user to the database", username);
        UserEntity userEntity = userDao.findByUsername(username);
        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("fetching all users");
        return userDao.findAll().stream().map(userEntity -> mapper.map(userEntity, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding {} role to the {} user", roleName, username);
        UserEntity userEntity = userDao.findByUsername(username);
        Role role = roleDao.findByName(roleName);
        userEntity.getRoles().add(role);
    }

    @Override
    public void saveRole(String roleName) {
        log.info("saving {} role to the db", roleName);
        roleDao.save( new Role(null, roleName));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userDao.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
