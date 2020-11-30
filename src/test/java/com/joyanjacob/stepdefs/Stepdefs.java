package com.joyanjacob.stepdefs;

import com.joyanjacob.base.BrowserGlue;
import com.joyanjacob.model.SearchForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class Stepdefs extends BrowserGlue {

    public Properties props;
    FileInputStream is;
    String propFilePath;
    SearchForm searchForm;

    public Stepdefs() throws IOException {
        props = new Properties();
        propFilePath = new File("").getAbsolutePath() + "/src/main/resources/app.properties";
        is = new FileInputStream(propFilePath);
        props.load(is);
        driver = getDriver();
        searchForm = new SearchForm(driver);
    }

    @Given("I view the home page")
    public void iViewTheHomePage() throws InterruptedException {
        driver.get(props.getProperty("home_page_url"));
        Thread.sleep(3000);
    }

    @And("I search for {}")
    public void iSearchForSummerDresses(String searchTerm) throws InterruptedException {
        searchForm.enterSearchTerm(searchTerm);
        searchForm.submitSearch();
        Thread.sleep(3000);
    }

    @And("the search results page is displayed")
    public void theSearchResultsPageIsDisplayed() {
        assertEquals("Couldn't navigate to search results page",
                driver.getTitle(), "Search - My Store");
    }

    @And("I view the overlay for product listing {int} on the page")
    public void iNavigateToProductDetailsPageForListingOnThePage(int listingIndex) throws InterruptedException {
        driver.findElement(By.className("product_list")).findElement(By.cssSelector("li:nth-child("+listingIndex+")")).findElement(By.className("product_img_link")).click();
        Thread.sleep(3000);
    }

    @And("I add the product to cart")
    public void iAddTheProductToCart() throws InterruptedException {
        driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(0));
        WebElement inPopUp = driver.findElement(By.id("add_to_cart"));
        Actions actions = new Actions(driver);
        actions.moveToElement(inPopUp);
        actions.click().build().perform();
        Thread.sleep(3000);
    }

    @And("I proceed to checkout")
    public void iProceedToCheckout() throws InterruptedException {
        driver.findElement(By.linkText("Proceed to checkout")).click();
        Thread.sleep(3000);
    }

    @And("I continue shopping to go back to search results page")
    public void iContinueShoppingToGoBackToSearchResultsPage() throws InterruptedException {
        driver.findElement(By.className("continue")).click();
        Thread.sleep(3000);
    }

    @Then("the shopping cart summary page must be displayed")
    public void theShoppingCartSummaryPageMustBeDisplayed() {
        assertEquals("Shopping cart summary page not loaded", driver.getTitle(), "Order - My Store");
    }

    @And("I proceed to checkout from shopping cart summary page")
    public void iProceedToCheckoutFromShoppingCartSummaryPage() throws InterruptedException {

        driver.findElement(By.className("standard-checkout")).click();
        Thread.sleep(3000);
    }

    @Then("the login page is displayed")
    public void theLoginPageIsDisplayed() {
        assertEquals("Login page not displayed on checking out as anonymous user", driver.getTitle(), "Login - My Store");
        assertTrue("Login form elements not displayed as expected",driver.findElement(By.id("email")).isDisplayed());
        assertTrue("Login form elements not displayed as expected", driver.findElement(By.id("passwd")).isDisplayed());
    }
}
