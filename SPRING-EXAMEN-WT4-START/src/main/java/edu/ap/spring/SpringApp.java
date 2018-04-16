package edu.ap.spring;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import edu.ap.spring.model.InhaalExamen;


@SpringBootApplication
public class SpringApp {
	
	@Autowired
	private InhaalExamenRepository InhaalExamenRedisTemplate;
	
	@Bean
	@ConditionalOnMissingBean 
	public RedisConnectionFactory redisConnectionFactory() 
	                                    throws UnknownHostException { 
	    JedisConnectionFactory factory = 
	                                new JedisConnectionFactory(); 
	    factory.setHostName("localhost"); 
	    factory.setPort(6379); 
	    return factory; 
	} 

	@Bean 
	@ConditionalOnMissingBean(name = "redisTemplate") 
	public RedisOperations<Object, Object> redisTemplate(
	                        RedisConnectionFactory redisConnectionFactory) 
	                                 throws UnknownHostException { 
	    RedisTemplate<Object, Object> template = new RedisTemplate<>(); 
	    template.setConnectionFactory(redisConnectionFactory); 
	    return template; 
	} 


	@Bean 
	public RedisTemplate<String, InhaalExamen> getInhaalExamenRedisTemplate(
	                 RedisConnectionFactory redisConnectionFactory) { 
	    RedisTemplate<String, InhaalExamen> t = new RedisTemplate<>(); 
	    t.setConnectionFactory(redisConnectionFactory); 
	    t.setKeySerializer(new StringRedisSerializer()); 
	    t.setValueSerializer(new Jackson2JsonRedisSerializer<>(InhaalExamen.class)); 
	    t.afterPropertiesSet(); 
	    return t; 
	} 
	

	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			
		/*	InhaalExamenRedisTemplate.saveInhaalExamen(new InhaalExamen("test","test","test","test"));
			InhaalExamenRedisTemplate.saveInhaalExamen(new InhaalExamen("test2","test","test","test"));
			InhaalExamenRedisTemplate.saveInhaalExamen(new InhaalExamen("test5","test","test","test"));
			InhaalExamenRedisTemplate.getAll().forEach(s -> System.out.println(s.getStudent()));*/
	 		
		};
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
		
	}

}
