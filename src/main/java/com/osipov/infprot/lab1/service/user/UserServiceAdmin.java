package com.osipov.infprot.lab1.service.user;

import com.osipov.infprot.lab1.dto.user.NewUserRequest;
import com.osipov.infprot.lab1.dto.user.SearchUserParams;
import com.osipov.infprot.lab1.dto.user.UpdateUserRequest;
import com.osipov.infprot.lab1.dto.user.UserDto;

import java.util.Collection;

public interface UserServiceAdmin {
    Collection<UserDto> findAll(int from, int size);

    UserDto findById(Long id);

    UserDto findByParam(SearchUserParams userParams);

    UserDto create(NewUserRequest userRequest);

    void deleteById(Long id);

    UserDto update(UpdateUserRequest userRequest);
}
