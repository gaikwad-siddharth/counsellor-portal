package in.siddharth.service;

import in.siddharth.dto.DashboardResponseDto;
import in.siddharth.entity.Counsellor;

public interface CounsellorService {
	
	public boolean register(Counsellor counsellor);
	
	public boolean inEmailUnique(String email);
	
	public Counsellor login(String email, String pwd);
		
	public DashboardResponseDto getDashboardInfo(Integer counsellorId);

}
