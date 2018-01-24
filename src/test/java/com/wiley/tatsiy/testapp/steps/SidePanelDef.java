package com.wiley.tatsiy.testapp.steps;

import com.wiley.tatsiy.testapp.elements.SidePanel;
import com.wiley.tatsiy.testapp.elements.interfaces.ISubItemHolder;
import cucumber.api.java.en.And;
import org.junit.Assert;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class SidePanelDef extends StepsDefinitionHelper {

    @And("^side panel \"(.*?)\" has ([0-9].*) item(?:s|)$")
    public void subjectsAmount(String title, int amount) throws PageException {
        SidePanel sidePanel = getElementByTitle(title);
        long actualAmount = sidePanel.items().count();
        Assert.assertTrue("Navigation menu item with name \"" + title + "\" doesn't have " + amount + " (actual: " + actualAmount + ") sub-headers.", amount == actualAmount);
    }

    @And("^side panel \"(.*?)\" contains \"(.*?)\" item(?:s|)$")
    public void subjectsContains(String title, String list) throws PageException {
        SidePanel sidePanel = getElementByTitle(title);
        List<String> expected = Arrays.stream(list.split(",")).map(String::trim).collect(Collectors.toList());
        for (String e : expected) {
            if (sidePanel.titlies().filter(e::equalsIgnoreCase).findFirst().orElse(null) == null) {
                Assert.fail("Side panel with name \"" + title + "\" not contained \"" + e + "\" item.");
            }
        }
    }

}
