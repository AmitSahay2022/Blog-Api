package com.amit.kumar.blogapi.exception;

public class UserAllReadyExistException extends RuntimeException {
    public UserAllReadyExistException() {
		super("User AllReady Exist With This Email");
	}
}
