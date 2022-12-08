package com.programmers.baseballticketing.web.domain.match.dto;

import java.time.LocalDateTime;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.stadium.model.Stadium;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchResponseDto {

	private Long id;
	private LocalDateTime startTime;
	private String homeTeamName;
	private String awayTeamName;
	private Integer leftSeatsCount;
	private String stadiumName;

	public static MatchResponseDto toResponseDto(Match match) {
		return new MatchResponseDto(
			match.getId(),
			match.getStartTime(),
			match.getHomeTeamName(),
			match.getAwayTeamName(),
			match.getLeftSeatsCount(),
			Stadium.getStadiumById(match.getStadiumId()).getName()
		);
	}

	public void setId(Long id) {
		this.id = id;
	}
}
