package com.request.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.request.services.entities.CustomerRequest;
import com.request.services.repositories.CustomerRequestRepository;

@Service
public class CustomerRequsetService {

    @Autowired
    private CustomerRequestRepository repo;
    
    public List<CustomerRequest> getAllRequests(){
        return repo.findAll();
    }

    public List<CustomerRequest> getRequestStatus(String status) throws Exception{
        List<CustomerRequest> optional = (List<CustomerRequest>) repo.findByStatus(status);
        
        
        List<CustomerRequest> count;
        if (((List<CustomerRequest>) optional).size() > 0)
            count=(List<CustomerRequest>) optional;   
        else
            count=null;            
        return count;

//        if(optional.isEmpty()){
//            throw new Exception("Service Request not found with id: "+status);
//        } else {
//            return (List<CustomerRequest>) optional.get();
//        }
    }

    public CustomerRequest saveServiceRequest(CustomerRequest requsets){
        return repo.save(requsets);
    }

//    public CustomerRequest updateServiceRequest(int id, CustomerRequest requsets){
//    	String getCommentsVal=requsets.getComments();
//    	//return repo.updateCommentsByPendingStatus(getCommentsVal, id)
//        return null;
//    }

    public int deleteRequest(int deletedStatusId) throws Exception{
    	 Optional<CustomerRequest> optional = repo.findById(deletedStatusId);
    	 int id=0;
         if(optional.isEmpty()){
             throw new Exception("Service Request not found with id: "+deletedStatusId);
         } else {
        	 System.out.println(optional.get().getId()+"optional.get().getId();");
        	 repo.deleteById(deletedStatusId);
              
         }
    		
        return id;
    }

    public Integer updateCommentsByClosedStatus(String comments,String status, int id) {
    	String statusReopen="REOPENED";
		return repo.updateCommentsByClosedStatus(comments, statusReopen, id);
	}
    
	public Integer updateCommentsByPendingStatus(String comments,String status, int id) {
		return repo.updateCommentsByPendingStatus(comments, status, id);
	}
    
}