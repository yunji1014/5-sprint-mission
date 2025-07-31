package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class JCFMessageService implements MessageService {

    MessageRepository repo;

    public JCFMessageService(MessageRepository repo, ChannelService channelService, UserService userService) {
        this.repo = repo;
    }

    @Override
    public Message create(UUID userId, UUID chId, String message) {
        Message ms = new Message(userId, chId, message);
        return repo.save(ms);
    }

    @Override
    public Message findById(UUID id) {
        Message message = repo.findById(id).orElse(null);
        if(message == null){
            throw new NoSuchElementException("Message with id " + id + " not found");
        }
        return message;
    }

    @Override
    public List<Message> findAll() {

        return repo.findAll();
    }

    @Override
    public Message update(UUID id, String newMessage) {

        Message message = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Message with id " + id + " not found"));
        message.update(newMessage);
        return repo.save(message);
    }

    @Override
    public void delete(UUID id) {
        if(repo.delete(id) == null){
            throw new NoSuchElementException("Message with id " + id + " not found");
        }
    }
}
