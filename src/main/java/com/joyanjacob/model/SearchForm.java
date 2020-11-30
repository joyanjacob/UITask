package com.joyanjacob.model;

import com.joyanjacob.base.BrowserGlue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class SearchForm extends BrowserGlue {

    private By searchFormId = By.id("search_query_top");
    private By submitSearch = By.className("button-search");

    public SearchForm(WebDriver driver) throws IOException {
        super();
        this.driver = driver;
    }

    public void enterSearchTerm(String searchTerm){
        driver.findElement(searchFormId).sendKeys(searchTerm);
    }

    public void submitSearch(){
        driver.findElement(submitSearch).click();
    }
//
//    public void clickRemoteAccess(){
//        driver.findElement(remoteAccess).click();
//    }
//
//    public void clickShoppingCartLink() throws InterruptedException {
//        driver.findElement(shoppingCart).click();
//        Thread.sleep(3000);
//    }
//
//    public void clickAdvertiseLink() { driver.findElement(advertiseLink).click(); }
//
//    public void clickContactAndSupportLink() { driver.findElement(contactAndSupportLink).click(); }
//
//    public void clickTermsAndConditionsLink() { driver.findElement(termsAndConditionsLink).click(); }
//
//    public void clickPrivacyPolicyLink() { driver.findElement(privacyPolicy).click(); }
//
//    public void clickRelxLogoLink() { driver.findElement(relxLogo).click(); }
//
//    public void clickUseOfCookiesLink() { driver.findElement(useOfCookies).click(); }
}
