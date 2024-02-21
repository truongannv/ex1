package com.example.demo.Service;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentService implements IStudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public ResponseEntity<?> creatStudent(Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @Override
    public ResponseEntity<?> listStudent() {
        try {
            List<Student> studentList = studentRepository.findAll();
            if(studentList.isEmpty()){
                return (ResponseEntity<?>) ResponseEntity.EMPTY;
            }else{
                return ResponseEntity.ok(studentList);
            }
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @Override
    public ResponseEntity<?> updateStudent(int id, Student student) {
        Student student1 = studentRepository.findById(id).orElse(null);
        if(student1==null){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }else{
            return ResponseEntity.ok(student);
        }
    }

    @Override
    public ResponseEntity<?> deleteStudent(@RequestBody int id) {
        return null;
    }
}
