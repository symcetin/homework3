package tests.homework_04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.BrowserUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCases {
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeMethod
    public void setup() {
       driver =  BrowserFactory.createDriver("chrome");
        driver.manage().window().maximize();
    }


    /*
   DAYS
   1.go to http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox
   2.Randomly select a checkbox.
   As soon as you check any day, print the name of the day and uncheck immediately.
   After you check and uncheck Friday for the third time, exit the program.

    NOTE: Remember some checkboxes are not selectable.
    You need to find a way to ignore them when they are randomly selected.
    It has to be dynamic. Do not hard code Saturday and Sunday.
    Use values of certain attributes.
     */

    @Test
    public void testDays() {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        BrowserUtils.wait(4);
        List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));
        System.out.println(checkBoxes.size());
        BrowserUtils.wait(2);
        //randomly click one of the days
        Random random = new Random();
        int x = random.nextInt(6);
        System.out.println(x);
        checkBoxes.get(x).click();

        String arr[] ={"Monday" ,"Tuesday" , "Wednesday","Thursday","Friday","Saturday","Sunday"};
        boolean isCl = checkBoxes.get(x).isEnabled();
        if(isCl){
            System.out.println("PASSED: " + arr[x] + " Clicked");
        }else{
            System.out.println("FAILED:"+ " " + arr[x] + " Not Clicked");
        }

        BrowserUtils.wait(3);

        // click on Friday 3 times
        for (int i = 0; i < 3; i++) {
            checkBoxes.get(4).click();
            System.out.println(checkBoxes.get(4).getText());
        }
//        for (int i = 0; i < checkBoxes.size(); i++) {
//            //       if visible,                            eligible to click  and         not clicked yet
//            if (checkBoxes.get(i).isDisplayed() && checkBoxes.get(i).isEnabled() && (!checkBoxes.get(i).isSelected())) {
//                //if checkbox is not selected, click on it
//                checkBoxes.get(i).click();
//                BrowserUtils.wait(2);
//                // click on the checkbox
//                checkBoxes.get(i).click();
//                System.out.println(i + 1 + " checkbox clicked!");
//                System.out.println(checkBoxes.get(i).getText());
//            } else {
//
//                System.out.println(i + 1 + " checkbox wasn't clicked!");
//            }
//            BrowserUtils.wait(3);
//        }
    }


    /*
    1.go to http://practice.cybertekschool.com/dropdown
    2.verify that dropdowns under Select your date of birth display current year, month,day
     */

    @Test
    public void todayDate(){
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);

        String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY MMMM dd"));

        Select year = new Select(driver.findElement(By.id("year")));
        String yearText = year.getFirstSelectedOption().getText();

        Select month = new Select(driver.findElement(By.id("month")));
        String monthText = month.getFirstSelectedOption().getText();

        Select day = new Select(driver.findElement(By.id("day")));
        String dayText = day.getFirstSelectedOption().getText();
        String actualDate = yearText+" "+monthText+" "+dayText;

       Assert.assertEquals(actualDate, expectedDate);

    }

    /*
    1.go to http://practice.cybertekschool.com/dropdown
    2.select a random year under Select your date of birth
    3.select month January
    4.verify that days dropdown has current number of days
    5.repeat steps 3, 4 for all the months
    NOTE: if you randomly select a leap year, verify February has 29 days
     */
    @Test
    public void yearsMonthsDays(){
        driver.get("http://practice.cybertekschool.com/dropdown");

        BrowserUtils.wait(3);

        Random random = new Random();
        int rndm = random.nextInt(12);

    }


    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
       driver.quit();
    }

}

