import PageObject.Cart;
import PageObject.RegisterPage;
import PageObject.Site;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class TestSuite {

    String username = "test34@2gmail.com";
    String password = "123456";

    @Test
    public void test1() throws Exception {

            Site site = new Site("Chrome", true, "http://automationpractice.com/", "Automation Practical", "Test Case 1");
            site.searchBox("Clothes");
            site.flush();
            site.shutdown();



    }

    @Test
    public void test2() throws Exception {
        Site mainpage = new Site("Chrome", true, "http://automationpractice.com/", "Automation Practical", "Test Case 2");
        String[] value = {"Blue", "Yellow", "Red"};
        for (String s : value) {
            mainpage.searchBox(s);
        }
        mainpage.shutdown();
        mainpage.flush();
    }


    @Test
        public void test3() throws Exception {
            Site mainpage = new Site("Chrome",true,"http://automationpractice.com/","Automation Practical","Test Case 2");
            String[] value = mainpage.textlist("src/main/resources/File.txt");
        for (String s : value) {
            mainpage.searchBox(s);
        }
        mainpage.shutdown();
        mainpage.flush();
        }

    @Test
        public void test4(){
            try {
                RegisterPage page = new RegisterPage("Chrome",true,"http://automationpractice.com/","Automation Practical","Test Case 2");
                page.login(username,password);
                page.searchBox("Blouse");
                page.shutdown();
                page.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    @Test
    public void test5(){
        try {
            Cart page = new Cart("Chrome",true,"http://automationpractice.com/","Automation Practical","Test Case 5");
            page.searchBox("blouse");
            page.addcarts();
            page.ProceedTocheckout();
            page.addItem();
            Assert.assertTrue(page.totalPrice());
            page.shutdown();
            page.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void test6(){
        try {
            Site mainpage = new Site("Chrome",true,"http://automationpractice.com/","Automation Practical","Test Case 6");
            Assert.assertEquals(mainpage.randomPageSelectionChecker(),true);
            mainpage.shutdown();
            mainpage.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
