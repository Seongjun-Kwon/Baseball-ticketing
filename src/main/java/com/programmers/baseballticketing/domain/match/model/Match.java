package com.programmers.baseballticketing.domain.match.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Match {

	private final Long id;
	private LocalDateTime startTime;
	private final String homeTeamName;
	private final String awayTeamName;
	private Integer leftSeatsCount;
	private final Long stadiumId;
}
