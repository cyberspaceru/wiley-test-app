package com.wiley.tatsiy.testapp.runners;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import ru.sbtqa.tag.cucumber.TagCucumber;

/**
 * Created by cyberspace on 1/23/2018.
 */

@RunWith(TagCucumber.class)
@CucumberOptions(monochrome = true, format = {"pretty"},
        glue = {"com.wiley.tatsiy.testapp.steps"},
        features = {"src/test/resources/features/"})

public class CucumberTest {

}
