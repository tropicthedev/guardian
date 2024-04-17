package com.tropicoss.guardian.cli.discord.events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserAdapter extends ListenerAdapter {
    private User user;

    public UserAdapter(User user) {
        this.user = user;
    }

    private User getUser(JDA api) {
        // Acquire a reference to the User instance through the id
        User newUser = api.getUserById(this.user.getIdLong());
        if (newUser != null)
            this.user = newUser;
        return this.user;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();

        System.out.println(author.getAsTag() + ": " + message.getContentDisplay());
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        JDA api = event.getJDA();
        User user = getUser(api); // use getter to refresh user automatically on access
        user.openPrivateChannel().queue((channel) -> {
            // Send a private message to the user
            channel.sendMessageFormat("I have joined a new guild: **%s**", event.getGuild().getName()).queue();
        });
    }
}
