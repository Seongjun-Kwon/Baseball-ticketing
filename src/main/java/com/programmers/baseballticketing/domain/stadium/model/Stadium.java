package com.programmers.baseballticketing.domain.stadium.model;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum Stadium {

	JAMSIL(1L, "서울종합운동장 야구장", 25553),
	GOCHEOK(2L, "고척 스카이돔", 17000),
	INCHEON(3L, "인천 SSG 랜더스필드", 23000),
	SUWON(4L, "수원 케이티 위즈 파크", 20000),
	DAEGU(5L, "대구 삼성 라이온즈 파크", 24000),
	BUSAN(6L, "사직 야구장", 22990),
	CHANGWON(7L, "창원 NC 파크", 22112),
	DAEJEON(8L, "대전 한화생명 이글스파크", 13000),
	GWANGJU(9L, "광주-기아 챔피언스 필드", 20500);

	private final Long id;
	private final String name;
	private Integer seatsCount;

	public static Stadium getStadiumById(Long id) {
		return Arrays.stream(Stadium.values())
			.filter(stadium -> stadium.id.equals(id))
			.findFirst()
			.orElseThrow(() -> {
				throw new IllegalArgumentException("잘못된 경기장 아이디입니다.");
			});
	}

	public static Stadium getStadiumByName(String name) {
		return Arrays.stream(Stadium.values())
			.filter(stadium -> stadium.name.equals(name))
			.findFirst()
			.orElseThrow(() -> {
				throw new IllegalArgumentException("잘못된 경기장 이름입니다.");
			});
	}
}
