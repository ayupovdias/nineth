package kz.rest.de.service.impl;

import kz.rest.de.Entity.ApplicationRequest;
import kz.rest.de.Entity.Operators;
import kz.rest.de.repository.OperatorsRepository;
import kz.rest.de.service.ApplicationRequestService;
import kz.rest.de.service.OperatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OperatorsServiceImpl implements OperatorsService {
    private final OperatorsRepository operatorsRepository;
    private final ApplicationRequestService applicationRequestService;

    @Override
    public List<Operators> getOperators(){
        return operatorsRepository.findAll();
    }
    @Override
    public Operators getOperator(Long id){
        return operatorsRepository.findById(id).orElse(null);
    }
    @Override
    public Operators addOperator(Operators operators){
        return operatorsRepository.save(operators);
    }
    @Override
    public ApplicationRequest assignOperatorToApplicationRequest(Long id,
                                                                 Long requestId){
        Operators operator=getOperator(id);
        ApplicationRequest applicationRequest=applicationRequestService.getApplicationRequest(requestId);
        if(operator!=null && applicationRequest!=null){
            List<Operators> operators=applicationRequest.getOperators();
            if(operators==null){
                operators=new ArrayList<>();
            }
            if(!operators.isEmpty()){
                for(Operators op: operators){
                    if(op.getId()==id){
                        return null;
                    }
                }
            }
            operators.add(operator);
            applicationRequest.setOperators(operators);
            ApplicationRequest updatedApplicationRequest=applicationRequestService.updateApplicationRequest(requestId,applicationRequest);
            return updatedApplicationRequest;
        }
        return null;
    }
    @Override
    public ApplicationRequest unassignOperatorToApplicationRequest(Long id, Long requestId){
        Operators operator=getOperator(id);
        ApplicationRequest applicationRequest=applicationRequestService.getApplicationRequest(requestId);
        if(operator!=null && applicationRequest!=null){
            List<Operators> operators=applicationRequest.getOperators();
            if(operators==null){
                return null;
            }

            if(!operators.isEmpty()){
                if(!operators.contains(getOperatorById(id,operators))){
                    return null;
                }
                for(int i=0;i<operators.size();i++){
                    if(operators.get(i).getId()==id){
                        operators.remove(i);
                        applicationRequest.setOperators(operators);
                        ApplicationRequest updatedApplicationRequest=applicationRequestService.updateApplicationRequest(requestId, applicationRequest);
                        return updatedApplicationRequest;

                    }
                }
                return null;
            }

        }
        return null;
    }
    @Override
    public boolean deleteOperator(Long id){
        Operators operator=getOperator(id);
        List<ApplicationRequest> applicationRequests=applicationRequestService.getApplicationRequests();
        if(operator!=null){
            if(!applicationRequests.isEmpty()){
                for(int i=0;i<applicationRequests.size();i++){
                    List<Operators> operators=applicationRequests.get(i).getOperators();
                    if(operators!=null){
                        for(int j=0;j<operators.size();j++){
                            if(operators.get(j).getId()==id){
                                operators.remove(j);
                                break;
                            }
                        }
                        applicationRequests.get(i).setOperators(operators);
                    }
                }

            }
            operatorsRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Operators getOperatorById(Long id, List<Operators> operators){
        for(Operators op: operators){
            if(op.getId()==id){
                return op;
            }
        }
        return null;
    }
}
