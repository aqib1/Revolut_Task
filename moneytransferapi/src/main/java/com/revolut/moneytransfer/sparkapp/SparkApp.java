package com.revolut.moneytransfer.sparkapp;

import java.util.Objects;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 *        <p>
 *        This class is used to initialize spark-java application against all
 *        controller and exceptions
 *        </p>
 */
public class SparkApp {
	// we will make object volatile so that in the case of multiple threads,
	// each thread have have updated object value, from main memory
	private static volatile SparkApp sparkApp = null;

	private SparkApp() {

	}

	public static SparkApp getInstance() {
		if (Objects.isNull(sparkApp)) {
			synchronized (SparkApp.class) {
				if (Objects.isNull(sparkApp)) {
					sparkApp = new SparkApp();
				}
			}
		}
		return sparkApp;
	}

}
