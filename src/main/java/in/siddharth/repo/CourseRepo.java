package in.siddharth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
