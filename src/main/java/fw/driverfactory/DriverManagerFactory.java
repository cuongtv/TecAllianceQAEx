/**
 * 
 */
package fw.driverfactory;

/**
 * @author vancuong.tran
 *
 */
public class DriverManagerFactory {
	public static DriverManager getManager(DriverType type) {
		DriverManager driverManager;
		switch (type) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case EDGE:
			driverManager = new EdgeDriverManager();
			break;
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
			break;
		default:
			driverManager = new ChromeDriverManager();
			break;
		}
		return driverManager;
	}

	public enum DriverType {
		CHROME, EDGE, FIREFOX;
	}
}
