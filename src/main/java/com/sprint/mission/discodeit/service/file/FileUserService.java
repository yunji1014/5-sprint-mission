package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileUserService implements UserService {

    private final UserRepository userRepository = new FileUserRepository();

    @Override
    public User create(String nickname, String email, String password) {
        User user = new User(nickname, email, password); // 내부에서 UUID 생성
        return userRepository.save(user);
    }

    @Override
    public User find(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UUID id, String newNickname, String newEmail, String newPassword) {
        return userRepository.findById(id)
                .map(u -> {
                    u.update(newNickname, newEmail, newPassword); // 모든 필드&timestamp 갱신
                    return userRepository.save(u);                // 덮어쓰기
                })
                .orElse(null);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
