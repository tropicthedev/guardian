package com.tropicoss.guardian.common.config;

import com.google.gson.Gson;
import com.tropicoss.guardian.common.database.model.Server;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class ConfigManager {
    private final Path configFilePath;
    private final Gson gson; // Reuse a single Gson instance
    private ConfigData configData;

    public ConfigManager(Path configFilePath) throws IOException {
        this.configFilePath = configFilePath;
        this.gson = new Gson(); // Initialize Gson here
        loadConfig();
    }

    public void loadConfig() throws IOException {
        if (!Files.isRegularFile(configFilePath)) {
            throw new IOException("Configuration file not found at " + configFilePath);
        }
        String json = Files.readString(configFilePath);
        this.configData = gson.fromJson(json, ConfigData.class);
        if (this.configData == null) {
            throw new IOException("Failed to parse configuration data from file");
        }
    }

    public void saveConfig() throws IOException {
        if (configData == null) {
            throw new IllegalStateException("No configuration data to save");
        }
        String json = gson.toJson(configData);
        Files.writeString(configFilePath, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public ConfigData getConfigData() {
        return configData;
    }

    public static class ConfigData {
        private final BotConfig bot = new BotConfig();
        private final ServerConfig server = new ServerConfig();
        private final DatabaseConfig database = new DatabaseConfig();

        public BotConfig getBotConfig() {
            return bot;
        }
        public ServerConfig getServerConfig() {return server;}
        public DatabaseConfig getDatabaseConfig() {return database;}
    }
}