package io.github.feluzan.many2many;

import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * No mapeamento individual, os objetos da lista friends não precisam mapear a sua própria lista friends
     * */
    @Mapping(target = "friends", ignore=true)
    @Mapping(target = "friendOf", ignore=true)
    @Mapping(target = "friendship", qualifiedByName = "toModelListWithoutFriends")
    User toModel (UserEntity entity);

    @Mapping(target = "friends", qualifiedByName = "fromModelListWithoutFriends")
    @Mapping(target = "friendOf", qualifiedByName = "fromModelListWithoutFriends")
    @Mapping(target = "friendship", ignore = true)
    UserEntity fromModel(User user);

    @Named("toModelWithoutFriends")
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "friendOf", ignore = true)
    @Mapping(target="friendship", ignore = true)
    User toModelWithoutFriends(UserEntity entity);

    @Named("fromModelWithoutFriends")
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "friendOf", ignore = true)
    @Mapping(target="friendship", ignore = true)
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


    /**
     * Antes de mapear de entity para model é necessário construir a lista friendship na entity
     * */
    @BeforeMapping
    default void generateFriendshipOnEntity(@MappingTarget User model,
                                         UserEntity entity) {
        entity.setFriendship(new ArrayList<>());
        entity.getFriendship().addAll(entity.getFriends());
        entity.getFriendship().addAll(entity.getFriendOf());

    }

    /**
     * Antes de mapear de model para entity é necessário desmontar a lista friendship em friends e friendOf no Model
     * */
    @BeforeMapping
    default void unmountFriendshipOnModel(@MappingTarget UserEntity entity, User user) {

        user.setFriends(new ArrayList<>());
        user.setFriendOf(new ArrayList<>());
        if (user.getFriendship() == null)
            return;
        for (int i = 0; i < user.getFriendship().size(); i++) {
            if (user.getFriendship().get(i).getId() > user.getId())
                user.getFriends().add(user.getFriendship().get(i));
            else
                user.getFriendOf().add(user.getFriendship().get(i));

        }
    }



}
