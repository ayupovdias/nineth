package kz.rest.de.service.impl;

import kz.rest.de.Entity.ApplicationRequest;
import kz.rest.de.Entity.Course;
import kz.rest.de.repository.ApplicationRequestRepository;
import kz.rest.de.repository.CourseRepository;
import kz.rest.de.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ApplicationRequestRepository applicationRequestRepository;
    @Override
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }
    @Override
    public Course getCourse(Long id){
        return courseRepository.findById(id).orElse(null);
    }
    @Override
    public Course addCourse(Course course){
        if(course.getPrice()<0){
            course.setPrice(0);
        }
        return courseRepository.save(course);
    }
    @Override
    public boolean deleteCourse(Long id){
        Course course=getCourse(id);
        List<ApplicationRequest> applicationRequests=applicationRequestRepository.findAll();
        if(course!=null){
            for(int i=0;i<applicationRequests.size();i++){
                Course applicationRequestCourse=applicationRequests.get(i).getCourse();
                if(applicationRequestCourse!=null){
                    if(applicationRequestCourse.getId()==id)
                        applicationRequests.get(i).setCourse(null);
                }
            }
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
