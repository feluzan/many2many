package io.github.feluzan.many2many;

import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * No mapeamento individual, os objetos da lista following não precisam mapear a sua própria lista following
     * */
    @Mapping(target = "following", qualifiedByName = "toModelListWithoutFollows")
    @Mapping(target = "followedBy", qualifiedByName = "toModelListWithoutFollows")
    User toModel (UserEntity entity);

    @Mapping(target = "following", qualifiedByName = "fromModelListWithoutFollows")
    @Mapping(target = "followedBy", qualifiedByName = "fromModelListWithoutFollows")
    UserEntity fromModel(User user);

    @Named("toModelWithoutFollowing")
    @Mapping(target = "following", ignore = true)
    @Mapping(target = "followedBy", ignore = true)
    User toModelWithoutFollows(UserEntity entity);

    @Named("fromModelWithoutFollowing")
    @Mapping(target = "following", ignore = true)
    @Mapping(target = "followedBy", ignore = true)
    UserEntity fromModelWithoutFollows(User user);


    @Named("toModelListWithoutFollows")
    default List<User> toModelListWithoutFollows(List<UserEntity> entities){
        if(entities==null) return null;
        List<User> list = new ArrayList<>();
        for(UserEntity ue : entities){
            list.add(toModelWithoutFollows(ue));
        }
        return list;
    }

    @Named("fromModelListWithoutFollows")
    default List<UserEntity> fromModelListWithoutFollows(List<User> users){
        if(users==null) return null;
        List<UserEntity> list = new ArrayList<>();
        for(User u : users){
            list.add(fromModelWithoutFollows(u));
        }
        return list;
    }

    /**
     * Mapeamento de list
     * Método de Primeiro nível: é chamado pela classe service
     * Nesse caso, é necessário mapear a lista following dos itens da lista, porém somente no primeiro nível.
     * */
    List<User> toModelList(List<UserEntity> entities);

    List<UserEntity> fromModelList(List<User> users);




}
