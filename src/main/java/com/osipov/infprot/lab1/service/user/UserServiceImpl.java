package com.osipov.infprot.lab1.service.user;

import com.osipov.infprot.lab1.entity.User;
import com.osipov.infprot.lab1.exceptions.model.NotFoundException;
import com.osipov.infprot.lab1.exceptions.model.ValidationException;
import com.osipov.infprot.lab1.mapper.UserMapper;
import com.osipov.infprot.lab1.dto.user.NewUserRequest;
import com.osipov.infprot.lab1.dto.user.SearchUserParams;
import com.osipov.infprot.lab1.dto.user.UpdateUserRequest;
import com.osipov.infprot.lab1.dto.user.UserDto;
import com.osipov.infprot.lab1.repository.UserRepository;
import com.osipov.infprot.lab1.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
public class UserServiceImpl extends BaseService<User> implements UserServiceAdmin {
    private final UserRepository rootRepo;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository, "entity.User");
        rootRepo = repository;
    }

    @Override
    public Collection<UserDto> findAll(int from, int size) {
        return UserMapper.toDto(super.findAllBase(from, size));
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.toDto(super.findByIdBase(id));
    }

    public void existsById(Long id){
        super.findByIdBase(id);
    }

    public User getUserById(Long id){
        return super.findByIdBase(id);
    }

    @Override
    public UserDto findByParam(SearchUserParams userParams) {
        if (userParams.getUsername() != null)
            return this.findByUsername(userParams.getUsername());
        if (userParams.getEmail() != null)
            return this.findByEmail(userParams.getEmail());

        throw new ValidationException("'username' или 'email' должен быть указан");
    }

    @Override
    public UserDto create(NewUserRequest userRequest) {
        this.validUsernameAndEmail(userRequest.getUsername(), userRequest.getEmail());
        User user = UserMapper.fromDto(userRequest);
        return UserMapper.toDto(super.save(user));
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public UserDto update(UpdateUserRequest userRequest) {
        User storageUser = super.findByIdBase(userRequest.getId());
        storageUser.setUsername(userRequest.getUsername());
        storageUser.setEmail(userRequest.getEmail());
        this.validUsernameAndEmail(storageUser);

        return UserMapper.toDto(rootRepo.save(storageUser));
    }

    public UserDto findByUsername(String name) {
        User user = rootRepo.findByUsername(name).orElseThrow(
                () -> {
                    String message = format("%s{name=%s} не найдено", nameClassForLog, name);
                    log.info(message);
                    return new NotFoundException(message);
                }
        );

        log.debug("findByUsername({}) -> {}{name={}} получен из БД и возвращен", name, nameClassForLog, name);

        return UserMapper.toDto(user);
    }

    public UserDto findByEmail(String email) {
        User user = rootRepo.findByEmail(email).orElseThrow(
                () -> {
                    String message = format("%s{email=%s} не найдено", nameClassForLog, email);
                    log.info(message);
                    return new NotFoundException(message);
                }
        );

        log.debug("findByEmail({}) -> {}{email={}} получен из БД и возвращен", email, nameClassForLog, email);

        return UserMapper.toDto(user);
    }

    private void validUsernameAndEmail(String username, String email) {
        if (rootRepo.existsByUsername(username))
            throw new ValidationException("Пользователь с таким 'username' уже существует");
        if (rootRepo.existsByEmail(email))
            throw new ValidationException("Пользователь с таким 'email' уже существует");
    }

    private void validUsernameAndEmail(User user) {
        Optional<User> byUsername = rootRepo.findByUsername(user.getUsername());
        if (byUsername.isPresent() && !byUsername.get().getId().equals(user.getId()))
            throw new ValidationException("Пользователь с таким 'username' уже существует");

        Optional<User> byEmail = rootRepo.findByEmail(user.getEmail());
        if (byEmail.isPresent() && !byEmail.get().getId().equals(user.getId()))
            throw new ValidationException("Пользователь с таким 'email' уже существует");
    }
}
