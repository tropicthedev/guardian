package com.tropicoss.guardian.cli;

import com.tropicoss.guardian.common.config.ConfigManager;

import java.io.IOException;
import java.nio.file.Path;

public class Guardian {

    public static void main(String[] args) throws IOException {
        ConfigManager configManager =  new ConfigManager(Path.of("config.json"));

        configManager.loadConfig();

        System.out.println(configManager.getConfigData().getBotConfig().token);
    }
}
