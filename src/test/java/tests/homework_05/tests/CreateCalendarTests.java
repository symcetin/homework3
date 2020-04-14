package tests.homework_05.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.homework_05.pages.CalendarEventsPage;
import tests.homework_05.pages.LoginPage;
import utilities.BrowserUtils;
import utilities.DateTimeUtilities;
import utilities.Driver;

import java.util.List;

public class CreateCalendarTests extends AbstractTestBase {

    private WebDriver driver;

    /*
    Test Case #1
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Hover on three dots “...” for “Testers meeting” calendar event
    5.Verify that “view”, “edit” and “delete” options are available
     */

    @Test
    public void verifyTreeDots(){
        driver = Driver.getDriver();
        test = report.createTest("Verify tree dots is displayed ");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage eventsPage = new CalendarEventsPage();

        loginPage.login();
        eventsPage.navigateTo("Activities", "Calendar Events");
        actions = new Actions(driver);
        actions.moveToElement(eventsPage.getTreeDots()).build().perform();
        BrowserUtils.wait(4);
       // eventsPage.treeDotsOptions().get(0).click();
        for(WebElement each : eventsPage.treeDotsOptions()){
            Assert.assertTrue(each.isDisplayed());
        }
    }
    /*
    Test Case #2
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Grid Options” button
    5.Deselect all options except “Title”
    6.Verify that “Title” column still displayed
     */
    @Test
    public void verifyTitleColumn(){
        test = report.createTest("Verify title column is appear ");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage eventsPage = new CalendarEventsPage();

        loginPage.login();
        eventsPage.navigateTo("Activities", "Calendar Events");
        eventsPage.deselectCheckBoxes();
        Assert.assertTrue(eventsPage.getGridTitle().isDisplayed());
    }

    /*
Test Case #3
1.Go to “https://qa1.vytrack.com/"
2.Login as a store manager
3.Navigate to “Activities -> Calendar Events”
4.Click on “Create Calendar Event” button
5.Expand “Save And Close” menu
6.Verify that “Save And Close”, “Save And New” and “Save” options are available
     */
    @Test
    public void verifySaveAndCloseOptions() {
        test = report.createTest("Verify save and close options");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage eventsPage = new CalendarEventsPage();

        loginPage.login();
        eventsPage.navigateTo("Activities", "Calendar Events");
        eventsPage.clickToCreateCalendarEvent();
        eventsPage.clickOnSaveAndClose();
        for(WebElement each : eventsPage.getSaveAndCloseList()){
            Assert.assertTrue(each.isDisplayed());
        }
    }

    /*
    Test Case #4
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Then, click on “Cancel” button
    6.Verify that “All Calendar Events” page subtitle is displayed
     */
    @Test
    public void verifyPageSubtitle() {
        test = report.createTest("Verify page subtitle");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage eventsPage = new CalendarEventsPage();

        loginPage.login();
        eventsPage.navigateTo("Activities", "Calendar Events");
        eventsPage.clickToCreateCalendarEvent();
        eventsPage.clickCancel();
        Assert.assertTrue(eventsPage.getPageSubtitle().isDisplayed());
    }

    /*
    Test Case #5
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Verify that difference between end and start time is exactly 1 hour
     */

    @Test
    public void timeDifferenceTest() {
        test = report.createTest("Verify time difference");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        loginPage.login();

        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();

        String startTime = calendarEventsPage.getStartTime(); //get start time
        String endTime = calendarEventsPage.getEndTime(); //get end time
        String format = "h:mm a";//format 5:15 AM for example

        long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);

        Assert.assertEquals(actual, 1, "Time difference is not correct");

