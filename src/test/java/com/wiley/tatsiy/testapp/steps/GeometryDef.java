package com.wiley.tatsiy.testapp.steps;

import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class GeometryDef extends StepsDefinitionHelper {

    @And("^element \"(.*?)\" is above an another element \"(.*?)\"$")
    public void elementUnderAnother(String upperTitle, String lowerTitle) throws PageException {
        WebElement upper = getElementByTitle(upperTitle);
        WebElement lower = getElementByTitle(lowerTitle);
        int yBottomOfUpper = upper.getLocation().getY() + upper.getSize().height;
        int yTopOfLower = lower.getLocation().getY();
        int difference = yBottomOfUpper - yTopOfLower;
        Assert.assertTrue("Element \"" + upperTitle + "\" (Y: " + yBottomOfUpper + ") isn't above of an another element \"" + lowerTitle + "\" (Y: " + yTopOfLower + ").", difference <= 0);
    }

}
