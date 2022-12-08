package com.programmers.baseballticketing.web.domain.ticket.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class TicketSearchRequestDto {

	private Long matchId;
	private LocalDateTime startTime;
	private String team;
	private Long userId;
}
