package com.example.StudentDetails.StudentDetails.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.StudentDetails.StudentDetails.Entity.Student;
import com.example.StudentDetails.StudentDetails.Exception.InvalidFileException;
import com.example.StudentDetails.StudentDetails.Exception.StudentNotFoundException;
import com.example.StudentDetails.StudentDetails.repository.StudentRepository;
@Service
public class StudentService {





	 @Autowired
	    private StudentRepository studentRepository;

	    //  Get All Students
	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    //  Get Student by ID
	    public Student getStudentById(Long id) {
	        return studentRepository.findById(id)
	                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
	    }

	    //  Get Students by Year
	    public List<Student> getStudentsByYear(int year) {
	    	List<Student> students = studentRepository.findByYear(year);
	        return students;
	    }

	    //  Get Students by Department
	    public List<Student> getStudentsByDepartment(String department) {
	        List<Student> students = studentRepository.findByDepartment(department);
	        if (students.isEmpty()) {
	            throw new StudentNotFoundException("No students found in department: " + department);
	        }
	        return students;
	    }


	    //  Save a Single Student
	    public void saveStudent(Student student) {
	        studentRepository.save(student);
	    }

	    //  Delete Student
	    public void deleteStudent(Long id) {
	        if (!studentRepository.existsById(id)) {
	            throw new StudentNotFoundException("Student with ID " + id + " not found.");
	        }
	        studentRepository.deleteById(id);
	    }


	    //  Upload & Save Students from CSV File
	    public void saveStudentsFromCSV(MultipartFile file) {
	        validateFileFormat(file, "csv");
	        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

	            List<Student> students = new ArrayList<>();
	            for (CSVRecord csvRecord : csvParser) {
	                try {
	                    String name = csvRecord.get("Name");
	                    int year = Integer.parseInt(csvRecord.get("Year")); // ✅ Catch NumberFormatException
	                    String department = csvRecord.get("Department");

	                    students.add(new Student(name, year, department));
	                } catch (NumberFormatException e) {
	                    throw new InvalidFileException("Invalid year format in CSV file.");
	                }
	            }
	            studentRepository.saveAll(students);
	        } catch (IOException e) {
	            throw new InvalidFileException("Error reading CSV file.");
	        }
	    }


	    //  Upload & Save Students from Excel File
	    public void saveStudentsFromExcel(MultipartFile file) {
	        validateFileFormat(file, "xlsx");
	        try (InputStream inputStream = file.getInputStream();
	             Workbook workbook = new XSSFWorkbook(inputStream)) {

	            Sheet sheet = workbook.getSheetAt(0);
	            List<Student> students = new ArrayList<>();

	            for (Row row : sheet) {
	                if (row.getRowNum() == 0) continue; // Skip header row

	                try {
	                    String name = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "Unknown";
	                    int year = row.getCell(1) != null ? (int) row.getCell(1).getNumericCellValue() : 0;
	                    String department = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "Unknown";

	                    students.add(new Student(name, year, department));
	                } catch (Exception e) {
	                    throw new InvalidFileException("Error processing row " + row.getRowNum() + " in Excel file.");
	                }
	            }
	            studentRepository.saveAll(students);
	        } catch (IOException e) {
	            throw new InvalidFileException("Error reading Excel file.");
	        }
	    }


	    private void validateFileFormat(MultipartFile file, String expectedExtension) {
	        String filename = file.getOriginalFilename();
	        if (filename == null || !filename.endsWith("." + expectedExtension)) {
	            throw new InvalidFileException("Only ." + expectedExtension + " files are allowed!");
	        }
	    }

	    
}

