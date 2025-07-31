package com.sprint.mission.discodeit.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID id;
    private final Long createAt;
    private Long updateAt;
    private String nickname;
    private String email;
    private String password; //직렬화 제외 대상

    public User(String nickname, String email, String password) {

        this.id = UUID.randomUUID();
        this.createAt = Instant.now().getEpochSecond();
        this.updateAt = this.createAt;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
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


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void update(String newNickname, String newEmail, String newPassword) {
        boolean anyValueUpdated = false;
        if(newNickname != null && !newNickname.equals(this.nickname)) {
            this.nickname = newNickname;
            anyValueUpdated = true;
        }
        if(newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
            anyValueUpdated = true;
        }
        if(newPassword != null && !newPassword.equals(this.password)) {
            this.password = newPassword;
            anyValueUpdated = true;
        }
        if(anyValueUpdated) {
            this.updateAt = Instant.now().getEpochSecond();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", username=" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
