package in.siddharth.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.siddharth.dto.EnqFilterRequestDto;
import in.siddharth.dto.EnquiryDto;
import in.siddharth.entity.Counsellor;
import in.siddharth.entity.Course;
import in.siddharth.entity.Enquiry;
import in.siddharth.repo.CounsellorRepo;
import in.siddharth.repo.CourseRepo;
import in.siddharth.repo.EnquiryRepo;
import in.siddharth.service.EnquiryService;
import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private CounsellorRepo counsellorRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private EnquiryRepo enquiryRepo;

	@Override
	public boolean addEnquiry(EnquiryDto enquiryDto, Integer counsellorId) {

	    // If ID is present, update existing enquiry
	    if (enquiryDto.getEnqId() != null) {
	        return updateEnquiry(enquiryDto);
	    }

	    Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
	    Course course = courseRepo.findById(enquiryDto.getCourseId()).orElseThrow();

	    Enquiry entity = new Enquiry();

	    entity.setStudName(enquiryDto.getStudName());
	    entity.setStudPhno(enquiryDto.getStudPhno());
	    entity.setClassMode(enquiryDto.getClassMode());
	    entity.setEnqStatus(enquiryDto.getEnqStatus());

	    entity.setCourse(course);
	    entity.setCounsellor(counsellor);

	    Enquiry savedEnq = enquiryRepo.save(entity);

	    return savedEnq.getEnqId() != null;
	}
	

	@Override
	public List<Enquiry> getAllEnquiry(Integer counsellorId) {
		return enquiryRepo.findByCounsellorCounsellorId(counsellorId);
	}

	@Override
	public List<Enquiry> getEnquiryWithFilter(EnqFilterRequestDto filterRequestDto, Integer counsellorId) {
		Enquiry entity = new Enquiry();

		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		entity.setCounsellor(counsellor);

		if (!StringUtils.isEmpty(filterRequestDto.getClassMode())) {
		    entity.setClassMode(filterRequestDto.getClassMode());
		}

		if (!StringUtils.isEmpty(filterRequestDto.getEnqStatus())) {
		    entity.setEnqStatus(filterRequestDto.getEnqStatus());
		}
		
		if(filterRequestDto.getCourseId()!=null && filterRequestDto.getCourseId()>0) {
			Course course = courseRepo.findById(filterRequestDto.getCourseId()).orElseThrow();
			entity.setCourse(course);
		}


		return enquiryRepo.findAll(Example.of(entity));
	}

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		return enquiryRepo.findById(enqId).orElseThrow();
	}

	@Override
	public boolean updateEnquiry(EnquiryDto enquiryDto) {

	    Optional<Enquiry> byId = enquiryRepo.findById(enquiryDto.getEnqId());

	    if (byId.isPresent()) {

	        Enquiry enquiry = byId.get();

	        enquiry.setStudName(enquiryDto.getStudName());
	        enquiry.setStudPhno(enquiryDto.getStudPhno());
	        enquiry.setClassMode(enquiryDto.getClassMode());
	        enquiry.setEnqStatus(enquiryDto.getEnqStatus());

	        Course course = courseRepo.findById(enquiryDto.getCourseId()).orElseThrow();
	        enquiry.setCourse(course);

	        enquiryRepo.save(enquiry);

	        return true;
	    }

	    return false;
	}

}
