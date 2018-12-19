package service;

import handler.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the entry point of the application
 */
@SpringBootApplication
@RestController
public class Application {

    /**
     * This function is used for checking input data for the correctness
     * Function reads data from the input stream and divided it into XML and XSD contents
     * @param body - this is a body of the request method POST
     * @return ResponseEntity<String> - http response
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> interpretXMLandXSD(@RequestBody String body) {
        if(body.length() == 0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not valid. Empty body\n");
        }

        Pattern pattern = Pattern.compile("<\\?xml");
        Matcher matcher = pattern.matcher(body);
        int startXml, startXsd;
        if(matcher.find()){
            startXml = matcher.start();
        } else {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Not valid. XML not found\n");
        }
        if(matcher.find()){
            startXsd = matcher.start();
        } else {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Not valid. XSD not found\n");
        }

        String xml = body.substring(startXml, startXsd);
        String xsd = body.substring(startXsd);

        if(Validator.validateXMLByXSD(xml, xsd)) {
            return ResponseEntity.ok("Valid\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not valid\n");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
