package io.github.feluzan.many2many;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {

    User toModel (UserEntity entity);

    UserEntity fromModel (User model);

    List<User> toModelList (List<UserEntity> entityList);

    List<UserEntity> fromModelList (List<User> modelList);

}
