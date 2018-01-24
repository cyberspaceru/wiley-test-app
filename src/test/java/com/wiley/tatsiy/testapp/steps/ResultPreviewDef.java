package com.wiley.tatsiy.testapp.steps;

import com.wiley.tatsiy.testapp.elements.ResultPreview;
import cucumber.api.java.en.And;
import org.junit.Assert;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class ResultPreviewDef extends StepsDefinitionHelper {

    @And("^result preview \"(.*?)\" has ([1-9].*) list items$")
    public void resultPreviewHasAmount(String title, int amount) throws PageException {
        ResultPreview preview = getElementByTitle(title);
        long actual = preview.items().count();
        boolean conditionResult = actual == amount;
        Assert.assertTrue("Result preview with name \"" + title + "\" has not " + amount + "(actual: " + actual + ") list items.", conditionResult);
    }

    @And("^result preview \"(.*?)\" contains \"(.*?)\" list items$")
    public void resultPreviewContainsItems(String title, String list) throws PageException {
        ResultPreview preview = getElementByTitle(title);
        List<String> expected = Arrays.asList(list.split(","));
        // boolean conditionResult = preview.items().collect(Collectors.toList()).containsAll(expected);
        // I can't use it because I need to get a certain error message.
        for (String e : expected) {
            e = e.trim();
            if (preview.titles().filter(e::equalsIgnoreCase).findFirst().orElse(null) == null) {
                Assert.fail("Result preview with name \"" + title + "\" not contains \"" + e + "\" list item.");
            }
        }
    }

    @And("^result preview \"(.*?)\" has ([1-9].*) list items which starting with \"(.*?)\"$")
    public void resultPreviewItemsStartingWith(String title, int amount, String prefix) throws PageException {
        ResultPreview preview = getElementByTitle(title);
        long actual = preview.titles().filter(x -> x.startsWith(prefix)).count();
        boolean conditionResult = actual == amount;
        Assert.assertTrue("Result preview with name \"" + title + "\" has not " + amount + " (actual: " + actual + ") list items which starting with \"" + prefix + "\".", conditionResult);
    }

    @And("^result preview \"(.*?)\" has ([1-9].*) product items which contains \"(.*?)\" in titles$")
    public void resultPreviewHasProductItemsWhichContains(String title, int amount, String prefix) throws PageException {
        ResultPreview preview = getElementByTitle(title);
        long actual = preview.products().filter(x -> x.getTitle().contains(prefix)).count();
        boolean conditionResult = actual == amount;
        Assert.assertTrue("Result preview with name \"" + title + "\" has not " + amount + " (actual: " + actual + ") product items which contains \"" + prefix + "\".", conditionResult);
    }

}
