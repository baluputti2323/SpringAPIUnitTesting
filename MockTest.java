package Sping8.hours.com.example.SpringAPIUnit.Practice;

import Sping8.hours.com.example.SpringAPIUnit.Servey.Question;
import Sping8.hours.com.example.SpringAPIUnit.Servey.SurveyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MockTest {
    private static String Specific_URL_FOR_QUESTION="/serveys/Survey1/questions/Question1";
    private static String general_URL_FOR_QUESTION="/serveys/Survey1/questions";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SurveyService surveyService;

    @Test
    void retrieveDataBySpecificQuestions_For_bad_Request() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(Specific_URL_FOR_QUESTION).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(404,mvcResult.getResponse().getStatus());

    }
    @Test
    void retrieveDataBySpecificQuestions_For_GET_Request() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(Specific_URL_FOR_QUESTION).accept(MediaType.APPLICATION_JSON);
        Question question= new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        when(surveyService.retriveDataBySpecificQuestions("surveyID","QuestionID"))
                .thenReturn(question);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());}
    @Test
    void addNewQuestion_For_Post_Request() throws Exception {
        String requestBody= """
                [
                {
                "description":"Most Popular Course Platform Today",
                "options":["java","Python","Google "," Cloud"],
                "correctAnswer":"java"}
                ]
                """;
        when(surveyService.addNewQuestions(anyString(),any())).thenReturn("SOME_ID");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(general_URL_FOR_QUESTION).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(201,result.getResponse().getStatus());
        assertTrue(result.getResponse().getHeaders("Location").contains("/serveys/Survey1/questions"));
    }
}
