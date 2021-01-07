package restful.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetWithHeader {
    static final String URL_USERS = "http://91.241.64.178:7081/api/users";
    static final String URL_USERS_DELETE = "http://91.241.64.178:7081/api/users/3";

    public static void main(String[] args) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(URL_USERS, //
                HttpMethod.GET, entity, String.class);
        String session = response.getHeaders().get("Set-Cookie").get(0);
        System.out.println(session);



        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers1.set("Cookie",session);
        User user = new User(3L,"James","Brown", (byte) 22);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user, headers1);
        ResponseEntity<String> result
                = restTemplate.postForEntity(URL_USERS, userHttpEntity, String.class);
        System.out.println(result.getBody());
        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> userHttpEntity2 = new HttpEntity<>(user, headers1);
        ResponseEntity<String> result2 = restTemplate.exchange(URL_USERS, HttpMethod.PUT, userHttpEntity2, String.class);
        System.out.println(result2.getBody());
        ResponseEntity<String> result3 = restTemplate.exchange(URL_USERS_DELETE, HttpMethod.DELETE, userHttpEntity2, String.class);
        System.out.println(result3.getBody());




    }
}
