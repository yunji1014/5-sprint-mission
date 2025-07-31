package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.UUID;

public class JCFChannelService implements ChannelService {
    ChannelRepository repo;

    public JCFChannelService(ChannelRepository repo) {
        this.repo = repo;
    }

    @Override
    public Channel create(String channelName) {
        Channel channel = new Channel(channelName);
        return repo.save(channel);
    }

    @Override
    public Channel findById(UUID id) {
        Channel channel = repo.findById(id).orElse(null);
        if(channel == null) {
            throw new RuntimeException("Channel with id " + id + " not found");
        }
        return channel;
    }

    @Override
    public List<Channel> findAll() {
        return repo.findAll();
    }

    @Override
    public Channel update(UUID id, String newChannelName) {
        Channel channel = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Channel with id " + id + " not found"));

        channel.setChannelName(newChannelName);
        return repo.save(channel);
    }

    @Override
    public void delete(UUID id) {
        if(repo.delete(id) == null){
            throw new RuntimeException("Channel with id " + id + " not found");
        }
    }
}
