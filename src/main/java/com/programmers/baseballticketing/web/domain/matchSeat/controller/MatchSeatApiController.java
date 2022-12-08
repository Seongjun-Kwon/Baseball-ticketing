package com.programmers.baseballticketing.web.domain.matchSeat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.baseballticketing.domain.matchSeat.service.MatchSeatService;
import com.programmers.baseballticketing.web.domain.matchSeat.dto.MatchSeatResponseDto;
import com.programmers.baseballticketing.web.domain.matchSeat.dto.MatchSeatSearchRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/match-seat")
@RequiredArgsConstructor
public class MatchSeatApiController {

	private final MatchSeatService matchSeatService;

	@GetMapping
	public List<MatchSeatResponseDto> getMatchSeats(
		@RequestParam(required = false) Long matchId,
		@RequestParam(required = false) String status
	) {
		MatchSeatSearchRequestDto matchSeatSearchRequestDto = new MatchSeatSearchRequestDto(matchId, status);
		return matchSeatService.getMatchSeats(matchSeatSearchRequestDto);
	}
}
