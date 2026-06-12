package in.siddharth.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.siddharth.dto.EnqFilterRequestDto;
import in.siddharth.dto.EnquiryDto;
import in.siddharth.entity.Enquiry;
import in.siddharth.service.CourseService;
import in.siddharth.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/enquiry")
	public String enquiryForm(Model model) {
		
		EnquiryDto enquiryDtoObj = new EnquiryDto();
		model.addAttribute("enquiry", enquiryDtoObj);
		model.addAttribute("courses", courseService.getCourse());
		return "add-enq";
	}
	
	@PostMapping("/enquiry")
	public String addEnquiry(EnquiryDto enquiryDto, Model model, HttpServletRequest request) {

	    HttpSession session = request.getSession(false);
	    Integer cid = (Integer) session.getAttribute("CID");

	    boolean status = enqService.addEnquiry(enquiryDto, cid);

	    if (status) {
	        model.addAttribute("smsg", "Enquiry Added");
	    } else {
	        model.addAttribute("emsg", "Enquiry Not Added");
	    }

	    // REQUIRED
	    model.addAttribute("enquiry", new EnquiryDto());
	    model.addAttribute("courses", courseService.getCourse());

	    return "add-enq";
	}
	
	@GetMapping("/view-enquiries")
	public String viewEnquires(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		Integer cid = (Integer) session.getAttribute("CID");
		
		EnqFilterRequestDto filterRequestDtoObj = new EnqFilterRequestDto();
		
		List<Enquiry> enqList = enqService.getAllEnquiry(cid);
		
		model.addAttribute("filerRequestDto", filterRequestDtoObj);
		model.addAttribute("enqs", enqList);
		model.addAttribute("courses", courseService.getCourse());
		
		return "view-enqs";
	}
	
	@PostMapping("/filter-enquiries")	
	public String viewEnquires(EnqFilterRequestDto filterRequestDto, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		Integer cid = (Integer) session.getAttribute("CID");
		
		List<Enquiry> enquiryWithFilter = enqService.getEnquiryWithFilter(filterRequestDto, cid);
		
		model.addAttribute("enqs", enquiryWithFilter);
		model.addAttribute("courses", courseService.getCourse());
		
		return "view-enqs";
	}
	
	@GetMapping("/editEnq")
	public String editEnquiry(@RequestParam("enqId") Integer enqId, Model model) {
		Enquiry enquiryById = enqService.getEnquiryById(enqId);
		
		EnquiryDto dto= new EnquiryDto();
		BeanUtils.copyProperties(enquiryById, dto);
		dto.setCourseId(enquiryById.getCourse().getCourseId());
		
		model.addAttribute("enqDto", dto);		
		model.addAttribute("courses", courseService.getCourse());
		
		return "add-enq";
	}

}
