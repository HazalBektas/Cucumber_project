package steps;

import cucumber.api.java.After;
import utilities.Driver;

public class Hooks {

    @After
    public void tearDown(){
        Driver.quitDriver();
    }
}
