package in.siddharth.service;

import java.util.List;

import in.siddharth.dto.EnqFilterRequestDto;
import in.siddharth.dto.EnquiryDto;
import in.siddharth.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnquiry(EnquiryDto enquiryDto, Integer counsellorId);
	
	public List<Enquiry> getAllEnquiry(Integer counsellorId);
	
	public List<Enquiry> getEnquiryWithFilter(EnqFilterRequestDto filterDto, Integer counsellorId);
	
	public Enquiry getEnquiryById(Integer enqId);
	
	public boolean updateEnquiry(EnquiryDto enquiryDto);

}
