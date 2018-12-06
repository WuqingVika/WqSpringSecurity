/**
 * 
 */
package com.wq.service.impl;
import org.springframework.stereotype.Service;
import com.wq.service.HelloService;
@Service
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.wq.service.HelloService#greeting(java.lang.String)
	 */
	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello "+name;
	}

}
