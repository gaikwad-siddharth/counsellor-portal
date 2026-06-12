package in.siddharth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{
	
	public List<Enquiry> findByCounsellorCounsellorId(Integer counsellorId);

}
