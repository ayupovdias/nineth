package kz.rest.de.service;

import kz.rest.de.Entity.Course;
import java.util.List;
public interface CourseService {
    List<Course> getCourses();
    Course getCourse(Long id);
    Course addCourse(Course course);
    boolean deleteCourse(Long id);
}
