package com.programmers.baseballticketing.domain.stadium.model;

import java.util.Arrays;

public enum SeatSection {

	FIRST_BASE_INFIELD_NORMAL,
	FIRST_BASE_INFIELD_PREMIUM,
	THIRD_BASE_INFIELD_NORMAL,
	THIRD_BASE_INFIELD_PREMIUM,
	OUTFIELD,
	VIP;

	public static SeatSection getSeatSection(String section) {
		return Arrays.stream(SeatSection.values())
			.filter(seatSection -> seatSection.equals(section))
			.findFirst()
			.orElseThrow(() -> {
				throw new IllegalArgumentException("좌석 구역이 잘못 입력되었습니다.");
			});
	}
}
