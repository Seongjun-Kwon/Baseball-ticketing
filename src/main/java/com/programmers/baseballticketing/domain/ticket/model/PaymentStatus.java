package com.programmers.baseballticketing.domain.ticket.model;

import java.util.Arrays;

public enum PaymentStatus {

	ACCEPTED,
	PAYMENT_CONFIRMED,
	CANCELLED;

	public static PaymentStatus getTicketStatus(String status) {
		return Arrays.stream(PaymentStatus.values())
			.filter(ticketStatus -> ticketStatus.name().equals(status))
			.findFirst()
			.orElseThrow(() -> {
				throw new IllegalArgumentException("결제 상태를 잘못 입력하였습니다.");
			});
	}
}
