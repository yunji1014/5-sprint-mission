package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message create(UUID userId, UUID chId, String message);
    Message findById(UUID id);
    List<Message> findAll();
    Message update(UUID id, String newMessage);
    void delete(UUID id);
}
