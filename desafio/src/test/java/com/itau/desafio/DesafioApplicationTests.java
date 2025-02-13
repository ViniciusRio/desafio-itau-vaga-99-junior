package com.itau.desafio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


import java.time.OffsetDateTime;

@SpringBootTest
class DesafioApplicationTests {

	@Test
	public void shouldValidDate() {
		TransactionService transactionService = new TransactionService();
		Transaction transaction = new Transaction(100.0, OffsetDateTime.now().plusDays(1));

		assertThrows(
				IllegalArgumentException.class,
				() -> transactionService.addTransaction(transaction)
		);

	}

	@Test
	public void shouldValidValue() {
		TransactionService transactionService = new TransactionService();
		Transaction transaction = new Transaction(-1.0, OffsetDateTime.now());

		assertThrows(
				IllegalArgumentException.class,
				() -> transactionService.addTransaction(transaction)
		);

	}

}
