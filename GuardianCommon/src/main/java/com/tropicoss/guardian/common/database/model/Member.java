package com.tropicoss.guardian.common.database.model;

import java.time.LocalDateTime;

public class Member {
    private int memberId;
    private String discordId;
    private Boolean isAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Member() {}

    public Member(int memberId, String discordId, Boolean isAdmin) {
        this.memberId = memberId;
        this.discordId = discordId;
        this.isAdmin = isAdmin;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getDiscordId() {
        return discordId;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
        this.modifiedAt = LocalDateTime.now();
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
        this.modifiedAt = LocalDateTime.now();
    }
}
