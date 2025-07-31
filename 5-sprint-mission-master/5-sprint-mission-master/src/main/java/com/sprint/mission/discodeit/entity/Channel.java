package com.sprint.mission.discodeit.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class Channel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID id;
    private final long createAt;
    private long updateAt;
    private String channelName;

    public Channel(String channelName) {
        this.id = UUID.randomUUID();
        this.createAt = Instant.now().getEpochSecond();
        this.updateAt = createAt;
        this.channelName = channelName;

    }

    public UUID getId() {
        return id;
    }

    public long getCreateAt() {
        return createAt;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void update(String newChannelname) {
        boolean anyValueUpdated = false;
        if(newChannelname != null && !newChannelname.equals(this.channelName)) {
            this.channelName = newChannelname;
            anyValueUpdated = true;
        }
        if(anyValueUpdated) {
            this.updateAt = Instant.now().getEpochSecond();
        }
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id: " + id +
                ", createAt: " + createAt +
                ", updateAt: " + updateAt +
                ", ChannelName: " + channelName + '\'' +
                '}';
    }
}
