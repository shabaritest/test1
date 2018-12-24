package StepDefinitions;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//import managers.FileReaderManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.io.File;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/", glue = "StepDefinitions", plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}, tags = { "@Int1" })
//, plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}

public class base {

    @BeforeClass
    public static void setup() {
    }

    public String getReportConfigPath(){
        String reportConfigPath = System.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }

   @AfterClass
    public static void writeExtentReport() {
       // Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
       Reporter.loadXMLConfig(new File("config/report.xml"));
    }

    @AfterClass
    public static void teardown() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml");
    }
}
