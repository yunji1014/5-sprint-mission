package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository{

    //즉, 저장 로직 분리
    User save(User user); //저장과 업데이트

    Optional<User> findById(UUID id);

    List<User> findAll();

     User delete(UUID id);

    boolean existsById(UUID id);

    //더 필요한 기능들 추가
    //https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html
}
