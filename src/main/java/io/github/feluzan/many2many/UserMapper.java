package io.github.feluzan.many2many;

import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * No mapeamento individual, os objetos da lista following não precisam mapear a sua própria lista following
     * */
    @Mapping(target = "following", qualifiedByName = "toModelListWithoutFollowing")
    User toModel (UserEntity entity);

    @Mapping(target = "following", qualifiedByName = "fromModelListWithoutFollowing")
    UserEntity fromModel(User user);

    @Named("toModelWithoutFollowing")
    @Mapping(target = "following", ignore = true)
    User toModelWithoutFollowing(UserEntity entity);

    @Named("fromModelWithoutFollowing")
    @Mapping(target = "following", ignore = true)
    UserEntity fromModelWithoutFollowing(User user);


    @Named("toModelListWithoutFollowing")
    default List<User> toModelListWithoutFollowing(List<UserEntity> entities){
        if(entities==null) return null;
        List<User> list = new ArrayList<>();
        for(UserEntity ue : entities){
            list.add(toModelWithoutFollowing(ue));
        }
        return list;
    }

    @Named("fromModelListWithoutFollowing")
    default List<UserEntity> fromModelListWithoutFollowing(List<User> users){
        if(users==null) return null;
        List<UserEntity> list = new ArrayList<>();
        for(User u : users){
            list.add(fromModelWithoutFollowing(u));
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
