package com.osipov.infprot.lab1.mapper;

import com.osipov.infprot.lab1.entity.User;
import com.osipov.infprot.lab1.dto.user.NewUserRequest;
import com.osipov.infprot.lab1.dto.user.UserDto;

import java.util.Collection;

public class UserMapper {
    public static User fromDto(NewUserRequest newUserRequest){
        return new User(null, newUserRequest.getUsername(), newUserRequest.getEmail());
    }

    public static UserDto toDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public static Collection<UserDto> toDto(Collection<User> users){
        return users.stream().map(UserMapper::toDto).toList();
    }
}
