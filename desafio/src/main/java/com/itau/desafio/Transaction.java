package com.itau.desafio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Transaction {
    @JsonProperty("valor")
    private final BigDecimal valor;
    @JsonProperty("dataHora")
    private final OffsetDateTime dataHora;

    public Transaction(BigDecimal valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }
    @Override
    public String toString() {
        return "Transacao{" +
                "valor=" + valor +
                ", dataHora=" + dataHora +
                '}';
    }

    public BigDecimal getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
