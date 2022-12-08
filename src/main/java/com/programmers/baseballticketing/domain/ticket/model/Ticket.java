package com.programmers.baseballticketing.domain.ticket.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Ticket {

	private Long id;
	private PaymentStatus status;
	private final LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private final Integer price;
	private final Long userId;
	private final Long matchId;
	private final Long seatId;

	public Ticket(
		PaymentStatus status,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt,
		Integer price,
		Long userId,
		Long matchId,
		Long seatId
	) {
		this.status = status;
		this.createdAt = createdAt;
		this.lastModifiedAt = lastModifiedAt;
		this.price = price;
		this.userId = userId;
		this.matchId = matchId;
		this.seatId = seatId;
	}
}
