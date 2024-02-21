package com.example.demo.Service;

import com.example.demo.Model.Student;
import org.springframework.http.ResponseEntity;

public interface IStudentService {
    ResponseEntity<?> creatStudent(Student student);
    ResponseEntity<?> listStudent();
    ResponseEntity<?> updateStudent(int id, Student student);
    ResponseEntity<?> deleteStudent(int id);
}
