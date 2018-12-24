package StepDefinitions;

import com.aventstack.extentreports.Status;
import com.cucumber.listener.Reporter;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

import java.io.*;
import java.util.UUID;
import java.util.logging.Logger;

import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
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

public class int1 {

    public ResponseEntity<String> getResponseStatus;
    String Url = "http://216.10.245.166";
    String uri = "/maps/api/place/add/json";
    String request = "\"{\\\"name\\\":\\\"testdfd545\\\",\\\"salary\\\":\\\"123\\\",\\\"age\\\":\\\"23\\\",\\\"id\\\":\\\"719\\\"}\"";
    String getUri = "api/v1/employee/1";
    HttpHeaders requestHeader = new HttpHeaders();


    public JsonObject convertStringToJson(String request) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObect = (JsonObject) parser.parse(request);
        return jsonObect;
    }

    public JsonObject readFileToJSON(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        // File file = new File(System.getProperty("user.dir") +
        // "/src/test/java/testFiles/" + fileName);
        BufferedReader br = null;
        // File file = CommonUtils.readFile("/testFiles/" + fileName);
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/" + fileName);
        try {
            // URL url =
            // this.getClass().getClassLoader().getResource("testFiles/"+fileName);
            // File file = new
            // File(this.getClass().getClassLoader().getResource("testFiles/"+fileName).getPath());
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line;
            while ((line = br.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String contents = sb.toString();
        return convertStringToJson(contents);
    }

    public ResponseEntity<String> postCallTest(String request, String Url, String uri) throws FileNotFoundException {
        requestHeader.add("Content-Type","application/json");
        String postUrl = Url + uri;
        HttpEntity<?> httpEntity = new HttpEntity<>(request,requestHeader);
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

    @Given("^Post-Method$")
    public void post_Method() throws Throwable {
        JsonObject lotJsonPayload = this.readFileToJSON("Request.json");
        String request2 = lotJsonPayload.toString();
        requestHeader.add("Content-Type","application/json");
        requestHeader.add("key","qaclick123");
        getResponseStatus = this.postCallTest(request2,Url,uri);
        System.out.println(getResponseStatus.getStatusCode());
        System.out.println(getResponseStatus.getBody());
        System.out.println(getResponseStatus.getHeaders());
        System.out.println(getResponseStatus.getHeaders().getContentType().getType());
        System.out.println(getResponseStatus.getHeaders().getContentType());
        //Assert.assertEquals(getResponseStatus.getHeaders().getContentType(),"application/json;charset=UTF-8");

        String strResponse = getResponseStatus.getBody().toString();
        JsonParser parser = new JsonParser();
        JsonObject responseObj1 = parser.parse(strResponse).getAsJsonObject();
        String placeId = responseObj1.get("place_id").toString();
        System.out.println("placeId--"+placeId);




        throw new PendingException();
    }


}
