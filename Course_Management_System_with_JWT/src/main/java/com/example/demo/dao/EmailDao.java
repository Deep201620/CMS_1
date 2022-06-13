package com.example.demo.dao;

import com.example.demo.configuration.EmailDetails;

public interface EmailDao {
	
	String sendSimpleEmail(EmailDetails emailDetails);

}
