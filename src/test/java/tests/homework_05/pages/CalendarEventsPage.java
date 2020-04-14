package tests.homework_05.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.BrowserUtils;

import java.util.List;



public class CalendarEventsPage extends AbstractPageBase {

    @FindBy(xpath = "//td[text()='Testers Meeting']/..//div//a")
    private WebElement treeDots;

    @FindBy(xpath = "//td[text()='Testers Meeting']/..//div//a/following-sibling::ul//a")
    private List<WebElement> viewEditDeleteList;


    @FindBy(css = "[class='fa-cog hide-text']")
    protected WebElement gridSettings;

    @FindBy(xpath = "//table[@class='grid table-hover table table-condensed']//tbody//tr")
    protected List<WebElement>gridCheckboxes;

    @FindBy(xpath = "//label[text()='Title']")
    protected WebElement gridTitle;

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(css = "[class='btn-success btn dropdown-toggle']")
    private WebElement saveAndClose;

    @FindBy(css = "[class='main-group action-button dropdown-item']")
    private List<WebElement> saveAndCloseList;

    @FindBy(css = "[title='Cancel']")
    private WebElement cancel;

    @FindBy(css = "[class='oro-subtitle']")
    private WebElement pageSubtitle;


    @FindBy(className="select2-chosen")
    protected WebElement owner;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    private WebElement endDate;

    @FindBy(css="[id^='time_selector_oro_calendar_event_form_start']")
    private WebElement startTime;

    @FindBy(css="[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;

    @FindBy(xpath = "//li[text()='9:00 PM']")
    private List<WebElement> timeList;

    @FindBy(css = "[id^='oro_calendar_event_form_allDay']")
    private WebElement allDayEvent;

    @FindBy(css = "[id^='recurrence-repeat']")
    private WebElement repeat;

    @FindBy(css = "[id^='recurrence-repeats']")
    private WebElement repeats;

    @FindBy(xpath = "(//input[@type='radio'])[1]")
    private WebElement repeatEvery;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    private WebElement ends_never;


    @FindBy(css = "[data-related-field='occurrences']")
    private WebElement occurrences;

    @FindBy(xpath = "(//input[@type='radio'])[5]")
    private WebElement byRadio;

    @FindBy(xpath = "(//input[@placeholder='Choose a date'])[3]")
    private WebElement chooseDate;

    @FindBy(css = "[data-handler='selectMonth']")
    private WebElement selectMonth;

    @FindBy(css = "[data-handler='selectYear']")
    private WebElement selectYear;





    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//div")
    private WebElement generalInfoDescription;

    public WebElement getTreeDots(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(treeDots));
        return treeDots;
    }

    public List<WebElement> treeDotsOptions(){
        return viewEditDeleteList;
    }


    public void deselectCheckBoxes(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.elementToBeClickable(gridSettings)).click();

        for (int x = 0; x < gridCheckboxes.size(); x++) {
           if(x!=0) {
               gridCheckboxes.get(x).click();
           }
        }

    }

    public WebElement getGridTitle(){
        return gridTitle;
    }

    public List<WebElement> getSaveAndCloseList(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.elementToBeClickable(saveAndCloseList.get(0)));

        saveAndCloseList.add(driver.findElement(By.cssSelector("[class='main-group action-button dropdown-item']")));
        return saveAndCloseList;
    }

    public void clickCancel(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
    }

    public WebElement getPageSubtitle(){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.elementToBeClickable(pageSubtitle));
        return pageSubtitle;
    }

    public void choseTime(String time){
        BrowserUtils.waitForPageToLoad(25);
        wait.until(ExpectedConditions.elementToBeClickable(startTime));
        startTime.click();
        WebElement timeToChoose = driver.findElement(By.xpath("//li[text()='"+time+"']"));
        timeToChoose.click();
    }

    public void choseDate(String date){
        BrowserUtils.waitForPageToLoad(25);
//        wait.until(ExpectedConditions.elementToBeClickable(startDate)).click();
        WebElement dateToChoose = driver.findElement(By.xpath("//a[text()='"+date+"']"));
        dateToChoose.click();
        BrowserUtils.wait(2);
    }

    public WebElement getStartTimeWeb(){
        return startTime;
    }

    public WebElement getEndTimeWeb(){
        return startTime;
    }

    public WebElement getStartDateWeb(){
        return startDate;
    }

    public WebElement getEndDateWeb(){
        return endDate;
    }


    public String getGeneralInfoTitleText(){
        BrowserUtils.waitForPageToLoad(20);
        return generalInfoTitle.getText();
    }

    public String getGeneralInfoDescriptionText(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div")));
        return generalInfoDescription.getText();
    }

    public String getStartDate(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        BrowserUtils.scrollTo(startDate);
        return startDate.getAttribute("value");
    }

    public String getStartTime(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startTime)); // we have to put not to fail
        return startTime.getAttribute("value");
    }
    public String getEndTime(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }

    public void clickAllDayEvent(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(allDayEvent)).click();
        BrowserUtils.wait(2);
    }

    public WebElement clickRepeat() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(repeat)).click();
        return repeat;
    }

    public WebElement getRepeats(){
        return repeats;
    }

    public void clickOnSaveAndClose(){
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
    }

    /**
     * This method is for click action
     */
    public void clickToCreateCalendarEvent(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtils.wait(20);
    }


    public WebElement getSummary(String text){
        WebElement summary = driver.findElement(By.xpath("//span[text()='"+text+"']"));
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(summary));
        return summary;
    }

    public WebElement getRepeatEvery(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(repeatEvery));
        return repeatEvery;
    }

    public WebElement getEnds_never(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(ends_never));
        return ends_never;
    }

    public void putOccurrences(String number){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(occurrences)).click();
        occurrences.sendKeys("10");
    }

    public void clickByRadioButton(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(byRadio)).click();
    }

    public void clickDate(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(chooseDate)).click();
    }

    public WebElement clickMonth(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(selectMonth));
        return selectMonth;
    }

    public WebElement clickYear(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(selectYear));
        return selectYear;
    }

    public void selectDay(String day){
        BrowserUtils.waitForPageToLoad(25);
        WebElement daySelect = driver.findElement(By.cssSelector("input[value='"+day+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(daySelect)).click();

    }




}