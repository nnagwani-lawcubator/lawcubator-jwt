package com.lawcubator.partners.cucumber;

import com.lawcubator.partners.PartnersApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = PartnersApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
