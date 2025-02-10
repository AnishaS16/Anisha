package com.example.StudentDetails.StudentDetails.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.StudentDetails.StudentDetails.Entity.Student;
import com.example.StudentDetails.StudentDetails.Service.StudentService;



@Controller
@RequestMapping("/students")
public class StudentController {

	

	@Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
//
//    @GetMapping("/view")
//    public String viewAllStudents(Model model) {
//        List<Student> students = studentService.getAllStudents();
//        model.addAttribute("students", students);
//        return "view";
//    }
//    @GetMapping("/view")
//    public String viewAllStudents(Model model) {
//        List<Student> students = studentService.getAllStudents();
//
//        System.out.println("Retrieved Students: " + students);
//        // ✅ Ensure the list is not null before passing to JSP
//        if (students == null || students.isEmpty()) {
//            model.addAttribute("errorMessage", "No student records found.");
//        } else {
//            model.addAttribute("students", students);
//        }
//
//        return "view"; // ✅ Must match `view.jsp` inside `WEB-INF/views/`
//    }
    @GetMapping("/view")
    public String viewAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        
        // ✅ Debugging: Print retrieved students
        System.out.println("Retrieved Students: " + students);

        // ✅ Ensure data is passed to JSP
        model.addAttribute("students", students);
        
        return "view"; // Ensure this matches `view.jsp`
    }


    //  Get Student by ID with Exception Handling
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    //  Fetch All Students
    @GetMapping("/all")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    //  Fetch Students by Year
    @GetMapping("/year/{year}")
    @ResponseBody
    public List<Student> getStudentsByYear(@PathVariable int year) {
        return studentService.getStudentsByYear(year);
    }

    //  Fetch Students by Department
    @GetMapping("/department/{department}")
    @ResponseBody
    public List<Student> getStudentsByDepartment(@PathVariable String department) {
        return studentService.getStudentsByDepartment(department);
    }

    //  Upload CSV
    @PostMapping("/upload/csv")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        studentService.saveStudentsFromCSV(file);
        return ResponseEntity.ok("CSV File Uploaded Successfully!");
    }

    //  Upload Excel
    @PostMapping("/upload/excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        studentService.saveStudentsFromExcel(file);
        return ResponseEntity.ok("Excel File Uploaded Successfully!");
    }
}
