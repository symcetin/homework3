package tests.homework_03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;

public class TestCase1_8 {
    private WebDriver driver;
    private String URL = "https://practice-cybertekschool.herokuapp.com";

    /*
    Test case #1
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter “wrong_dob” into date of birth input box.
    Step 4. Verify that warning message is displayed: “The date of birth is not valid”
    */
    @Test
    public void test1() {
        driver.get(URL);
        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(3);

        driver.findElement(By.name("birthday")).sendKeys("23/21/4567");
        ////*[@id="registrationForm"]/div[8]/div/small[2]
        WebElement message = driver.findElement(By.xpath("//small[text()='The date of birth is not valid']"));
        Assert.assertTrue(message.isDisplayed());
    }
/*
Test case #2

Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. Click on “Registration Form”
Step 3. Verify that following options for programming languages are displayed: c++, java, JavaScript
 */

    @Test
    public void test2() {
        driver.get(URL);
        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(3);

        driver.findElement(By.xpath("//label[text()='C++']")).isDisplayed();
        driver.findElement(By.xpath("//label[text()='Java']")).isDisplayed();
        driver.findElement(By.xpath("//label[text()='JavaScript']")).isDisplayed();
    }

/*
Test case #3
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. Click on “Registration Form”
Step 3. Enter only one alphabetic character into first name input box.
Step 4. Verify that warning message is displayed: “first name must be more than 2 and less than 64 characters long”

 */

    @Test
    public void test3() {
        driver.get(URL);
        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(3);

        driver.findElement(By.name("firstname")).sendKeys("s");
        WebElement warningMessage = driver.findElement(By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']"));
        Assert.assertTrue(warningMessage.isDisplayed());
    }

    /*
    Test case #4
Step 1. Go to https://practice-
cybertekschool.herokuapp.com
Step 2. Click on “Registration Form”
Step 3. Enter only one alphabetic character into last name input box.
Step 4. Verify that warning message is displayed: “The last name must be more than 2 and less than 64 characters long”
   */
    @Test
    public void test4() {
        driver.get(URL);
        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(3);

        driver.findElement(By.name("lastname")).sendKeys("c");
        WebElement warningMessage = driver.findElement(By.xpath("//small[text()='The last name must be more than 2 and less than 64 characters long']"));
        Assert.assertTrue(warningMessage.isDisplayed());
    }

    /*
 Test case #5
Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
Step 2. Click on “Registration Form”
Step 3. Enter any valid
Step 4. Enter any valid
Step 5. Enter any valid
Step 6. Enter any valid
Step 7. Enter any valid
Step 8. Select gender.
Step 9. Enter any valid
Step 10. Select any department.
Step 11. Enter any job title.
Step 12. Select java as a programming language.
Step 13. Click Sign up.
Step 14. Verify that following success message is displayed: “You've successfully completed registration!”
Note: for using dropdown, please refer to the documentation: https://selenium.dev/selenium/ docs/api/java/org/openqa/selenium/support/ui/ Select.html or, please watch short video about drop- downs that is posted on canvas.
first name.
last name. user name. password. phone number.
date of birth.
*/
    @Test
    public void test5() {
        driver.get(URL);
        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(3);

        driver.findElement(By.name("firstname")).sendKeys("tom");
        driver.findElement(By.name("lastname")).sendKeys("smith");
        driver.findElement(By.name("username")).sendKeys("tmsmith");
        driver.findElement(By.name("email")).sendKeys("tomsmith@email.com");
        driver.findElement(By.name("password")).sendKeys("Abs1234567");
        driver.findElement(By.name("phone")).sendKeys("234-456-8976");

        driver.findElement(By.cssSelector("input[value='female']")).click();
        driver.findElement(By.name("birthday")).sendKeys("01/02/1234");

        Select department = new Select(driver.findElement(By.name("department")));
        department.selectByVisibleText("Accounting Office");
        Select jobTitle = new Select(driver.findElement(By.name("job_title")));
        jobTitle.selectByVisibleText("SDET");

        driver.findElement(By.xpath("//label[text()='C++']")).click();
        driver.findElement(By.xpath("//label[text()='Java']")).click();

        BrowserUtils.wait(3);

        driver.findElement(By.id("wooden_spoon")).click();

        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();
        System.out.println(actual);

        Assert.assertEquals(actual, expected);
    }


    /*
 Test case #6
Step 1. Go to "https://www.tempmailaddress.com/"
Step 2. Copy and save email as a string.
Step 3. Then go to “https://practice-cybertekschool.herokuapp.com”
Step 4. And click on “Sign Up For Mailing List".
Step 5. Enter any valid name.
Step 6. Enter email from the Step 2.
Step 7. Click Sign Up
Step 8. Verify that following message is displayed: “Thank you for signing up. Click the button below to return to the home page.”
Step 9. Navigate back to the “https:// www.tempmailaddress.com/”
Step 10. Verify that you’ve received an email from
“do-not-reply@practice.cybertekschool.com”
Step 11. Click on that email to open it.
Step 12. Verify that email is from: “do-not- reply@practice.cybertekschool.com”
Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
*/
    @Test
    public void test6() {
        String fakeURL = "https://www.tempmailaddress.com/";
        driver.get(fakeURL);
        String email = driver.findElement(By.xpath("//span[@id='email']")).getText();
        String password = "passwordpassword";
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.name("full_name")).sendKeys("tomSmith");
        driver.findElement(By.name("email")).sendKeys(email, Keys.ENTER);

        BrowserUtils.wait(4);

        String actual = driver.findElement(By.tagName("h3")).getText();
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual, expected);

