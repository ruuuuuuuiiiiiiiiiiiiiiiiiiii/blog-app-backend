package com.laureles.blog.controller.basics;

import com.laureles.blog.bean.basics.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Louie",
                "Laureles"
        );
        // return new ResponseEntity<>(student, HttpStatus.OK);
        // return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                                .header("custom-header", "louie")
                                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Louie", "Laureles"));
        students.add(new Student(2,"Mark", "Laureles"));
        students.add(new Student(3,"ML", "Laureles"));

        return ResponseEntity.ok(students);
    }

    // http://localhost:8080/students/1/fname/lname
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        // return new Student(studentId, firstName, lastName);
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //Spring boot REST API with Request Params
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                            @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //Spring Boot REST API that handles HTTP POST Request
    //@PostMapping and @RequestBody
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //Spring boot REST API that handles HTTP PUT Request  - updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student Deleted Successfully " + studentId);
    }
}
