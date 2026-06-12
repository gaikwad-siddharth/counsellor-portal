package in.siddharth.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siddharth.dto.DashboardResponseDto;
import in.siddharth.entity.Counsellor;
import in.siddharth.entity.Enquiry;
import in.siddharth.repo.CounsellorRepo;
import in.siddharth.repo.EnquiryRepo;

@Service
public class CounsellorServiceImpl implements in.siddharth.service.CounsellorService {
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Autowired
	private EnquiryRepo enquiryRepo;

	@Override
	public boolean register(Counsellor counsellor) {
		Counsellor savedCounsellor = counsellorRepo.save(counsellor);
		
		if(savedCounsellor.getCounsellorId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean inEmailUnique(String email) {
		Counsellor c = counsellorRepo.findByEmail(email);
		
		if(c!=null) {
			return false;
		}
		return true;
	}

	@Override
	public Counsellor login(String email, String pwd) {
		Optional<Counsellor> byEmailAndPwd= counsellorRepo.findByEmailAndPwd(email, pwd);
		
		if(byEmailAndPwd.isPresent()) {
			return byEmailAndPwd.get();
		}
		return null;
	}

	@Override
	public DashboardResponseDto getDashboardInfo(Integer counsellorId) {
		List<Enquiry> enquiryList = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		int totalEnqs = enquiryList.size();
		
		Map<String, Long> statusWiseMap = enquiryList.stream()
			.collect(Collectors.groupingBy(Enquiry::getEnqStatus, Collectors.counting()));
		
		int openCnt= statusWiseMap.getOrDefault("OPEN", 0L).intValue();
		int enrolledCnt= statusWiseMap.getOrDefault("ENROLLED", 0L).intValue();
		int lostCnt= statusWiseMap.getOrDefault("LOST", 0L).intValue();

		DashboardResponseDto dto = DashboardResponseDto.builder()
				.totalEnqs(totalEnqs)
				.enrolledEnqs(enrolledCnt)
				.openEnqs(openCnt)
				.lostEnqs(lostCnt)
				.build();
		return dto;
	}

}
