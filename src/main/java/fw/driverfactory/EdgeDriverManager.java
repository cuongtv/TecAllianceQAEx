/**
 * 
 */
package fw.driverfactory;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author vancuong.tran
 *
 */
public class EdgeDriverManager extends DriverManager {
	private EdgeDriverService edService;

	@Override
	protected void startService() {
		if (null == edService) {
			try {
				WebDriverManager.edgedriver().setup();
				edService = new EdgeDriverService.Builder().usingAnyFreePort().build();
				edService.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void stopService() {
		if (null != edService && edService.isRunning())
			edService.stop();
	}

	@Override
	protected void createDriver() {
		driver = new EdgeDriver(edService);
	}

}
