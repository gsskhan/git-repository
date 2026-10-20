package org.demo.ai.chat.app.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.demo.ai.chat.app.constant.ApplicationConstants.OLLAMA_LLM_PARAM_TEMPERATURE;

@Slf4j
@Service
public class ApplicationChatClient {

    @Value("${app.ollama.base.url}")
    private String baseUrl;

    @Value("${app.ollama.model}")
    private String model;

    public String call(String promptText) {
        log.info("Preparing to call model: {} at: {} with prompt: {}", model, baseUrl, promptText);

        var ollamaApi = OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();

        var chatModel = OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .options(
                        OllamaChatOptions.builder()
                                // Pass the exact model tag string used in Ollama
                                .model(model)
                                .temperature(OLLAMA_LLM_PARAM_TEMPERATURE)
                                .build())
                .build();

        ChatResponse response = chatModel.call(new Prompt(promptText));
        log.debug("Response received: {}", response);

        return response.getResults().stream()
                .map(generation -> generation.getOutput().getText())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}