package kz.rest.de.controller;

import kz.rest.de.Entity.Course;
import kz.rest.de.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value="/api/courses")
@RestController
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getCourses(){
        List<Course> courses=courseService.getCourses();
        if(courses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(courses);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCourse(@PathVariable("id") Long id){
        Course course=courseService.getCourse(id);
        if(course==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        Course addedCourse=courseService.addCourse(course);
        return new ResponseEntity<>(addedCourse,HttpStatus.CREATED);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id){
        boolean result=courseService.deleteCourse(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