        driver.findElement(By.xpath("//i[text()=' Home']")).click();

        BrowserUtils.wait(4);
        driver.navigate().to(fakeURL);
        BrowserUtils.wait(5);
        WebElement mail = driver.findElement(By.xpath("//td[text()='Thanks for subscribing to practice.cybertekschool.com!']"));
        Assert.assertTrue(mail.isDisplayed());
        BrowserUtils.wait(4);
        driver.findElement(By.xpath("//tr[@class='hidden-xs hidden-sm klikaciRadek newMail']")).click();

        String emailActual = driver.findElement(By.id("predmet")).getText();
        String emailExpected = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(emailActual, emailExpected);


    }

    /*
 Test case #7
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. And click on “File Upload".
Step 3. Upload any file with .txt extension from your computer.
Step 4. Click “Upload” button.
Step 5. Verify that subject is: “File Uploaded!”
Step 6. Verify that uploaded file name is displayed.
Note: use element.sendKeys(“/file/path”) with specifying path to the file for uploading. Run this method against “Choose File” button.
*/

    @Test
    public void test7() {
        driver.get(URL);
        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(3);

        driver.findElement(By.id("file-upload")).sendKeys("/Users/semih/IdeaProjects/OnlineNewGitPractice2019/colors.txt");
        BrowserUtils.wait(3);
        driver.findElement(By.id("file-submit")).click();

        WebElement uploadMessage = driver.findElement(By.tagName("h3"));
        String actual = uploadMessage.getText();
        String expected = "File Uploaded!";
        Assert.assertEquals(actual, expected);
        Assert.assertTrue(uploadMessage.isDisplayed());

        WebElement file = driver.findElement(By.id("uploaded-files"));
        Assert.assertTrue(file.isDisplayed());
    }


    /*
Test case #8
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. And click on “Autocomplete”.
Step 3. Enter “United States of America” into country input box.
Step 4. Verify that following message is displayed: “You selected: United States of America”
     */
    @Test
    public void test8() {
        driver.get(URL);
        driver.findElement(By.linkText("Autocomplete")).click();
        BrowserUtils.wait(3);

        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.cssSelector("input[type='button']")).click();

        String actual = driver.findElement(By.id("result")).getText();
        String expected = "You selected: United States of America";
        Assert.assertEquals(actual, expected);
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }

}
