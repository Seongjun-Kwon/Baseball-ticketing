package com.programmers.baseballticketing.web.domain.match.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchUpdateRequestDto {

	private LocalDateTime startTime;
	private Integer leftSeatsCount;
}
