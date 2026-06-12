package in.siddharth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer>{
	
	public Counsellor findByEmail(String email);
	
	public Optional<Counsellor> findByEmailAndPwd(String email, String pwd);

}
