package extentReportExample;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportExample {
	public static WebDriver wd;
	public static ExtentReports extentreport;

	public static ExtentTest test;

	@BeforeTest
	public void launchBrowser() {
		extentreport = new ExtentReports("C:\\WorkSpace3\\J1_ExtendReport\\reports\\report.html");

		WebDriverManager.chromedriver().setup();
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterTest
	public void closeBrowser() {
		wd.quit();
		extentreport.flush();
	}

	@Test
	public void openGoogle() throws IOException {

		test = extentreport.startTest("verify google title");
		test.log(LogStatus.INFO, "navigating to url");
		wd.get("https://www.google.com/");
		String title = wd.getTitle();
		if (title.equals("Google")) {
			test.log(LogStatus.PASS, "Actual title and expected title are matching");
		} else {
			test.log(LogStatus.FAIL,test.addScreenCapture(screenshot()) + "Actual title and expected title are not matching");

		}
	}

	@Test
	public void openBing() throws IOException {
		test = extentreport.startTest("verify bing title");
		test.log(LogStatus.INFO, "navigating to url");
		wd.get("https://www.bing.com/");
		String title = wd.getTitle();
		if (title.equals("Bing")) {
			test.log(LogStatus.PASS, "Actual title and expected title are matching");
		} else {
			test.log(LogStatus.FAIL,test.addScreenCapture(screenshot()) + "Actual title and expected title are not matching");

		}
	}

	@Test
	public void openWikipedia() throws IOException {
		test = extentreport.startTest("verify wiki title");
		test.log(LogStatus.INFO, "navigating to url");
		wd.get("https://www.wikipedia.org/");
		String title = wd.getTitle();
		if (title.equals("wiki")) {
			test.log(LogStatus.PASS, "Actual title and expected title are matching");
		} else {
			test.log(LogStatus.FAIL,
					test.addScreenCapture(screenshot()) + "Actual title and expected title are not matching");

		}
	}

	public static String screenshot() throws IOException {

		File src = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\WorkSpace3\\J1_ExtendReport\\screenshots\\wiki1.png");
		String absolutepath = destination.getAbsolutePath();
		FileHandler.copy(src, destination);
		return absolutepath;

	}

}
