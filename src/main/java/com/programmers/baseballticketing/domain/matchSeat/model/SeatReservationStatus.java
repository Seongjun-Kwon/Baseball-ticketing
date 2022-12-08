package com.programmers.baseballticketing.domain.matchSeat.model;

import java.util.Arrays;

public enum SeatReservationStatus {

	EMPTY,
	RESERVED;

	public static SeatReservationStatus getSeatReservationStatus(String status) {
		return Arrays.stream(SeatReservationStatus.values())
			.filter(reservationStatus -> reservationStatus.name().equals(status))
			.findFirst()
			.orElseThrow(() -> {
				throw new IllegalArgumentException("예약 상태가 잘못 입력되었습니다.");
			});
	}
}
