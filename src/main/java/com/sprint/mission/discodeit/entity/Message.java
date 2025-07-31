package com.sprint.mission.discodeit.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID id;
    private final long createdAt;
    private long updatedAt;

    private final UUID userId;
    private final UUID chId;

    private String message;


    //외부에서 받아오는 생성자들을 선언해줘야 함.
    public Message(UUID userId, UUID chId, String message){
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now().getEpochSecond();
        this.updatedAt = this.createdAt;
        this.userId = userId;
        this.chId = chId;
        this.message = message;
    }

    public UUID getId(){
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getChId() {
        return chId;
    }

    public long getCreatedAt(){
        return createdAt;
    }

    public long getUpdatedAt(){
        return updatedAt;
    }

    public String getMessage(){
        return message;
    }

    public void update(String newMessage){
        boolean anyValueUpdated = false;
        if(newMessage != null && !newMessage.equals(this.message)){
            this.message = newMessage;
            anyValueUpdated = true;
        }
        if(anyValueUpdated){
            this.updatedAt = Instant.now().getEpochSecond();
        }
    }

    @Override
    public String toString(){
        return "Message{ " +
                "id: " + id +
                ", createdAt: " + createdAt +
                ", updateAt: " + updatedAt +
                ", Message: " + message + '\'' +
                ", updatedAt: " + updatedAt + '\'' +
                '}';
    }
}
