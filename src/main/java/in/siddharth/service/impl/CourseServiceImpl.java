package in.siddharth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siddharth.entity.Course;
import in.siddharth.repo.CourseRepo;
import in.siddharth.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepo courseRepo;

	@Override
	public List<Course> getCourse() {
		return courseRepo.findAll();
	}

}
