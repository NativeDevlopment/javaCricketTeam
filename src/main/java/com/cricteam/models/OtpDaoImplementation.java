package com.cricteam.models;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cricteam.repository.OtpRepository;

@Service("otpDao")
@Repository
@Transactional
public class OtpDaoImplementation implements OtpDao {
	@Autowired 
	OtpRepository otprepository ;
	@PersistenceContext
	protected EntityManager entityManager;
	@Override
	public String saveOtp(Otp otp) {
		// TODO Auto-generated method stub
		
		return generateMoblieVerificationCode(otp.getMobileNo());
	}
	public String generateMoblieVerificationCode( String mobileNo) {
		
		long createdTime = new Date().getTime();
		entityManager.createNativeQuery("delete from otp where mobile_no=:mobileNo")
		.setParameter("mobileNo", mobileNo)
		.executeUpdate();
		String code = null;
		int result = entityManager.createNativeQuery("insert into otp(otp,mobile_no,created_time)"
				 +"SELECT * FROM (SELECT FLOOR(100000 + RAND() * 899999) AS otp , :num,:createdTime) as temp " 
				  +"WHERE 'otp' NOT IN (SELECT otp FROM otp  ) LIMIT 1")
				
		.setParameter("num", mobileNo)
		.setParameter("createdTime", createdTime)
		.executeUpdate();
		
		if(result==1){
			code = (String) entityManager.createNativeQuery("select otp from otp where mobile_no=:mobileNo and "
					+ "created_time=:time")
					.setParameter("mobileNo", mobileNo)
					.setParameter("time", createdTime)
					.setFirstResult(0).
					setMaxResults(1)
					.getSingleResult();	
			
		}
		
		return code;
	}
}
