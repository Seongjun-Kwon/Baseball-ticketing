package com.programmers.baseballticketing.common.exception;

public class DeleteFailException extends RuntimeException {

	private static final String MESSAGE = "데이터 삭제에 실패했습니다.";

	public DeleteFailException() {
		super(MESSAGE);
	}
}
