package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TechGlobalFrontEndPage extends TechGlobalBasePage {
    public TechGlobalFrontEndPage() {
        super();
    }


    @FindBy(css = "div[class*=CardGrids]>div")
    public List<WebElement> cards;

    public void clickOnCard(String cardName) {
        for(WebElement card : cards) {
            if (card.getText().equals(cardName)) {
                card.click();
                break;
            }
        }
    }
}
