/**
 * 
 */
package fw.driverfactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import fw.settings.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author vancuong.tran
 *
 */
public class ChromeDriverManager extends DriverManager {
	private ChromeDriverService chService;

	@Override
	protected void startService() {
		if (null == chService) {
			try {
				WebDriverManager.chromedriver().setup();
				chService = new ChromeDriverService.Builder().usingAnyFreePort().build();
				chService.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void stopService() {
		if (null != chService && chService.isRunning())
			chService.stop();

	}

	@Override
	protected void createDriver() {
		try {
			Map<String, Object> plugin_Chrome = new HashMap<>();
			File f = new File(Constants.RESOURCE_TEST);

			plugin_Chrome.put("enabled", false);
			plugin_Chrome.put("name", "Chrome PDF Viewer");
			Map<String, Object> prefs_Chrome = new HashMap<>();
			prefs_Chrome.put("download.default_directory", f.getCanonicalPath());
			prefs_Chrome.put("profile.default_content_settings.popups", 0);
			prefs_Chrome.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
			prefs_Chrome.put("plugins.plugins_list", Arrays.asList(plugin_Chrome));
			prefs_Chrome.put("credentials_enable_service", false);
			prefs_Chrome.put("profile.password_manager_enabled", false);
			ChromeOptions options_Chrome = new ChromeOptions();
			// options_Chrome.addArguments("test-type");
			options_Chrome.setExperimentalOption("prefs", prefs_Chrome);
			options_Chrome.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options_Chrome.setExperimentalOption("useAutomationExtension", false);
			options_Chrome.addArguments("start-maximized");
			options_Chrome.setAcceptInsecureCerts(true);

			driver = new ChromeDriver(chService, options_Chrome);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
