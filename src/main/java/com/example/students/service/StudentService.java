package com.example.students.service;

import com.example.students.exception.StudentNotFoundException;
import com.example.students.frontend.CreateStudent;
import com.example.students.frontend.StudentDto;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log
@Service
//@RequiredArgsConstructor
public class StudentService {

    private static final String API_URL = "http://localhost:8080/students";
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    //teraz możemy pobrać nasz interface jako standardowego Springowego beana
    private final StudentClient studentClient;


    //pobierając go przez konstruktor
    public StudentService(RestTemplate restTemplate, WebClient webClient, StudentClient studentClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
        this.studentClient = studentClient;
    }

    public void createStudent(CreateStudent createStudent) {
//        restTemplate.postForEntity(URI.create(API_URL), createStudent, Void.class);
//        webClient.post()
//                .bodyValue(createStudent)
//                .retrieve()
//                .toBodilessEntity()
//                .subscribe(response -> log.info("Student save properly"));
//        log.info("Response returned");

        // i wywołać na nim metodę
        studentClient.createStudent(createStudent);
    }

    public StudentDto getStudentById(UUID id) {
//        try {
//            return webClient.get()
//                    .uri("/{id}", id)
//                    .retrieve()
//                    .bodyToMono(StudentDto.class)
//                    .block(Duration.of(10, ChronoUnit.SECONDS));
//        } catch (WebClientResponseException e) {
//            throw new StudentNotFoundException("Student with id " + id + " not found");
//        } catch (HttpServerErrorException e) {
//            throw new RuntimeException("Error during sending request");
//        }
        return studentClient.getStudentById(id);
    }

    public void deleteByName(String name) {
        restTemplate.delete(API_URL + "?name=" + name);
    }

    public List<StudentDto> getNameBy(String name) {
//        var studentsArray = restTemplate.getForObject(URI.create(API_URL + "?name=" + name),
//                StudentDto[].class);
//        return Arrays.asList(studentsArray);
        try {
            return restTemplate.exchange(API_URL + "?name=" + name, HttpMethod.GET, null, new ParameterizedTypeReference<List<StudentDto>>() {
            }).getBody();
        } catch (WebClientResponseException e) {
            throw new StudentNotFoundException("Student with name " + name + " not found");
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Error during sending request");
        }
    }

    public List<StudentDto> findAll() {
        return new ArrayList<>();
    }
}
