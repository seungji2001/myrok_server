package com.example.myrok.dto;

import jakarta.validation.constraints.NotNull;

public record RecordDeleteDTO(
        @NotNull
        Long recordWriterId
) {
}
