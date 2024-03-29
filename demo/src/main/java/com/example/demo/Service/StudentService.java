package com.example.demo.Service;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
public class StudentService implements IStudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Cacheable(value = "student")
    public ResponseEntity<?> listStudent() {
        try {
            log.info("Call to Student");
            List<Student> studentList = studentRepository.findAll();
            if(studentList.isEmpty()){
                return ResponseEntity.ok().build();
            }else{
                return (ResponseEntity<?>) ResponseEntity.ok(studentList);
            }
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @CachePut(value = "student", key = "#student.getId")
    @Override
    public ResponseEntity<?> creatStudent(Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @CachePut(value = "student", key = "#student.id")
    @Override
    public ResponseEntity<?> updateStudent(int id, Student student) {
        Student student1 = studentRepository.findById(id).orElse(null);
        if(student1==null){
            return ResponseEntity.notFound().build();
        }else{
            student1.setId(student.getId());
            student1.setAge(student.getAge());
            student1.setAddress(student.getAddress());
            student1.setName(student.getName());
            student1.setPhoneNumber(student.getPhoneNumber());
            return (ResponseEntity<?>) ResponseEntity.ok(student);
        }
    }

    @CacheEvict(value = "student", allEntries = true)
    @Override
    public ResponseEntity<?> deleteStudent(@RequestBody int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student == null){
            return ResponseEntity.notFound().build();
        }else{
            studentRepository.deleteById(id);
            return (ResponseEntity<?>) ResponseEntity.ok(id);
        }
    }
}
