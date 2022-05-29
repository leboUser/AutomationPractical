package PageObject;

import org.openqa.selenium.By;

public class Cart extends Site {

    private String iconPlusXPath = "//i[@class='icon-plus']";
    private String priceclass = "price";
    private String totalPriceXPath = "//td[@class='cart_total']//span[@class='price']";
    private String numberItemName = "quantity_2_7_0_0";

    public Cart(String browsersType, boolean windowSize, String url, String testSuite, String testcase) throws Exception {
        super(browsersType, windowSize, url, testSuite, testcase);
    }

    public void addItem( ){
        hoverElement(By.xpath(iconPlusXPath),true,"Hover on to item");
        clickElement(By.name(iconPlusXPath),true,"Clicked add button");


    }


    public boolean totalPrice(){
        String total_price = getTextFromElement(By.xpath(totalPriceXPath),false,"Extracting total price").replace("$","");
        String unit_price = getTextFromElement(By.xpath(priceclass),false,"Extracting price").replace("$","");
        String number_of_items = getTextFromElement(By.xpath(numberItemName),false,"Extracting number of items");

        int totalPrice = Integer.parseInt(unit_price) * Integer.parseInt(number_of_items);
        return Integer.parseInt(total_price) == totalPrice;

    }
}
