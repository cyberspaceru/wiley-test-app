package com.wiley.tatsiy.testapp.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.reflections.Reflections;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.allure.TagAllureReporter;
import ru.sbtqa.tag.allurehelper.OnFailureScheduler;
import ru.sbtqa.tag.allurehelper.ParamsHelper;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.drivers.TagWebDriver;
import ru.sbtqa.tag.pagefactory.exceptions.FactoryRuntimeException;
import ru.sbtqa.tag.pagefactory.stepdefs.SetupStepDefs;
import ru.sbtqa.tag.pagefactory.support.Environment;
import ru.sbtqa.tag.qautils.properties.Props;
import ru.sbtqa.tag.qautils.reflect.ClassUtilsExt;
import ru.sbtqa.tag.qautils.reflect.FieldUtilsExt;
import ru.sbtqa.tag.videorecorder.VideoRecorder;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.events.StepFinishedEvent;
import ru.yandex.qatools.allure.events.StepStartedEvent;
import ru.yandex.qatools.allure.events.TestCaseStartedEvent;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by cyberspace on 1/23/2018.
 */
public class SetUp {
    private static final Logger LOG = Logger.getLogger(SetupStepDefs.class);

    @Before()
    public void setUp() {

        //Apply failure callback
        TagAllureReporter.applyFailureCallback(OnFailureScheduler.class);

        //try to connect logger property file if exists
        String path = "src/test/resources/config/log4j.properties";
        if (new File(path).exists()) {
            PropertyConfigurator.configure(path);
            LOG.info("Log4j proprties were picked up on the path " + path);
        } else {
            LOG.warn("There is no log4j.properties on the path " + path);
        }

        try {
            String tasksToKill = Props.get("tasks.to.kill");
            if (!PageFactory.isSharingProcessing() && !"".equals(tasksToKill)) {
                for (String task : tasksToKill.split(",")) {
                    if (SystemUtils.IS_OS_WINDOWS) {
                        Runtime.getRuntime().exec("taskkill /IM " + task.trim() + " /F");
                    } else {
                        Runtime.getRuntime().exec("killall " + task.trim());
                    }
                }
            }
        } catch (IOException e) {
            LOG.debug("Failed to kill one of task to kill", e);
        }

        String aspectDisabled = Props.get("page.aspect.disabled");
        if (!"".equals(aspectDisabled)) {
            PageFactory.setAspectsDisabled(Boolean.parseBoolean(aspectDisabled));
        }

        Reflections reflections;
        reflections = new Reflections(PageFactory.getPagesPackage());

        Collection<String> allClassesString = reflections.getStore().get("SubTypesScanner").values();
        Set<Class<?>> allClasses = new HashSet();
        for (String clazz : allClassesString) {
            try {
                allClasses.add(Class.forName(clazz));
            } catch (ClassNotFoundException e) {
                LOG.warn("Cannot add all classes to set from package storage", e);
            }
        }

        for (Class<?> page : allClasses) {
            List<Class> supers = ClassUtilsExt.getSuperclassesWithInheritance(page);
            if (!supers.contains(Page.class) && !supers.contains(HtmlElement.class)) {
                if (page.getName().contains("$")) {
                    continue; //We allow private additional classes but skip it if its not extends Page
                } else {
                    throw new FactoryRuntimeException("Class " + page.getName() + " is not extended from Page class. Check you webdriver.pages.package property.");
                }
            }
            List<Field> fields = FieldUtilsExt.getDeclaredFieldsWithInheritance(page);
            Map<Field, String> fieldsMap = new HashMap<>();
            for (Field field : fields) {
                Class<?> fieldType = field.getType();
                if (fieldType.equals(WebElement.class)) {

                    ElementTitle titleAnnotation = field.getAnnotation(ElementTitle.class);
                    if (titleAnnotation != null) {
                        fieldsMap.put(field, titleAnnotation.value());
                    } else {
                        fieldsMap.put(field, field.getName());
                    }
                }
            }

            PageFactory.getPageRepository().put((Class<? extends Page>) page, fieldsMap);
        }

        if (PageFactory.isVideoRecorderEnabled()) {
            VideoRecorder.getInstance().startRecording();
        }
    }

    @After
    public void tearDown() {
        if (PageFactory.isVideoRecorderEnabled() && VideoRecorder.getInstance().isVideoStarted()) {
            VideoRecorder.getInstance().stopRecording();
            VideoRecorder.getInstance().resetVideoRecorder();
        }

        if (PageFactory.getEnvironment() == Environment.WEB && TagWebDriver.isWebDriverShared()) {
            LOG.info("Webdriver sharing is processing...");
            PageFactory.setSharingProcessing(true);
        } else {
            PageFactory.dispose();
        }
    }
}
