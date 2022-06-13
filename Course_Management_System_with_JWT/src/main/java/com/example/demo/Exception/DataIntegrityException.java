package com.example.demo.Exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class DataIntegrityException extends SQLIntegrityConstraintViolationException{

	public DataIntegrityException(String message) {
		super(message);
	}
	
}
