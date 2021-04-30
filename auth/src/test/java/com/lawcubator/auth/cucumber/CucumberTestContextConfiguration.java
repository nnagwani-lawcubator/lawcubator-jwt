package com.lawcubator.auth.cucumber;

import com.lawcubator.auth.AuthApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = AuthApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
