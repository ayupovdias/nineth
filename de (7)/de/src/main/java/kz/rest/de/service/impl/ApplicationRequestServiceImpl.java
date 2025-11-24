package kz.rest.de.service.impl;

import kz.rest.de.Entity.ApplicationRequest;
import kz.rest.de.Entity.Course;
import kz.rest.de.Entity.Operators;
import kz.rest.de.repository.ApplicationRequestRepository;
import kz.rest.de.repository.OperatorsRepository;
import kz.rest.de.service.ApplicationRequestService;
import kz.rest.de.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ApplicationRequestServiceImpl implements ApplicationRequestService {
    private final ApplicationRequestRepository applicationRequestRepository;
    private final CourseService courseService;
    private final OperatorsRepository operatorsRepository;
    @Override
    public List<ApplicationRequest> getApplicationRequests(){
        return applicationRequestRepository.findAll();
    }
    @Override
    public ApplicationRequest getApplicationRequest(Long id){
        return applicationRequestRepository.findById(id).orElse(null);
    }
    @Override
    public ApplicationRequest addApplicationRequest(ApplicationRequest applicationRequest){
        applicationRequest.setHandled(false);
        Course course=applicationRequest.getCourse();
        List<Operators> operators=applicationRequest.getOperators();
        if(course!=null){
            Course receiveCourse =courseService.getCourse(course.getId());
            if(receiveCourse!=null){
                applicationRequest.setCourse(receiveCourse);
            }
            else{
                applicationRequest.setCourse(null);
            }
        }
        List<Long> ids=new ArrayList<>();
        if(!operators.isEmpty()){
            for(Operators op:operators){
                if(op!=null){
                    if(!ids.contains(op.getId())){
                        ids.add(op.getId());
                    }
                }
            }
        }
        List<Operators> newOperators=new ArrayList<>();
        if(!ids.isEmpty()){
            for(Long id: ids){
                if(id>0){
                    Operators operator=operatorsRepository.findById(id).orElse(null);
                    if(operator!=null){
                        newOperators.add(operator);
                    }
                }
            }
            applicationRequest.setOperators(newOperators);
        }
        ApplicationRequest addedApplicationRequest=applicationRequestRepository.save(applicationRequest);
        return addedApplicationRequest;
    }
    @Override
    public ApplicationRequest updateApplicationRequest(Long id, ApplicationRequest applicationRequest){
        ApplicationRequest receiveApplicationRequest=getApplicationRequest(id);
        if(receiveApplicationRequest!=null){
            if(applicationRequest.getUserName()!=null){
                receiveApplicationRequest.setUserName(applicationRequest.getUserName());
            }
            if(applicationRequest.getComment()!=null){
                receiveApplicationRequest.setComment(applicationRequest.getComment());
            }
            if(applicationRequest.getPhone()!=null){
                receiveApplicationRequest.setPhone(applicationRequest.getPhone());
            }
            receiveApplicationRequest.setHandled(true);
            Course course=applicationRequest.getCourse();
            if(course!=null){
                Course receiveCourse=courseService.getCourse(course.getId());
                if(receiveCourse!=null){
                    receiveApplicationRequest.setCourse(receiveCourse);
                }
                else{
                    receiveApplicationRequest.setCourse(null);
                }
            }
            List<Long> ids=new ArrayList<>();
            List<Operators> operators=applicationRequest.getOperators();
            if(operators==null){
                receiveApplicationRequest.setOperators(null);
            }
            else if(operators.isEmpty()){
                receiveApplicationRequest.setOperators(new ArrayList<>());
            }
            else{
                for(Operators op: operators){
                    if(!ids.contains(op.getId())){
                        ids.add(op.getId());
                    }
                }
                List<Operators> updatedOperators=new ArrayList<>();
                for(Long identification : ids){
                        Operators operator=operatorsRepository.findById(identification).orElse(null);
                        if(operator!=null){
                            updatedOperators.add(operator);
                        }

                }
                receiveApplicationRequest.setOperators(updatedOperators);
            }
            ApplicationRequest updatedApplicationRequest=applicationRequestRepository.save(receiveApplicationRequest);
            return updatedApplicationRequest;
        }
        return null;
    }
    @Override
    public boolean deleteApplicationRequest(Long id){
        ApplicationRequest applicationRequest=getApplicationRequest(id);
        if(applicationRequest!=null){
            applicationRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
