package kz.rest.de.service;

import kz.rest.de.Entity.ApplicationRequest;

import java.util.List;

public interface ApplicationRequestService {
    List<ApplicationRequest> getApplicationRequests();
    ApplicationRequest getApplicationRequest(Long id);
    ApplicationRequest addApplicationRequest(ApplicationRequest applicationRequest);
    ApplicationRequest updateApplicationRequest(Long id, ApplicationRequest applicationRequest);
    boolean deleteApplicationRequest(Long id);
}
