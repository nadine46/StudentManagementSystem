package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(
            @ModelAttribute("student") Student student,
            RedirectAttributes redirectAttributes) {

        studentService.saveStudent(student);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Student registered successfully!"
        );

        return "redirect:/student/register";
    }

    @GetMapping("/all")
    public String showAllStudents(Model model) {
        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );
        return "students";
    }

    @GetMapping("/{id}")
    public String showStudentById(
            @PathVariable int id,
            Model model) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            model.addAttribute("students", List.of());
            model.addAttribute(
                    "message",
                    "No student found with ID: " + id
            );
            return "students";
        }

        model.addAttribute("students", List.of(student));
        return "students";
    }
}