package com.programmers.baseballticketing.web.domain.ticket.dto;

import java.time.LocalDateTime;

import com.programmers.baseballticketing.domain.ticket.model.PaymentStatus;
import com.programmers.baseballticketing.domain.ticket.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreateRequestDto {

	private Integer price;
	private Long userId;
	private Long matchId;
	private Long seatId;

	public Ticket toTicket(TicketCreateRequestDto ticketCreateRequestDto) {
		return new Ticket(
			PaymentStatus.ACCEPTED,
			LocalDateTime.now(),
			LocalDateTime.now(),
			ticketCreateRequestDto.getPrice(),
			ticketCreateRequestDto.getUserId(),
			ticketCreateRequestDto.getMatchId(),
			ticketCreateRequestDto.getSeatId()
		);
	}
}
