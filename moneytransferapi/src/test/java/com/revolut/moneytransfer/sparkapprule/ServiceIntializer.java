package com.revolut.moneytransfer.sparkapprule;

import spark.Service;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/16/2019
 */
@FunctionalInterface
public interface ServiceIntializer {
	void init(Service service);
}
