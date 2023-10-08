package dev.techsphere.restapitester.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.techsphere.restapitester.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class RestApiConsumer {

    @Autowired
    private RestTemplate restTemplate;

    public void getStudentAsJson() {

        String url = "http://localhost:8080/students";

        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String student = response.getBody();

        System.out.println(student);
    }

    public void getStudent() {

        String url
                = "http://localhost:8080/students";

        // Fetch response as List wrapped in ResponseEntity
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        List<Student> student1 = response.getBody();
        System.out.println(student1);


        // Fetching response as Object
        List<?> student2 = restTemplate.getForObject(url, List.class);

        System.out.println(student2);
    }

    public void addNewStudent() {

        String url = "http://localhost:8080/students";

        // Create the request body by wrapping the object in HttpEntity
        HttpEntity<Student> request = new HttpEntity<Student>(new Student("Rohit", 7.8, "BBSR"));

        // Send the request body in HttpEntity for HTTP POST request
        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println(response);
    }

    public void addNewStudentExchange() {
        String url = "http://localhost:8080/students";

        // Create the request body by wrapping the object in HttpEntity
        HttpEntity<Student> request = new HttpEntity<Student>(new Student("Robin", 8.0, "Agra"));

        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        System.out.println(resp);
    }

    public void updateStudentWithExchange() {

        String url = "http://localhost:8080/students";

        // Create the request body by wrapping the object in HttpEntity
        HttpEntity<Student> request = new HttpEntity<Student>(new Student(1, "Rakesh", 7.8, "Ara"));

        // Send the PUT method as a method parameter
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
    }

    public void getStudentasStream() {
        final Student student = new Student(1, "Rakesh", 7.8, "Ara");

        String url = "http://localhost:8080/Students";

        // Set HTTP headers in the request callback
        RequestCallback requestCallback = request -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(request.getBody(),
                    student);

            request.getHeaders()
                    .setAccept(Arrays.asList(
                            MediaType.APPLICATION_OCTET_STREAM,
                            MediaType.ALL));
        };

        // Processing the response. Here we are extracting the
        // response and copying the file to a folder in the server.
        ResponseExtractor<Void> responseExtractor = response -> {
            Path path = Paths.get("/var/temp/path");
            Files.copy(response.getBody(), path);
            return null;
        };

        restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor );

    }




}
