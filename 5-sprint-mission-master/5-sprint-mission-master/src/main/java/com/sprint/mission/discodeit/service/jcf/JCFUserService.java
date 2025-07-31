package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class JCFUserService implements UserService {

    UserRepository repo;

    public JCFUserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User create(String nickname, String email, String password) {
        User user = new User(nickname, email, password);
        return repo.save(user);
    }

    @Override
    public User find(UUID id) {
        User user = repo.findById(id).orElse(null);
        if(user == null){
            throw new NoSuchElementException("User with id" + id + "User not found");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User update(UUID id, String newNickname, String newEmail, String newPassword) {
        User user = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id" + id + "User not found"));

        user.update(newNickname, newEmail, newPassword);
        return repo.save(user); //덮어쓰기
    }

    @Override
    public void delete(UUID id) {
        if(repo.delete(id) == null){
            throw new NoSuchElementException("User with id" + id + "User not found");
        }
    }
}