        test.pass("Time difference verified");

    }

    /*
    Test Case #6
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Select “9:00 PM” as a start time
    6.Verify that end time is equals to “10:00 PM”
     */
    @Test
    public void timeDifferenceTest2() {
        test = report.createTest("Verify time difference");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        loginPage.login();

        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();

        calendarEventsPage.choseTime("9:00 PM");
        String endTime = calendarEventsPage.getEndTime(); //get end time
        Assert.assertEquals(endTime, "10:00 PM");

        test.pass("Time difference verified");

    }

    /*
    Test Case #7
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Select “All-Day Event” checkbox
    6.Verify that “All-Day Event” checkbox is selected
    7.Verify that start and end time input boxes are not displayed
    8.Verify that start and end date input boxes are displayed
     */
    @Test
    public void verifyTimeAndDate() {
        test = report.createTest("Verify time and date boxes status");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        loginPage.login();

        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.clickAllDayEvent();
        Assert.assertTrue(calendarEventsPage.getEndDateWeb().isDisplayed());
        Assert.assertTrue(calendarEventsPage.getStartDateWeb().isDisplayed());
        Assert.assertFalse(calendarEventsPage.getStartTimeWeb().isDisplayed());
        Assert.assertFalse(calendarEventsPage.getEndTimeWeb().isDisplayed());
    }


    /*
    Test Case #8 1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Select “Repeat” checkbox
    6.Verify that “Repeat” checkbox is selected
    7.Verify that “Daily” is selected by default and following options are available in “Repeats” drop-down:
     */
    @Test
    public void verifyRepeatCheckbox() {
        test = report.createTest("Verify repeat checkbox");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();
        Select repeats = new Select(calendarEventsPage.getRepeats());
        Assert.assertTrue(calendarEventsPage.clickRepeat().isSelected());
        Assert.assertEquals(repeats.getFirstSelectedOption().getText(), "Daily");

        String[] arr = {"Daily" ,"Weekly","Monthly", "Yearly"};
        List<String> repeatsTexts = BrowserUtils.getTextFromWebElements(repeats.getOptions());

        for (int x = 0; x < arr.length; x++) {
            Assert.assertEquals(repeatsTexts.get(x), arr[x]);

        }

    }

    /*
    Test Case #9
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Select “Repeat” checkbox
    6.Verify that “Repeat” checkbox is selected
    7.Verify that “Repeat Every” radio button is selected
    8.Verify that “Never” radio button is selected as an “Ends” option.
    9.Verify that following message as a summary is displayed: “Summary: Daily every 1 day”
     */
    @Test
    public void verifyClickedRepeatOptions() {
        test = report.createTest("Verify repeat checkbox");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();
        Select repeats = new Select(calendarEventsPage.getRepeats());
        Assert.assertTrue(calendarEventsPage.clickRepeat().isSelected());
        Assert.assertTrue(calendarEventsPage.getRepeatEvery().isSelected());
        Assert.assertTrue(calendarEventsPage.getEnds_never().isSelected());
        Assert.assertTrue(calendarEventsPage.getSummary("Daily every 1 day").isDisplayed());

    }

    /*
    Test Case #10
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Select “Repeat” checkbox
    6.Select “After 10 occurrences” as an “Ends” option.
    7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences”
     */
    @Test
    public void verifyOccurrencesSummary() {
        test = report.createTest("Verify occurrences summary");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.clickRepeat();
        calendarEventsPage.putOccurrences("10");
        Assert.assertTrue(calendarEventsPage.getSummary("Daily every 1 day").isDisplayed());
        Assert.assertTrue(calendarEventsPage.getSummary(", end after 10 occurrences").isDisplayed());
    }

    /*
    Test Case #11
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Select “Repeat” checkbox
    6.Select “By Nov 18, 2021” as an “Ends” option.
    7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”
     */

    @Test
    public void verifyByDateTimeSummary() {
        test = report.createTest("Verify By date time summary");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.clickRepeat();
        calendarEventsPage.clickByRadioButton();
        calendarEventsPage.clickDate();

        Select month = new Select(calendarEventsPage.clickMonth());
        month.selectByVisibleText("Nov");
        Select year = new Select(calendarEventsPage.clickYear());
        year.selectByVisibleText("2021");
        calendarEventsPage.choseDate("18");

        Assert.assertTrue(calendarEventsPage.getSummary("Daily every 1 day").isDisplayed());
        Assert.assertTrue(calendarEventsPage.getSummary(", end by Nov 18, 2021").isDisplayed());
    }
    /*
    Test Case #12
    1.Go to “https://qa1.vytrack.com/"
    2.Login as a store manager
    3.Navigate to “Activities -> Calendar Events”
    4.Click on “Create Calendar Event” button
    5.Select “Repeat” checkbox
    6.Select “Weekly” options as a “Repeat” option
    7.Select “Monday and Friday” options as a “Repeat On” options
    8.Verify that “Monday and Friday” options are selected
    9.Verify that following message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday”
     */

    @Test
    public void verifyWeeklyOptionSummary() {
        test = report.createTest("Verify weekly option summary");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.clickRepeat();
        Select repeats = new Select(calendarEventsPage.getRepeats());
        repeats.selectByVisibleText("Weekly");

        calendarEventsPage.selectDay("monday");
        calendarEventsPage.selectDay("friday");
        Assert.assertTrue(calendarEventsPage.getSummary("Weekly every 1 week on Monday, Friday").isDisplayed());
    }


}
