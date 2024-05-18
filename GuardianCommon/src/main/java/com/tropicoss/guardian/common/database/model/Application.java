package com.tropicoss.guardian.common.database.model;

import java.time.LocalDateTime;

public class Application {
    private int applicationId;
    private String content;
    private String discordId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Application() {}

    public Application(int applicationId, String content, String discordId) {
        this.applicationId = applicationId;
        this.content = content;
        this.discordId = discordId;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public int getApplicationId() {
        return applicationId;
    }

    public String getDiscordId() {
        return discordId;
    }

    public String getContent() {
        return content;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
        this.modifiedAt = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}