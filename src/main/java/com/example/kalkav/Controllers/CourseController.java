package com.example.kalkav.Controllers;
// package com.example.lesson2.Controllers;

// import com.example.lesson2.DTOs.CourseDTO;
// // import com.example.lesson2.Services.CourseService;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/courses")
// public class CourseController {

// @Autowired
// public CourseService courseService;

// @GetMapping("/all")
// public ResponseEntity<List<CourseDTO>> getAll() {
// return ResponseEntity.ok().body(courseService.getAllCourses());
// }

// @GetMapping("/getById/{id}")
// public ResponseEntity<CourseDTO> getById(@PathVariable Long id) {
// Optional<CourseDTO> courseDTO = courseService.getCourseById(id);
// if (courseDTO.isPresent())
// return ResponseEntity.ok().body(courseDTO.get());
// return ResponseEntity.notFound().build();
// }

// @PostMapping("/create")
// public ResponseEntity<Void> create(@RequestBody CourseDTO entity) {
// System.out.println("a");
// if (courseService.createCourse(entity)) {
// return ResponseEntity.status(HttpStatus.CREATED).build();
// }
// return ResponseEntity.badRequest().build();
// }

// }
