package com.programmers.baseballticketing.common.exception;

public class NotFoundException extends RuntimeException {

	private static final String MESSAGE = "데이터 조회에 실패했습니다.";

	public NotFoundException() {
		super(MESSAGE);
	}
}
