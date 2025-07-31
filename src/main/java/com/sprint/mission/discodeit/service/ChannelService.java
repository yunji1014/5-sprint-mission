package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    Channel create(String channelName);
    Channel findById(UUID id);
    List<Channel> findAll();
    Channel update(UUID id, String newChannelName);
    void delete(UUID id);
}
