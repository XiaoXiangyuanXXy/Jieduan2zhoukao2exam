package com.bw.test;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.cms.entity.Users;
import com.bw.cms.utils.TimeRandomUtil;
import com.bw.cms.utils.UserRandomUtil;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class RedisUserTest {
	
	@Resource
	private  RedisTemplate redisTemplate;
	
	@Test
	public void RedisUserTest(){
		long time1 = System.currentTimeMillis();
		
		//String类型
		//ValueOperations opsForValue = redisTemplate.opsForValue();
		
		
		//hash类型
		BoundHashOperations boundHashOps = redisTemplate.boundHashOps("hash-users");
		
		for (int i = 1; i <=50000; i++) {
			
			Users users=new Users();
			
			//设置编号
			users.setId(i);
			//设置姓名
			users.setName(UserRandomUtil.getChineseName());
			
			//设置性别
			users.setSex(UserRandomUtil.getSex());
			
			//设置电话号
			
			users.setPhone(UserRandomUtil.getPhone());
			
			//设置邮箱
			users.setEmail(UserRandomUtil.getEmail());
			
			//设置生日
			users.setBirthday(TimeRandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			
			//System.out.println(users);
			
			//String 类型
			//opsForValue.set(i+"", users);
			
			//hash  类型
			boundHashOps.put(i+"", users.toString());
			
			
		}
		long time2 = System.currentTimeMillis();
		long time=time2-time1;
		System.out.println("目标为:hash序列化,存储数量为:50000,运行时间为"+time+"ms");
		
	}

}
