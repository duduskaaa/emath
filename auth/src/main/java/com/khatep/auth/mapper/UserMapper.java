package com.khatep.auth.mapper;

import com.khatep.auth.dto.AuthRequestDto;
import com.khatep.auth.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(AuthRequestDto authRequestDto);

    AuthRequestDto toAuthRequestDto(User user);
}
