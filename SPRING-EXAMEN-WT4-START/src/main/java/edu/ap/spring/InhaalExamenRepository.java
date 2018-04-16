package edu.ap.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import edu.ap.spring.model.InhaalExamen;


@Service
public class InhaalExamenRepository {

		
	 	@Autowired 
	    private RedisTemplate<String, InhaalExamen> InhaalExamenRedisTemplate; 

	    public void saveInhaalExamen(InhaalExamen aInhaalExamen) { 
	        InhaalExamenRedisTemplate.boundListOps("people").rightPush(aInhaalExamen); 
	    } 

	    public List<InhaalExamen> getAll() { 
	        return InhaalExamenRedisTemplate.boundListOps("people").range(0, -1); 
	    } 
	} 
