package Sping8.hours.com.example.SpringAPIUnit.Servey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service

public class SurveyService {
    private static List<Servey> survey= new ArrayList<>();

    static {
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        Servey servey = new Servey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);
        survey.add(servey);
    }




    public List<Servey> retriveAllData()
    {
        return survey;
    }

   public Servey retriveDataByID(String serveyid) {
         Predicate<? super Servey> perdicate=servey -> servey.getId().equalsIgnoreCase(serveyid);
        Optional<Servey> optionalServey =survey.stream().filter(perdicate).findFirst();
        if(optionalServey.isEmpty()) return null;
        return optionalServey.get();
    }


    public List<Question> retriveDataByIDAndQuestions(String serveyid) {
       Servey servey = retriveDataByID(serveyid);
        if(servey==null) return null;
        return servey.getQuestion();
    }

    public Question retriveDataBySpecificQuestions(String serveyid, String questionID) {
       List<Question> surveyQuestions = retriveDataByIDAndQuestions(serveyid);
        if(surveyQuestions==null)  return null;
        Optional<Question> optionalQuestion=surveyQuestions.stream().filter(question -> question.getId().equalsIgnoreCase(questionID)).findFirst();
        if(optionalQuestion.isEmpty()) return null;
        return optionalQuestion.get();
    }


    public String addNewQuestions(String serveyid, Question question) {
       List<Question> questionList= retriveDataByIDAndQuestions(serveyid);
        question.setId(randomNumber());
       questionList.add(question);
       return question.getId();

    }
    public String randomNumber(){
        SecureRandom secureRandom = new SecureRandom();
        String randomId= new BigInteger(32,secureRandom).toString();
        return randomId;
    }

    public String deleteSpecificQuestions(String serveyid, String questionID) {
        List<Question> surveyQuestions = retriveDataByIDAndQuestions(serveyid);
        if(surveyQuestions==null)  return null;
        Predicate<? super Question> Predicate = survey ->survey.getId().equalsIgnoreCase(questionID);
        boolean removed=surveyQuestions.removeIf(Predicate);
        if(!removed) return null;
        return questionID;
    }

    public void updateSpecificQuestions(String serveyid, String questionID, Question question) {
        List<Question> surveyQuestions = retriveDataByIDAndQuestions(serveyid);
        surveyQuestions.removeIf(survey ->survey.getId().equalsIgnoreCase(questionID));
        surveyQuestions.add(question);
    }
}
