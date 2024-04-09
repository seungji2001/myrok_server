package com.example.myrok.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record JwtTokenDto (
        @JsonProperty("access_token")
        @NotNull
        String accessToken,

        @JsonProperty("refresh_token")
        String refreshToken
) implements Serializable {
    public static JwtTokenDto of(String accessToken, String refreshToken) {
        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}