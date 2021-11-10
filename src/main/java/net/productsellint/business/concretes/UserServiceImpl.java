package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.UserService;
import net.productsellint.dataAccess.abstracts.UserDao;
import net.productsellint.dataTransferObjects.concretes.UserRequest;
import net.productsellint.entities.concretes.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(ModelMapper mapper, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mapper = mapper;
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void save(UserRequest userRequest) {
        UserEntity userEntity = this.mapper.map(userRequest, UserEntity.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userDao.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
