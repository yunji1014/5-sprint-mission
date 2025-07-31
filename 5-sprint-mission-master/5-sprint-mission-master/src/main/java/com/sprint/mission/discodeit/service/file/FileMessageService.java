package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.UUID;

public class FileMessageService implements MessageService {
    private final MessageRepository messageRepository = new FileMessageRepository();

    @Override
    public Message create(UUID userId, UUID chId, String messageDetail) {
        Message message = new Message(userId, chId, messageDetail);
        return messageRepository.save(message);
    }

    @Override
    public Message findById(UUID id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message update(UUID id, String newMessage) {
        return messageRepository.findById(id)
                .map(m -> {
                    m.update(newMessage);
                    return messageRepository.save(m);
                })
                .orElse(null);
    }

    @Override
    public void delete(UUID id) {
        messageRepository.delete(id);
    }
}
