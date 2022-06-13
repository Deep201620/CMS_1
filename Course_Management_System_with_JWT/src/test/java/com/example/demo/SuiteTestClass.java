package com.example.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.demo.controller.InstructorController;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	UserControllerTest.class, InstructorController.class, StudentControllerTest.class
})
public class SuiteTestClass {

}
