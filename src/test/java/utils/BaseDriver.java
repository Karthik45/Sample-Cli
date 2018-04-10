package utils;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriver {

    private WebDriver webDriver;

    public BaseDriver() throws InvalidBrowserException{
        String browser = System.getProperty("browser")==null?"chrome":System.getProperty("browser");
        System.out.println("Browser to be tested on == "+browser);
        try {
            setBrowserProperties(browser);
            launchBrowser(browser);
        } catch (Exception e) {
            throw new InvalidBrowserException(browser);
        }
    }

    private void setBrowserProperties(String browser) throws Exception {
        switch (browser){
            case "chrome" :
                setChromeDriverBasedOnOperatingSystem();
                break;
            case "firefox" :
                setFirefoxDriverBasedOnOperatingSystem();
                break;
            default:
                throw new Exception("Not a valid browser");
        }
    }

    private void launchBrowser(String browser) throws Exception {
        switch (browser) {
            case "chrome" :
                webDriver = new ChromeDriver();
                webDriver.manage().window().maximize();
                break;

            case "firefox" :
                webDriver = new FirefoxDriver();
                webDriver.manage().window().maximize();
                break;

            default:
                throw new Exception("Not a valid browser");
        }
    }

    private void setChromeDriverBasedOnOperatingSystem(){
        if (isWindows()) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromeDriverWin.exe");
        }
        if (isMac()) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromeDriverMac");
        }
        if (isLinux()) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromeDriverlin");
        }
    }

    private void setFirefoxDriverBasedOnOperatingSystem(){
        if (isWindows()) {
            System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriverWin.exe");
        }
        if (isMac()) {
            System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriverMac");
        }
        if (isLinux()) {
            System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriverLin");
        }
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    private boolean isWindows() {
        return SystemUtils.IS_OS_WINDOWS;
    }

    private boolean isLinux() {
        return SystemUtils.IS_OS_LINUX;
    }

    private boolean isMac() {
        return SystemUtils.IS_OS_MAC;
    }
}
