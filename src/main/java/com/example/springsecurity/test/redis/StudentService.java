package com.example.springsecurity.test.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StudentRepository studentRepository;

    private final String STUDENT_KEY = "students";


    public void save() {
        Student student = new Student(
                "Eng2013002", "van", Student.Gender.MALE, 1, Arrays.asList("Duong", "Xuan", "khue"));
        studentRepository.save(student);
    }

    public void saveStudents(List<Student> students) {
        for (Student student : students) {
            redisTemplate.opsForList().rightPush(STUDENT_KEY, student);
        }
    }

    public List<Student> getListStudent() {
        List<Object> studentObjects = redisTemplate.opsForList().range(STUDENT_KEY, 0, -1);

        return studentObjects.stream()
                .map(obj -> (Student) obj) // Ép kiểu từ Object về Student
                .collect(Collectors.toList());
    }

}
