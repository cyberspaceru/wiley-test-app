package com.wiley.tatsiy.testapp.steps;

import com.wiley.tatsiy.testapp.elements.interfaces.ISubItemHolder;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class SubItemHolderDef extends StepsDefinitionHelper {

    @And("^element \"(.*?)\" has ([0-9].*) sub-item(?:s|)$")
    public void checkAmount(String title, int amount) throws PageException, InterruptedException {
        ISubItemHolder subItemHolder = getElementByTitle(title);
        int actualAmount = subItemHolder.getItems().size();
        Assert.assertTrue("Navigation menu item with name \"" + title + "\" doesn't have " + amount + " (actual: " + actualAmount + ") sub-headers.", amount == actualAmount);
    }

    @And("^element \"(.*?)\" contains \"(.*?)\" sub-item(?:s|)$")
    public void checkEntry(String title, String list) throws PageException, InterruptedException {
        ISubItemHolder subItemHolder = getElementByTitle(title);
        List<String> actual = subItemHolder.getTitles();
        List<String> expected = Arrays.stream(list.split(",")).map(String::trim).collect(Collectors.toList());
        for (String e : expected) {
            if (actual.stream().filter(e::equalsIgnoreCase).findFirst().orElse(null) == null) {
                Assert.fail("Sub-header with name \"" + e + "\" is not contained in \"" + title + "\".");
            }
        }
    }

    @And("^click on the element by the items chain: (.*?)$")
    public void chainClickByFocus(String chain) throws PageException, InterruptedException {
        List<String> elements = Arrays.asList(chain.split(">>>"));
        WebElement cursor = getElementByTitle(elements.get(0).trim());
        int i = 1;
        do {
            if (cursor instanceof ISubItemHolder) {
                ISubItemHolder subItemHolder = (ISubItemHolder)cursor;
                focusTo(subItemHolder.getAnchor());
                cursor = subItemHolder.getSubItem(elements.get(i).trim());
            } else  {
                Assert.fail("Can't follow by the chain, because some middle element hasn't implemented ISubItemHolder (or null): " + chain);
            }
        } while (++i < elements.size());
        Assert.assertNotNull("Can't follow by the chain, because the last element is null: " + chain, cursor);
        cursor.click();
    }


}
