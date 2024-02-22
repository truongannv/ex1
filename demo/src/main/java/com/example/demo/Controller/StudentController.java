package com.example.demo.Controller;

import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list-student")
    public ResponseEntity<?> listStudent(){
        return studentService.listStudent();
    }

    @PutMapping("/creat-student")
    public ResponseEntity<?> creatStudent(@RequestBody Student student){
        return studentService.creatStudent(student);
    }

    @PutMapping("/update-student")
    public ResponseEntity<?> updateStudent(@RequestParam int id,@RequestBody Student student){
        return studentService.updateStudent(id,student);
    }

    @DeleteMapping("/delete-student")
    public ResponseEntity<?> deleteStudent(@RequestParam int id){
        return studentService.deleteStudent(id);
    }
}
