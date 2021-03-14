/**
 * 
 */
package fw.driverfactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author vancuong.tran
 *
 */
public class FirefoxDriverManager extends DriverManager {
	private GeckoDriverService gkService;

	@Override
	protected void startService() {
		if (null == gkService) {
			try {
				WebDriverManager.firefoxdriver().setup();
				gkService = new GeckoDriverService.Builder().usingAnyFreePort().build();
				gkService.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void stopService() {
		if (null != gkService && gkService.isRunning())
			gkService.stop();
	}

	@Override
	protected void createDriver() {
		driver = new FirefoxDriver(gkService);
	}

}
