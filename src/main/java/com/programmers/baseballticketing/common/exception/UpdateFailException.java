package com.programmers.baseballticketing.common.exception;

public class UpdateFailException extends RuntimeException {

	private static final String MESSAGE = "데이터 갱신에 실패했습니다.";

	public UpdateFailException() {
		super(MESSAGE);
	}
}
