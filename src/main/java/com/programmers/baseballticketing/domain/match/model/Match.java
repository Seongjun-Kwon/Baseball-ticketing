package com.programmers.baseballticketing.domain.match.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Match {

	private Long id;
	private LocalDateTime startTime;
	private final String homeTeamName;
	private final String awayTeamName;
	private Integer leftSeatsCount;
	private final Long stadiumId;

	public Match(
		LocalDateTime startTime,
		String homeTeamName,
		String awayTeamName,
		Integer leftSeatsCount,
		Long stadiumId
	) {
		this.startTime = startTime;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.leftSeatsCount = leftSeatsCount;
		this.stadiumId = stadiumId;
	}
}
