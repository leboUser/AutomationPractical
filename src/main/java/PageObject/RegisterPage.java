package PageObject;
import org.openqa.selenium.By;



 public class RegisterPage extends Site {

    private String emailtextBoxName = "email";
     private String passwordtextName = "passwd";
     private String submitLoginButtonName = "SubmitLogin";
     private String siginButtonClass="login";

     public RegisterPage(String browsersType, boolean windowSize, String url, String testSuite, String testcase) throws Exception {
         super(browsersType, windowSize, url, testSuite, testcase);
     }


    public void login(String username, String password){
        clickElement(By.className(siginButtonClass),true,"Sigin");
        enterText(By.name(emailtextBoxName),username,true,"enter username");
        enterText(By.name(passwordtextName),password,true,"enter password");
        clickElement(By.name(submitLoginButtonName),true,"Clicked submit");
    }





 }
