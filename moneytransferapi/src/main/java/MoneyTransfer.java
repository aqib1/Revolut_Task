import static com.revolut.moneytransfer.utils.Helper.PORT_8080;
import static com.revolut.moneytransfer.utils.Helper.RESPONSE_TYPE_JSON;

import com.revolut.moneytransfer.sparkapp.SparkApp;

/**
 * @author AQIB JAVED
 * @since 12/11/2019
 * @version 1.0
 *
 */
public class MoneyTransfer {

	public static void main(String[] args) {
		SparkApp.getInstance().setPort(PORT_8080).setResponseType(RESPONSE_TYPE_JSON)
				.registerExceptions().registerControllers();
	}
}
