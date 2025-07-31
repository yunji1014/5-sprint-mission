package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.UUID;

public class FileChannelService implements ChannelService {

    private final ChannelRepository channelRepository = new FileChannelRepository();

    @Override
    public Channel create(String channelName) {
        Channel channel = new Channel(channelName);
        return channelRepository.save(channel);
    }

    @Override
    public Channel findById(UUID id) {
        return channelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel update(UUID id, String newChannelName) {
        return channelRepository.findById(id)
                .map( c -> {
                    c.update(newChannelName);
                    return channelRepository.save(c);
                })
                .orElse(null);
    }

    @Override
    public void delete(UUID id) {
        channelRepository.delete(id);
    }
}
