package com.example.students.service;

import com.example.students.frontend.CreateStudent;
import com.example.students.frontend.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//tworzymy interface klienta - bez implementacji. Implementacja, podobnie jak w przypadku JpaRepository, zostanie dla nas wygenerowana.
//Dodajemy adnotację FeignClient, parametr name jest obowiązakowy i musi być unikalny dla całej naszej aplikacji,
//po tej nazwie możemy dodawać konfigurację poszczególnych klientów do pliku application.yml
// więcej info o takich konfiguracjach dla zainteresowanych: https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
// dodatkowo podajemy parametr url, z adresem do którego chcemy wysłać zapytanie RESTowe
@FeignClient(name = "studentClient", url = "http://localhost:8080/students")
public interface StudentClient {

    //Następnie definiujemy metody, obowiązują tu dokładnie te same zasady co w przypadku @RestControllerów
    //Mamy dostępne adnotacje do metod, możemy dodać ścieżki, zmienne w ścieżkach, body zapytań, parametry to zapytań, a nawet nagłówki
    //Nie dodajemy informacji o statusie zwrotu (@ResponseStatus), status ten otrzymamy w odpowiedzi od serwisu, do którego wyślemy zapytanie
    //a więc nie możemy zdefiniować go z góry
    @GetMapping("/{id}")
    StudentDto getStudentById(@PathVariable UUID id);

    @PostMapping
    void createStudent(@RequestBody @Validated CreateStudent student);
}
