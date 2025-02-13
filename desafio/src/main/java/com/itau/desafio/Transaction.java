package com.itau.desafio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record Transaction(
        @JsonProperty("valor") Double valor,
        @JsonProperty("dataHora") OffsetDateTime dataHora
) {
    @Override
    public String toString() {
        return "Transaction{" +
                "valor=" + valor +
                ", dataHora=" + dataHora +
                '}';
    }
}
