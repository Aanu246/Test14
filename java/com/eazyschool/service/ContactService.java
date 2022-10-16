package com.eazyschool.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
//import org.springframework.web.context.annotation.ApplicationScope;
//import org.springframework.web.context.annotation.RequestScope;
//import org.springframework.web.context.annotation.SessionScope;

import com.eazyschool.constants.EazySchoolConstants;
import com.eazyschool.model.Contact;
import com.eazyschool.repository.ContactRepository;
//import java.util.Optional;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Service
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {
	//private static Logger log = LoggerFactory.getLogger(ContactService.class);
	//private int counter = 0;
	
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	
	
	public ContactService() {
		System.out.println("Contact Service Bean initialized");
	}
	public  boolean saveMessageDetails(Contact contact) {
		boolean isSaved = false;
		contact.setStatus(EazySchoolConstants.OPEN);
		//contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
		contact.setCreatedAt(LocalDateTime.now());
		Contact savedContact = contactRepository.save(contact);
		
		if(null!= savedContact && savedContact.getContactId()>0) {
			isSaved = true;
		}
		//log.info(contact.toString());
		return isSaved;
	}
	
	//paging and sorting
	public Page<Contact> findMsgWithOpenStatus(int pageNum,String sortField, String sortDir){
		int pageSize = 5;
	Pageable pageable = PageRequest.of(pageNum-1, pageSize, sortDir.equals("asc")? Sort.by(sortField).ascending()
				:Sort.by(sortField).descending());
		
		Page<Contact> msgPage = contactRepository.findByStatus(EazySchoolConstants.OPEN,pageable);
		// Page<Contact> msgPage = contactRepository.findOpenMsgs(EazySchoolConstants.OPEN,pageable);
		//Page<Contact> msgPage = contactRepository.findOpenMsgsNative(EazySchoolConstants.OPEN,pageable);
		return msgPage;
		
		
	}
	
	
	
	
	//public  List<Contact> findMsgWithOpenStatus(){
		//List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
	
		//return contactMsgs;
	//}
	
	public boolean updateMsgStatus(int contactId) {
		boolean isUpdated = false;
		//Optional<Contact> contact = contactRepository.findById(contactId);
		//contact.ifPresent(contact1 -> {
		//	contact1.setStatus(EazySchoolConstants.CLOSE);
			//contact1.setUpdatedBy(updatedBy);
			//contact1.setUpdatedAt(LocalDateTime.now());
		//});
		//Contact updatedContact = contactRepository.save(contact.get());
		//if(null!=updatedContact && updatedContact.getUpdatedBy() !=null) {
		//	isUpdated = true;
		//}
		
		int rows = contactRepository.updateStatusById(EazySchoolConstants.CLOSE,contactId);
		// int rows = contactRepository.updateMsgStatus(EazySchoolConstants.CLOSE,contactId);
		//int rows = contactRepository.updateMsgStatusNative(EazySchoolConstants.CLOSE,contactId);
		//int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE, updatedBy);
		//if(result>0) {
		//	isUpdated = true;
		//}
		
		if(rows>0) {
			isUpdated = true;
		}
		return isUpdated;
	}
	
	
	//public int getCounter() {
		//return counter;
	//}
//	public void setCounter(int counter) {
	//	this.counter = counter;
	//}

}
