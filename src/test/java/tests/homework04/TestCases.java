package tests.homework04;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

public class TestCases {
   private RemoteWebDriver driver;
   private WebDriverWait wait;

   @BeforeMethod
    public void setup(){
        BrowserFactory.createDriver("chrome");
        wait = new WebDriverWait(driver,10);
        driver.executeScript("widow.scrollBy(0,250)");
    }


    /*
   DAYS1.go to http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox2.Randomlyselect a checkbox.
   As soon as you check any day, print the name of the day and uncheck immediately.
   After you check and uncheck Friday for the third time, exit the program.

    NOTE: Remember some checkboxes are not selectable.
    You need to find a way to ignore them when they are randomly selected.
    It has to be dynamic. Do not hard code Saturday and Sunday.
    Use values of certain attributes.
     */
    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
       driver.quit();
    }
}
