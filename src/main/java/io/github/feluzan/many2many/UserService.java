package io.github.feluzan.many2many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<User> listAll() {
        List<UserEntity> entityList = userRepository.findAll();
        return userMapper.toModelList(entityList);
    }

    public User save(User user){
        UserEntity entity = userMapper.fromModel(user);
        entity = userRepository.save(entity);
        return userMapper.toModel(entity);

    }

    public User findById(Long id){
        return userMapper.toModel(userRepository.findById(id).orElse(null));
    }

    public void addFriends(User friend1, User friend2){
        friend1.addFriends(friend2);
        userRepository.save(userMapper.fromModel(friend1));
        friend2.addFriends(friend1);
        userRepository.save(userMapper.fromModel(friend2));
    }
}
