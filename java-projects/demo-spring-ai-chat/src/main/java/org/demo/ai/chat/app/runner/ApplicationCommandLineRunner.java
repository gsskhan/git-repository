package org.demo.ai.chat.app.runner;

import lombok.extern.slf4j.Slf4j;
import org.demo.ai.chat.app.client.ApplicationChatClient;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

    private final ApplicationChatClient applicationChatClient;

    public ApplicationCommandLineRunner(ApplicationChatClient applicationChatClient) {
        this.applicationChatClient = applicationChatClient;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        log.info("Application command line runner started with {} args.", Arrays.stream(args).count());
        try {
            var promptText = "Generate the names of any 5 famous cities of the world.";
            var response = applicationChatClient.call(promptText);
            log.info("Reply from model: {} {}", System.lineSeparator(), response);
        } catch (Exception e) {
            log.error("Exception occurred at {}: {}", this.getClass().getSimpleName(), e.getMessage(), e);
            throw new Exception(e);
        }
    }
}
