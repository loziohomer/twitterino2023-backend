package dev.hlab.twitterino.dal.mapper;

import dev.hlab.twitterino.dal.dto.UserSigninDto;
import dev.hlab.twitterino.dal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring") // bean che può essere iniettato da Spring
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    public User signInToUser(UserSigninDto userSigninDto);
}
