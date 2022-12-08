package com.programmers.baseballticketing.domain.stadium.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Seat {

	private Long id;
	private final SeatSection section;
	private final Integer seatNumber;
	private Integer price;
	private final Long stadiumId;
}
