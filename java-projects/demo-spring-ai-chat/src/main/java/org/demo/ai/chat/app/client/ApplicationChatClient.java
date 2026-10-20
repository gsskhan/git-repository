package org.demo.ai.chat.app.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.demo.ai.chat.app.constant.ApplicationConstants.OLLAMA_LLM_PARAM_TEMPERATURE;

@Slf4j
@Service
public class ApplicationChatClient {

    @Value("${app.ollama.base.url}")
    private String baseUrl;

    @Value("${app.ollama.model}")
    private String model;

    @Value("${app.ollama.model.chat.system-prompt}")
    private String systemPrompt;

    public String call(String promptText) {
        log.info("Preparing to call model: {} at: {} with prompt: {}", model, baseUrl, promptText);

        var ollamaApi = OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();

        var chatModel = OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .options(
                        OllamaChatOptions.builder()
                                .model(model)
                                .temperature(OLLAMA_LLM_PARAM_TEMPERATURE)
                                .build())
                .build();

        // 1. Define your system prompt / persona
        SystemMessage systemMessage = new SystemMessage(systemPrompt);

        // 2. Define the user message
        UserMessage userMessage = new UserMessage(promptText);

        // 3. Pass both messages into the Prompt
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        ChatResponse response = chatModel.call(prompt);
        log.debug("Response received: {}", response);

        return response.getResults().stream()
                .map(generation -> generation.getOutput().getText())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}