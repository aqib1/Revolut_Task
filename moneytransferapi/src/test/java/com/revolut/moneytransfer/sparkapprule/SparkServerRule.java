package com.revolut.moneytransfer.sparkapprule;

import org.junit.rules.ExternalResource;
import spark.Service;

/**
 * A JUnit rule that starts a Spark server (
 * <a href="http://sparkjava.com/">http://sparkjava.com/</a> ) before tests run,
 * and shuts the server down after tests run. It can be annotated with
 * either @{@link org.junit.ClassRule} or {@link org.junit.Rule}. If annotated
 * with ClassRule, then the same server will serve all tests, started before the
 * first test is run and stopped after all tests have finished. If annotated
 * with Rule, then a new server is started before and stopped after each
 * individual test.
 * <p>
 * Example usage:
 * 
 * <pre>
 * <code>
 * {@literal @}ClassRule
 *  public static final SparkServerRule SPARK_SERVER = new SparkServerRule(service -> {
 *      service.port(56789);
 *      service.get("/ping", (request, response) -> "pong");
 *      service.get("/health", (request, response) -> "healthy");
 *  });
 * </code>
 * </pre>
 * <p>
 * Credits : sleberknight
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/16/2019
 */
public class SparkServerRule extends ExternalResource {
	private ServiceIntializer serviceInitializer;
	private Service service;

	/**
	 * Create Spark server rule with specified {@link ServiceInitializer}. You use
	 * the {@link ServiceInitializer} to configure the Spark server port, IP
	 * address, security, routes, etc. Things like port and IP address must be
	 * configured before routes.
	 *
	 * @see Service
	 */
	public SparkServerRule(ServiceIntializer svcInit) {
		this.serviceInitializer = svcInit;
	}

	@Override
	protected void before() throws Throwable {
		service = Service.ignite();
		serviceInitializer.init(service);
		service.awaitInitialization();
	}

	@Override
	protected void after() {
		service.stop();
	}

}
