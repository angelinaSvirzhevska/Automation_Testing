package StepDefinitions;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;

public class Hooks {

    private DriverHelper driverHelper = new DriverHelper();

    @Before
    public void setUp(){
        driverHelper.createDriver();
    }

    @After
    public void tearDown(){
        driverHelper.closeDriver();
    }
}
