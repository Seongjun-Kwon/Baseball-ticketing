package com.programmers.baseballticketing.common.exception;

public class SaveFailException extends RuntimeException {

	private static final String MESSAGE = "데이터 저장에 실패했습니다.";

	public SaveFailException() {
		super(MESSAGE);
	}
}
