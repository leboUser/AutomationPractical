package PageObject;

import Controller.Selenium;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.util.Random;

public class Site extends Selenium {

   private String searchTextbox = "search_query";
   private String ItemimageContainer = "//div[@class='product-image-container']";
   private String addCartButton = "//span[text()='Add to cart']";
   private String submitSearchbutton = "submit_search";
   private String proceedTocheckoutSpan ="//span[contains(text(),'Proceed to checkout')]";
   private String iconPlusClass = "//i[@class='icon-plus']";
   //Categories
   private String categoriesDivlink = "//div[contains(text(),'Categories')]";
   private String dressesAlink = "//a[text()='Dresses'][2]";
   private String womenALink = "//a[contains(text(),'Women')]";
   private String t_shirtlink = "//a[contains(text(),'T-shirts')][2]";

   //SubCategories
    private String subCategories = "//h5//a[contains(text(),'Tops')]";
    private String dressesText ="//span[contains(text(),'Dresses ')]";
    private String t_shirtText = "//span[contains(text(),'T-shirts ')]";
    private String tops= "//span[contains(text(),'Tops ')]";



    public Site(String browsersType, boolean windowSize, String url, String testSuite, String testcase) throws Exception {
        super(browsersType, windowSize, url, testSuite, testcase);
    }


    public void searchBox(String searchText ){
        enterText(By.name(searchTextbox),searchText,true,"Entering on Search bar "+searchText);
        clickElement(By.name(submitSearchbutton),true,"Clicked Search button");

    }
    public void addcarts(){
        hoverElement(By.xpath(ItemimageContainer),true,"Hover on to item");
        clickElement(By.xpath(addCartButton),true,"Clicked add Cart Button");
    }

    public void ProceedTocheckout() {
        pause(5);
        clickElement(By.xpath(proceedTocheckoutSpan),true,"Clicked Proceed to checkout Button");
    }

    public String[] textlist (String file) throws FileNotFoundException {
       String texts = fileReader(file);
       return texts.split(",");
    }


    public Boolean randomPageSelectionChecker() {
        Random number = new Random();
        switch (number.nextInt(3)+1) {
            case 1:
                clickElement(By.xpath(dressesAlink), true, "Click Dresses Link");
                return getTextFromElement(By.xpath(dressesText), true, "Able to view Text").contentEquals("dresses");

            case 2:
                clickElement(By.xpath(womenALink), true, "Click Women Link");
                clickElement(By.xpath(subCategories), true, "Clicked Tops button");
                return getTextFromElement(By.xpath(tops), true, "Able to view Text").contentEquals("TOPS");

            case 3:
                clickElement(By.xpath(t_shirtlink), true, "Click Shirt Link");
                return getTextFromElement(By.xpath(t_shirtText), true, "Able to view Text").contentEquals("T-shirts");

            default:
                randomPageSelectionChecker();

        }
        return false;
    }



}

