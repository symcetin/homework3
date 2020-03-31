package tests.homework_04;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestCases {
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeMethod
    public void setup() {
       driver =  BrowserFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
    public void days() {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        BrowserUtils.wait(4);
        List<WebElement> checkBoxes = driver.findElements(By.tagName("input")); //all checkboxes

        Random random = new Random(); // create random class' object
        int cnt =0;

        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(checkBoxes.size()); // randomly select a number 0-to-7 (7 not included)

            if(checkBoxes.get(x).isEnabled()){
                checkBoxes.get(x).click(); //randomly click one of the days
                if(checkBoxes.get(x).getText().equals("Friday"))
                 cnt++;
            }
            System.out.println(checkBoxes.get(x).getText());
            checkBoxes.get(x).click();

        }
    }


    /*
    1.go to http://practice.cybertekschool.com/dropdown
    2.verify that dropdowns under Select your date of birth display current year, month,day
     */

    @Test
    public void today_date(){
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
    public void years_months_days(){
        driver.get("http://practice.cybertekschool.com/dropdown");
        wait = new WebDriverWait(driver, 10);

        Random random = new Random();
        int rndm = random.nextInt(100);

        Select yearSelect = new Select(driver.findElement(By.id("year")));
        Select monthSelect = new Select(driver.findElement(By.id("month")));
        Select daySelect = new Select(driver.findElement(By.id("day")));

        List<WebElement> years = yearSelect.getOptions();    //collect all year as a list
        List<WebElement> months = monthSelect.getOptions();  //collect all months
              //collect all days

        yearSelect.selectByIndex(rndm); //select randomly a year

        int selectedYear =0;
        for(WebElement each : years){
            if(each.isSelected()){
               selectedYear = Integer.valueOf(each.getText()); // find which year was selected
            }
        }

        //leap year formula
        boolean isLeapYear = ((selectedYear % 4 == 0) && (selectedYear % 100 != 0) || (selectedYear % 400 == 0));

        for (int i = 0; i < 12; i++) {
            monthSelect.selectByIndex(i); // select all months in order
            List<WebElement> days = daySelect.getOptions();

            System.out.println(months.get(i).getText());
            if(months.get(i).getText().equals("February")) {
                if(isLeapYear){
                    Assert.assertEquals(days.size(), 29);
                }else Assert.assertEquals(days.size(), 28);

            }else if(i<=6){
                if(i%2==0) {
                    Assert.assertEquals(days.size(), 31);
                }else Assert.assertEquals(days.size(), 30);

            }else if(i<=6){
                if(i%2==1) {
                    Assert.assertEquals(days.size(), 30);
                }else Assert.assertEquals(days.size(), 31);
            }
        }
    }

    /*
    1.go to https://www.amazon.com
    2.verify that defaultvalue of the All departmentsdropdown is All
    3.verify that options in the All departmentsdropdown are not sorted alphabetically
     */
    @Test
    public void departmentsSort(){
        driver.get("https://www.amazon.com");
        wait = new WebDriverWait(driver,10);

        WebElement iconAll = driver.findElement(By.cssSelector("span[class='nav-search-label']"));
        Assert.assertTrue(iconAll.isDisplayed());

        WebElement All = driver.findElement(By.cssSelector("select[class='nav-search-dropdown searchSelect']"));
        Select all = new Select(All);
        List<String> allList = BrowserUtils.getTextFromWebElements(all.getOptions());
        Set<String> sortedAllList = new TreeSet<>(allList);
        Assert.assertFalse(allList.equals(sortedAllList));

    }

    /*
    1.go to https://www.amazon.com/gp/site-directory
    2.verify that every main department name(indicated by blue rectangles in the picture)is present in the All departments dropdown (indicated by green rectangle in the picture)
     */
    @Test
    public void mainDepartments(){
        driver.get("https://www.amazon.com/gp/site-directory");
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleContains("Amazon"));
        List<String> allBlue = BrowserUtils.getTextFromWebElements(driver.findElements(By.cssSelector("h2[class='fsdDeptTitle']")));
        WebElement All = driver.findElement(By.cssSelector("select[class='nav-search-dropdown searchSelect']"));

        Select all = new Select(All);
        List<String> allGreen = BrowserUtils.getTextFromWebElements(all.getOptions());

        for(String eachBlue : allBlue){
           if(!allGreen.contains(eachBlue)){
               System.out.println(eachBlue);
               System.out.println("This main dep is not in All departments list");
           }

        }

       Assert.assertTrue(allGreen.containsAll(allBlue));
    }

    /*
    1.go to https://www.w3schools.com/
    2.find all the elements in the page with the tag a
    3.for each of those elements, if it is displayed on the page, print the text and the href of that element.
     */
    @Test
    public void links(){
        driver.get("https://www.w3schools.com/");
        wait = new WebDriverWait(driver, 10);
        List<WebElement> allATags = driver.findElements(By.tagName("a"));

        for(WebElement each : allATags){
            if(each.isDisplayed()){
                System.out.println("Text: " + each.getText()); //print text
                System.out.println("href: " + each.getAttribute("href")); // print
            }
        }
    }

    /*
    1.go to https://www.selenium.dev/documentation/en/
    2.find all the elements in the page with the tag a
    3.verify that all the links are valid
     */
    @Test
    public void valid_links(){
        driver.get("https://www.selenium.dev/documentation/en/");
        wait = new WebDriverWait(driver,10);
        List<WebElement> allATags = driver.findElements(By.tagName("a"));
        for(WebElement each : allATags){
            if(each.isEnabled()){
                System.out.println(each.getAttribute("href") + " is valid");
            }else
                System.out.println(each.getAttribute("href") + " is not valid");
        }
    }

    /*
    1.go to https://amazon.com
    2.search for "wooden spoon"
    3.click search
    4.remember the name and the price of a random result
    5.click on that random result
    6.verify default quantity of items is 1
    7.verify that the name and the price is the same as the one from step 5
    8.verify button"Add to Cart" is visible
     */
    @Test
    public void amazon_cart(){
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);

        BrowserUtils.wait(3);

        List<WebElement> items = driver.findElements(By.xpath("//span[@class='a-price']/span[@class='a-offscreen']"));


        Random random = new Random();
        int x = random.nextInt(items.size());

        x = x==0?1:x; // because results start from index 1

        String originName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])["+x+"]")).getText();
        String wholePricePart = driver.findElement(By.xpath("(//span[@class='a-price']/span[2]/span[2])["+x+"]")).getText();
        String fractionPricePart = driver.findElement(By.xpath("(//span[@class='a-price']/span[2]/span[3])["+x+"]")).getText();
        String originPrice = "$" + wholePricePart + "." + fractionPricePart;
        System.out.println(originPrice);

        driver.findElement(By.xpath("(//span[@class='a-price-fraction'])["+x+"]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Qty:']/following-sibling::span")).getText(), "1");
        Assert.assertEquals(driver.findElement(By.id("productTitle")).getText(), originName);
        Assert.assertEquals(driver.findElement(By.cssSelector("span[id='priceblock_ourprice']")).getText(), originPrice);
        Assert.assertTrue(driver.findElement(By.id("add-to-cart-button")).isDisplayed());
    }

    /*
    1.go to https://amazon.com
    2.search for "wooden spoon"
    3.click search
    4.remember name first result that has prime label
    5.select Prime checkbox on the left
    6.verify that name first result that has prime label is same as step 4
    7.check the last checkbox under Brand on the left
    8.verify that name first result that has prime label is different
     */

    @Test
    public void amazon_prime() {
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);
        // find first item name that has prime label
        WebElement firstPrimeName = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']//..//..//..//..//..//..//h2)[1]"));

        String name1 = firstPrimeName.getText();

        //click prime check box
        driver.findElement(By.xpath("//i[@class='a-icon a-icon-prime a-icon-medium']/../div/label/i")).click();

        String name2 = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2)[1]")).getText();
        Assert.assertEquals(name2, name1);

        driver.findElement(By.xpath("//div[@id='brandsRefinements']//ul/li[last()]//i")).click(); // last brand checkbox
        String name3 = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2)[1]")).getText();
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
        Assert.assertNotEquals(name1, name3);
    }


    /*
    MORE SPOONS1.go to https://amazon.com
    2.search for "wooden spoon"
    3.remember all Brand names on the left
    4.select Prime checkbox on the left
    5.verify that same Brand names are still displayedCHEAPSPOONS
    */
    @Test
    public void more_spoons(){
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);

        List<WebElement> brandList_1 = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> brandText_1 = BrowserUtils.getTextFromWebElements(brandList_1);

        //click prime
        driver.findElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[1]")).click();

        List<WebElement> brandList_2 = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> brandText_2 = BrowserUtils.getTextFromWebElements(brandList_2);

        Assert.assertEquals(brandText_1, brandText_2);
    }
    /*
   1.go to https://amazon.com
   2.search for "wooden spoon"
   3.click on Price option Under $25 on the left
   4.verify that all results are cheaper than $25
    */
    @Test
    public void cheap_spoons(){
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);
        driver.findElement(By.linkText("Under $25")).click();

        //we collect only dollar values from the price of every item
        List<WebElement> prices = driver.findElements(By.className("a-price-whole"));
        //we convert collection of web elements into collection of strings
        List<String> pricesText = BrowserUtils.getTextFromWebElements(prices);
        System.out.println(pricesText);
        for (String price : pricesText) {

            //we convert every price as a string into integer
            int priceConverted = Integer.parseInt(price);

            //checking if the price of every item is under 25
            Assert.assertTrue(priceConverted < 25);
        }
    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
       driver.quit();
    }

}

