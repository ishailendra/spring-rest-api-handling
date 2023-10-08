package dev.techsphere.webclienttester.consumer;

import dev.techsphere.webclienttester.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class RestApiConsumer {

    @Autowired
    private WebClient webClient;

    public void getStudentAsJsonString() {

        String url = "http://localhost:8080/students/1";


        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(url)
                .retrieve();

        String studentStr = responseSpec.bodyToMono(String.class).block();

        System.out.println(studentStr);
    }

    public void getStudentAsPOJO() {

        String url = "http://localhost:8080/students/1";


        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(url)
                .retrieve();

        Student student = responseSpec.bodyToMono(Student.class).block();

        System.out.println(student);
    }

    public void getStudents() {

        String url = "http://localhost:8080/students";


        WebClient.ResponseSpec response = webClient.get()
                .uri(url)
                .retrieve();

        List<Student> student = response.bodyToMono(List.class).block();
        System.out.println(student);
    }

    public void getStudentLogin() {

        String url = "http://localhost:8080/students/login/1";


        WebClient.ResponseSpec response = webClient.get()
                .uri(url)
                .retrieve();

        ResponseEntity<Student> resp = response.toEntity(Student.class).block();
        System.out.println(resp.getBody());

        HttpHeaders headers = resp.getHeaders();
        System.out.println(headers.get("Authorization"));
    }

    public void addNewStudent() {

        String url = "http://localhost:8080/students";

        Student student = new Student("Rohit", 7.8, "BBSR");

        String response = webClient.post()
                .uri(url)
                .header("Accept-Language", "en-US")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(student))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(response);
    }

    public void getStudentError() {

        String url = "http://localhost:8080/students/error/1";


        ResponseEntity<String> response = webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(
                        status -> status == HttpStatus.NOT_FOUND,
                        clientResponse -> Mono.empty()
                )
                .toEntity(String.class)
                .block();

        if (response != null && response.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.println(response.getBody());
        }
    }



}
