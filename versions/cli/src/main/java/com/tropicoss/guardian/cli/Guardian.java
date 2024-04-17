package com.tropicoss.guardian.cli;

import com.tropicoss.guardian.cli.discord.commands.OnboardingCommand;
import com.tropicoss.guardian.common.config.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.io.IOException;
import java.nio.file.Path;

public class Guardian {

    public static void main(String[] args) throws IOException, InterruptedException {

        ConfigManager configManager =  new ConfigManager(Path.of("config.json"));

        JDA api = JDABuilder.createDefault(configManager.getConfigData().getBotConfig().token)
                .build()
                .awaitReady();

        api.addEventListener(new OnboardingCommand());

        Guild guild =  api.getGuildById(configManager.getConfigData().getBotConfig().guildId);

        if(guild != null ) {
            guild.upsertCommand(
                    Commands.slash("welcome", "Creates a discord embed for new users to start onboarding")
            ).queue();
        }
    }
}
