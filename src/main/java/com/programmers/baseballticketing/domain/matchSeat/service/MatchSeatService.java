package com.programmers.baseballticketing.domain.matchSeat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.programmers.baseballticketing.domain.matchSeat.model.MatchSeat;
import com.programmers.baseballticketing.domain.matchSeat.repository.MatchSeatRepository;
import com.programmers.baseballticketing.domain.stadium.model.Seat;
import com.programmers.baseballticketing.domain.stadium.repository.SeatRepository;
import com.programmers.baseballticketing.web.domain.matchSeat.dto.MatchSeatResponseDto;
import com.programmers.baseballticketing.web.domain.matchSeat.dto.MatchSeatSearchRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchSeatService {

	private final MatchSeatRepository matchSeatRepository;
	private final SeatRepository seatRepository;

	public List<MatchSeatResponseDto> getMatchSeats(MatchSeatSearchRequestDto matchSeatSearchRequestDto) {
		List<MatchSeat> matchSeats = matchSeatRepository.findBy(
			matchSeatSearchRequestDto.getMatchId(),
			matchSeatSearchRequestDto.getStatus()
		);

		return matchSeats.stream()
			.map(matchSeat -> {
				Seat seat = seatRepository.findById(matchSeat.getSeatId());
				return MatchSeatResponseDto.toMatchSeatResponseDto(seat, matchSeat);
			})
			.toList();
	}
}
