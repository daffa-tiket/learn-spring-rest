package daffashafwandev.learnspringrest;

import daffashafwandev.learnspringrest.response.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("getAll")
    public ResponseEntity<Object> fetchAllStudents(){
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseHandler.generateResponse("Success Get Data", HttpStatus.OK, students);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createPerson(@RequestBody Student student) {
        try{
           Student studentResp =  studentService.saveUpdateStudent(student);
           return ResponseHandler.generateResponse("Successed Add Data", HttpStatus.OK, studentResp);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try{
            studentService.deleteStudent(id);
            return ResponseHandler.generateResponse("Success Delete Data", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
