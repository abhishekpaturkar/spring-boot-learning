package com.abhishek.module9springai.services;

import com.abhishek.module9springai.dto.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;

    public String getJoke(String topic) {
        String systemPrompt = """
                You are a standup comedian, you make poetic jokes in 4 lines.
                You don't make jokes around politics.
                Give me a joke on the topic: {topic}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(systemPrompt);
        String renderedText = promptTemplate.render(Map.of("topic", topic));

        var response = chatClient.prompt()
                .advisors(
                        new SimpleLoggerAdvisor() // Logs for AI
                )
                .user(renderedText)
                .call()
//                .chatClientResponse() This is for all the metadata
                .entity(Joke.class)
                ;

//        return response.chatResponse().getResult().getOutput().getText();
        return response.text(); // as we are parsing the response to Joke object
    }
}
