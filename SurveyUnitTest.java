package Sping8.hours.com.example.SpringAPIUnit.Practice;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyUnitTest {
    //http://localhost:8080/serveys/Survey1/questions/Question1
   /* String str= """
            {
            "id":"Question1",
            "description":"Most Popular Cloud Platform Today",
            "options":["AWS","Azure","Google Cloud","Oracle Cloud"],
            "correctAnswer":"AWS"
            }
            """;*/

    private static String Specific_URL_FOR_QUESTION="/serveys/Survey1/questions/Question1";
    private static String general_URL_FOR_QUESTION="/serveys/Survey1/questions";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    void retrieveDataBySpecificQuestions_WHEN_Specific_url_Given() throws JSONException {
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity(Specific_URL_FOR_QUESTION, String.class);
        String Expected= """
                  {
                            "id":"Question1",
                            "correctAnswer":"AWS"
                            }
                """;
        JSONAssert.assertEquals(Expected,forEntity.getBody(),false);
        assertTrue(forEntity.getStatusCode().is2xxSuccessful());
        assertEquals("application/json",forEntity.getHeaders().get("Content-Type").get(0));
    }

@Test
    void addNewQuestion_UsingPostMethod(){
        String requestedBody= """
                 [
                {
                "description":"Most Popular Course Platform Today",
                "options":["java","Python","Google "," Cloud"],
                "correctAnswer":"java"}
                ]
                """;
    HttpHeaders headers= new HttpHeaders();
    headers.add("Content-Type","application/json");
    HttpEntity<String> entity= new HttpEntity<>(requestedBody,headers);
    ResponseEntity<String> testRestTemplateForEntity
            = testRestTemplate.exchange(general_URL_FOR_QUESTION, HttpMethod.POST,entity,String.class);
    String locationHeader = testRestTemplateForEntity.getHeaders().get("Location").get(0);
    assertTrue(locationHeader.contains("/serveys/Survey1/questions"));
    assertTrue(testRestTemplateForEntity.getStatusCode().is2xxSuccessful());
    //If we want to delete means
    //testRestTemplate.delete(locationHeader);
}









}
