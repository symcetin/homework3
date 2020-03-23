package tests.homework03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;

public class TestCase9_12 {
private WebDriver driver;
private String URL = "https://practice-cybertekschool.herokuapp.com";
// Optional: If you want to to be a real selenium hero, use @DataProvider for for tests cases from 9 through 12.
// Please use following documentation: https:// testng.org/doc/documentation- main.html#parameters-dataproviders
/*
Test case #9
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. And click on “Status Codes”.
Step 3. Then click on “200”.
Step 4. Verify that following message is displayed: “This page returned a 200 status code”
*/
@Test
public void test9(){
    BrowserUtils.wait(2);
    driver.findElement(By.linkText("200")).click();

    String actual = driver.findElement(By.tagName("p")).getText();
    Assert.assertTrue(actual.contains("This page returned a 200 status code"));
}


/*
Test case #10
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. And click on “Status Codes”.
Step 3. Then click on “301”.
Step 4. Verify that following message is displayed: “This page returned a 301 status code”
  */
@Test
public void test10(){
    BrowserUtils.wait(2);
    driver.findElement(By.linkText("301")).click();

    String actual = driver.findElement(By.tagName("p")).getText();
    Assert.assertTrue(actual.contains("This page returned a 301 status code"));
}

/*
Test case #11
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 3. And click on “Status Codes”.
Step 4. Then click on “404”.
Step 5. Verify that following message is displayed: “This page returned a 404 status code”
*/

    @Test
    public void test11(){
        BrowserUtils.wait(2);
        driver.findElement(By.linkText("404")).click();

        String actual = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(actual.contains("This page returned a 404 status code"));
    }
/*
Test case #12
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 3. And click on “Status Codes”.
Step 4. Then click on “500”.
Step 5. Verify that following message is displayed: “This page returned a 500 status code”
 */

    @Test
    public void test12(){
        BrowserUtils.wait(2);
        driver.findElement(By.linkText("500")).click();

        String actual = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(actual.contains("This page returned a 500 status code"));
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        BrowserUtils.wait(3);
        driver.findElement(By.linkText("Status Codes")).click();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }

}
