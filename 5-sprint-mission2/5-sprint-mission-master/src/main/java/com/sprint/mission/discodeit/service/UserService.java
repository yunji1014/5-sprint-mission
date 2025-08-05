package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(String nickname, String email, String password);
    User find(UUID id);
    List<User> findAll();
    User update(UUID id, String newNickname, String newEmail, String newPassword);
    void delete(UUID id);
}
