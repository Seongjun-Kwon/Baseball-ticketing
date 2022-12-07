package com.programmers.baseballticketing.domain.stadium.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seat {

	private final Long id;
	private final SeatSection section;
	private final Integer seatNumber;
	private final Integer price;
	private final Long stadiumId;
}
