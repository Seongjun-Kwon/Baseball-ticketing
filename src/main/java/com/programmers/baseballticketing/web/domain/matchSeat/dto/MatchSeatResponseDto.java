package com.programmers.baseballticketing.web.domain.matchSeat.dto;

import com.programmers.baseballticketing.domain.matchSeat.model.MatchSeat;
import com.programmers.baseballticketing.domain.stadium.model.Seat;
import com.programmers.baseballticketing.domain.stadium.model.Stadium;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchSeatResponseDto {

	private String status;
	private String stadiumName;
	private String seatSection;
	private Integer seatNumber;
	private Integer price;

	public static MatchSeatResponseDto toMatchSeatResponseDto(Seat seat, MatchSeat matchSeat) {
		return new MatchSeatResponseDto(
			matchSeat.getStatus().name(),
			Stadium.getStadiumById(seat.getStadiumId()).name(),
			seat.getSection().name(),
			seat.getSeatNumber(),
			seat.getPrice()
		);
	}
}
