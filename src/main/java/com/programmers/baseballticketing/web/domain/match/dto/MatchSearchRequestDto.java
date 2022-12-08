package com.programmers.baseballticketing.web.domain.match.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchSearchRequestDto {

	private LocalDateTime startTime;
	private String stadiumName;
	private String team;
}
