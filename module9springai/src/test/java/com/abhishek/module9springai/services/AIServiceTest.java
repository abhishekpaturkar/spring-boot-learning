package com.abhishek.module9springai.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {

    @Autowired
    private AIService aiService;

    @Test
    public void testGetJoke() {
        var joke = aiService.getJoke("Dogs");
        System.out.println(joke);
    }

    @Test
    public void testGetEmbedding() {
        var embed = aiService.getEmbedding("This is a big text here");
        System.out.println("Length: " + embed.length);
        for(float e : embed) {
            System.out.print(e + " ");
        }
    }

    @Test
    public void testIngestDataToVectorStore() {
        aiService.ingestDataToVectorStore();
    }

    @Test
    public void testSimilaritySearch() {
        var result = aiService.similaritySearch("A team of people travel through a wormhole in space");
        for(var doc : result) {
            System.out.println(doc);
        }
    }
}
