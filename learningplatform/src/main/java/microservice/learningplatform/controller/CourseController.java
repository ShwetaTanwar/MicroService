package microservice.learningplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @GetMapping
    public List<Map<String, String>> getCourseDetails() {
        List<Map<String, String>> courseList = new ArrayList<>();

        // Course 1
        Map<String, String> course1 = new HashMap<>();
        course1.put("courseName", "Introduction to Programming");
        course1.put("instructor", "John Doe");
        course1.put("duration", "6 weeks");
        course1.put("description", "This course provides an introduction to programming concepts.");
        courseList.add(course1);

        // Course 2
        Map<String, String> course2 = new HashMap<>();
        course2.put("courseName", "Web Development Fundamentals");
        course2.put("instructor", "Jane Smith");
        course2.put("duration", "8 weeks");
        course2.put("description", "Learn the basics of web development and create interactive websites.");
        courseList.add(course2);

        // Course 3
        Map<String, String> course3 = new HashMap<>();
        course3.put("courseName", "Data Science Essentials");
        course3.put("instructor", "Michael Johnson");
        course3.put("duration", "10 weeks");
        course3.put("description", "Explore data analysis, visualization, and machine learning.");
        courseList.add(course3);

        // Course 4
        Map<String, String> course4 = new HashMap<>();
        course4.put("courseName", "Mobile App Development");
        course4.put("instructor", "Emily Brown");
        course4.put("duration", "7 weeks");
        course4.put("description", "Build mobile apps for Android and iOS platforms.");
        courseList.add(course4);

        // Course 5
        Map<String, String> course5 = new HashMap<>();
        course5.put("courseName", "Advanced Java Programming");
        course5.put("instructor", "David Wilson");
        course5.put("duration", "9 weeks");
        course5.put("description", "Deepen your understanding of Java programming techniques.");
        courseList.add(course5);

        return courseList;
    }
}
