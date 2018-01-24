package com.wiley.tatsiy.testapp.steps;

import com.wiley.tatsiy.testapp.elements.product.Product;
import com.wiley.tatsiy.testapp.elements.product.ProductTableRow;
import com.wiley.tatsiy.testapp.elements.product.ProductsList;
import cucumber.api.java.en.And;
import org.junit.Assert;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;

/**
 * Created by cyberspace on 1/24/2018.
 */
public class ProductsDef extends StepsDefinitionHelper {

    @And("^products list \"(.*?)\" has ([1-9].*) products$")
    public void checkProductsAmount(String title, int amount) throws PageException {
        ProductsList productsList = getElementByTitle(title);
        long actual = productsList.products().count();
        boolean conditionResult = actual == amount;
        Assert.assertTrue("Products list with name \"" + title + "\" has not " + amount + "(actual: " + actual + ") products.", conditionResult);
    }

    @And("^products list \"(.*?)\" has at least one “Add to Cart” button for each product.$")
    public void checkProductsAddToCardButtons(String title) throws PageException {
        ProductsList productsList = getElementByTitle(title);
        long expected = productsList.products().count();
        long actual = productsList.products()
                .filter(x -> x.rows().filter(ProductTableRow::addToCartButtonExists).findFirst().orElse(null) != null)
                .count();
        boolean conditionResult = actual == expected;
        Assert.assertTrue("Not all products of \"" + title + "\" contains at least one “Add to Cart” button. Actual value is " + actual + ".", conditionResult);
    }

}
