package com.abhishek.module9springai.dto;

public record Joke(
        String text,
        String category,
        Double laughScore,
        Boolean isNSFW
) {

}
