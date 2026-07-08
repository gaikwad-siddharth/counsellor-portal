package in.siddharth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnquiryDto {
	
	private Integer enqId;
	private String studName;
	private String studPhno;
	private String classMode;
	private String enqStatus;
	private Integer courseId;

}
