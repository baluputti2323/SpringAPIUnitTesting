package Sping8.hours.com.example.SpringAPIUnit.Servey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ServeyResourceController {
    private SurveyService surveyService;

    public ServeyResourceController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    @GetMapping("/serveys/")
    public Servey retriveallData(){
        return (Servey) surveyService.retriveAllData();
    }

    @GetMapping("/serveys/{serveyid}")
    public Servey retriveDataByID(@PathVariable String serveyid){
        Servey servey=surveyService.retriveDataByID(serveyid);
        if(servey==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return servey;
    }
    @GetMapping("/serveys/{serveyid}/questions")
    public List<Question> retriveDataByIDAndQuestions(@PathVariable String serveyid){
        List<Question> questions=surveyService.retriveDataByIDAndQuestions(serveyid);
        if(questions==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return questions;
    }
    @GetMapping("/serveys/{serveyid}/questions/{questionID}")
    public Question retriveDataBySpecificQuestions(@PathVariable String serveyid,@PathVariable String questionID){
        Question question= surveyService.retriveDataBySpecificQuestions(serveyid,questionID);
        if(question==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return question;
    }
    @RequestMapping(value = "/serveys/{serveyid}/questions",method = RequestMethod.POST)
    public ResponseEntity<Object> addNewQuestions(@PathVariable String serveyid,@RequestBody Question question){
        String questionID =surveyService.addNewQuestions(serveyid,question);
        URI Location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{questionID}").buildAndExpand().toUri();
        //for sending correct response 201
        return ResponseEntity.created(Location).build();

    }
    @RequestMapping(value ="/serveys/{serveyid}/questions/{questionID}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSpecificQuestions(@PathVariable String serveyid,@PathVariable String questionID){
       surveyService.deleteSpecificQuestions(serveyid,questionID);

        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value ="/serveys/{serveyid}/questions/{questionID}",method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSpecificQuestions(@PathVariable String serveyid,@PathVariable String questionID,@RequestBody Question question){
        surveyService.updateSpecificQuestions(serveyid,questionID,question);

        return ResponseEntity.noContent().build();
    }
}
