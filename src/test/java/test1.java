
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class test1 {

    //public ResponseEntity<String> postCallTest(JsonObject request, HttpHeaders requestHeaders, String Url, String uri) {
    public ResponseEntity<String> postCallTest(String request, String Url, String uri) {
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

}




