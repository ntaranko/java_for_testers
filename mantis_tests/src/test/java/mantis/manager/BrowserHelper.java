package mantis.manager;

import org.openqa.selenium.By;

public class BrowserHelper extends HelperBase{
    public BrowserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void signUp(String username, String email){
        manager.driver().get(manager.property("web.baseUrl"));
        manager.driver().findElement(By.className("back-to-login-link")).click();
        manager.driver().findElement(By.id("username")).sendKeys(username);
        manager.driver().findElement(By.id("email-field")).sendKeys(email);
        manager.driver().findElement(By.cssSelector("input[type='submit']")).click();
    }

    public void openLink(String url){
        manager.driver().get(url);
    }

    public void setPassword(String username, String password) {
        manager.driver().findElement(By.id("realname")).sendKeys(username);
        manager.driver().findElement(By.id("password")).sendKeys(password);
        manager.driver().findElement(By.id("password-confirm")).sendKeys(password);
        manager.driver().findElement(By.cssSelector("span.bigger-110")).click();
    }
}
