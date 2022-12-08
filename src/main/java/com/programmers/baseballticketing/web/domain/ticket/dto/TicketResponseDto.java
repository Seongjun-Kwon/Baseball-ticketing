package com.programmers.baseballticketing.web.domain.ticket.dto;

import java.time.LocalDateTime;

import com.programmers.baseballticketing.domain.ticket.model.PaymentStatus;
import com.programmers.baseballticketing.domain.ticket.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketResponseDto {

	private PaymentStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private Long matchId;
	private Long seatId;
	private Long userId;
	private Integer price;

	public static TicketResponseDto toTicketResponseDto(Ticket ticket) {
		return new TicketResponseDto(
			ticket.getStatus(),
			ticket.getCreatedAt(),
			ticket.getLastModifiedAt(),
			ticket.getMatchId(),
			ticket.getSeatId(),
			ticket.getUserId(),
			ticket.getPrice()
		);
	}
}
