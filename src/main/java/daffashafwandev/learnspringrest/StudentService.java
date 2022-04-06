package daffashafwandev.learnspringrest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveUpdateStudent(Student student) throws Exception {
        if(studentRepository.findStudentByEmail(student.getEmail()).isEmpty()){
            Student studentOps = studentRepository.insert(student);
            return studentOps;
        }
        throw new Exception("Email already Exist");
    }

    public void deleteStudent(String id){
        studentRepository.deleteById(id);
    }
}
