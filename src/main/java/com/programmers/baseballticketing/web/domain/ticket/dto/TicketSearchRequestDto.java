package com.programmers.baseballticketing.web.domain.ticket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketSearchRequestDto {

	private Long matchId;
	private LocalDateTime startTime;
	private String team;
	private Long userId;
}
