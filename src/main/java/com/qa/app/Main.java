package com.qa.app;

import com.beust.jcommander.internal.Lists;
import com.qa.util.Configuration;
import com.qa.util.ElementData;
import com.qa.util.TestData;
import org.testng.TestNG;

import java.util.List;

public class Main {
    public static void main(String[] args){
        Main main = new Main();
        main.startTest();

    }


    public void startTest(){
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        String testFile = null;

        Configuration.Load("src" + "/" + "main" + "/" + "resources" +  "/" + "conf.ini");
        TestData.Load("src" + "/" + "main" + "/" + "resources" + "/"  +  "testData.ini");
        ElementData.Load("src" + "/" + "main" + "/" + "resources" + "/"  + "elementData.ini");
        testFile = "src" + "/" + "main" + "/" + "resources"+ "/"  + "testng.xml";

        suites.add(testFile);

        testng.setTestSuites(suites);
        testng.run();
    }
}
