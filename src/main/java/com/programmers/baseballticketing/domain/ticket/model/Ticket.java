package com.programmers.baseballticketing.domain.ticket.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ticket {

	private final Long id;
	private PaymentStatus status;
	private final LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private final Integer price;
	private final Long userId;
	private final Long matchId;
	private final Long seatId;
}
