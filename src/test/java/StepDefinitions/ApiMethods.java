package StepDefinitions;

import com.aventstack.extentreports.Status;
import com.cucumber.listener.Reporter;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import java.io.File;
import java.util.UUID;
import java.util.logging.Logger;

import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiMethods {
    public ResponseEntity<String> getResponseStatus;
    String Url = "http://dummy.restapiexample.com/";
    String uri = "api/v1/create";
    String request = "\"{\\\"name\\\":\\\"testdfd545\\\",\\\"salary\\\":\\\"123\\\",\\\"age\\\":\\\"23\\\",\\\"id\\\":\\\"719\\\"}\"";
    String getUri = "api/v1/employee/1";
    HttpHeaders requestHeader = new HttpHeaders();

    public ResponseEntity<String> postCallTest(String request, String Url, String uri) {
        requestHeader.add("Content-Type","application/json");
        String postUrl = Url + uri;
        HttpEntity<?> httpEntity = new HttpEntity<>(request);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        System.out.println("Post URL: " + postUrl);
        try {
            response = restTemplate.exchange(postUrl, HttpMethod.POST, httpEntity, String.class);
        } catch (Throwable e) {
            System.err.println("Exception Code: " + e.getMessage());
        }
        return response;
    }

    public ResponseEntity<String> getCallTest() {
        requestHeader.add("Content-Type","application/json");
        String getUrl = "http://ip.jsontest.com/";
        HttpEntity<?> httpEntity = new HttpEntity<>(requestHeader);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        System.out.println("Get URL: " + getUrl);
        try {
            response = restTemplate.exchange(getUrl, HttpMethod.POST, httpEntity, String.class);
        } catch (Throwable e) {
            System.err.println("Exception Code: " + e.getMessage());
        }
        return response;
    }

    @Given("^Post-I have a configured Cucumber-JVM project$")
    public void i_have_a_configured_Cucumber_JVM_project() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        getResponseStatus = this.postCallTest(Url, uri, request);
        System.out.println(getResponseStatus.getStatusCode());
        System.out.println(getResponseStatus.getBody());
        System.out.println("testtttttttttttttttt-----------");
        throw new PendingException();
    }

    @When("^Get-I run it within my IDE$")
    public void i_run_it_within_my_IDE() throws Throwable {
        getResponseStatus = this.getCallTest();
        System.out.println(getResponseStatus.getStatusCode());
        System.out.println(getResponseStatus.getBody());
        System.out.println("gettt-----------");
        Reporter.addStepLog("test report");
        throw new PendingException();
    }

}
