package com.lawcubator.scheduler.cucumber;

import com.lawcubator.scheduler.SchedulerApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = SchedulerApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
