package com.programmers.baseballticketing.web.domain.matchSeat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchSeatSearchRequestDto {

	private Long matchId;
	private String status;
}
