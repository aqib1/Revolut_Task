package com.revolut.moneytransfer.bdd.createuser;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/test/resources/com/revolut/moneytransfer/bdd/createuser/"} , glue = "com.revolut.moneytransfer")
public class CucumberTestRunner {

}
