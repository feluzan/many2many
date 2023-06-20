package io.github.feluzan.many2many;

import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * No mapeamento individual, os objetos da lista friends não precisam mapear a sua própria lista friends
     * */
    @Mapping(target = "friends", qualifiedByName = "toModelListWithoutFriends")
    @Mapping(target = "friendOf", qualifiedByName = "toModelListWithoutFriends")
    User toModel (UserEntity entity);

    @Mapping(target = "friends", qualifiedByName = "fromModelListWithoutFriends")
    @Mapping(target = "friendOf", qualifiedByName = "fromModelListWithoutFriends")
    UserEntity fromModel(User user);

    @Named("toModelWithoutFriends")
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "friendOf", ignore = true)
    User toModelWithoutFriends(UserEntity entity);

    @Named("fromModelWithoutFriends")
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "friendOf", ignore = true)
    UserEntity fromModelWithoutFriends(User user);


    @Named("toModelListWithoutFriends")
    default List<User> toModelListWithoutFriends(List<UserEntity> entities){
        if(entities==null) return null;
        List<User> list = new ArrayList<>();
        for(UserEntity ue : entities){
            list.add(toModelWithoutFriends(ue));
        }
        return list;
    }

    @Named("fromModelListWithoutFriends")
    default List<UserEntity> fromModelWithoutFriends(List<User> users){
        if(users==null) return null;
        List<UserEntity> list = new ArrayList<>();
        for(User u : users){
            list.add(fromModelWithoutFriends(u));
        }
        return list;
    }

    List<User> toModelList(List<UserEntity> entities);

    List<UserEntity> fromModelList(List<User> users);




}
