/**
 * 
 */
package fw.driverfactory;

import org.openqa.selenium.WebDriver;

/**
 * @author vancuong.tran
 *
 */
public abstract class DriverManager {

	protected WebDriver driver;

	protected abstract void startService();

	protected abstract void stopService();

	protected abstract void createDriver();

	public void quitDriver() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}

	}

	public WebDriver getDriver() {
		if (null == driver) {
			startService();
			createDriver();
		}
		return driver;
	}
}
