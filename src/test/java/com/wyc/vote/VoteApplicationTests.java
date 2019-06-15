package com.wyc.vote;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(VoteApplicationTests.class);   

	@Test
	public void contextLoads() {
		logger.error("=========================TEST===============================");
		logger.error("=========================TEST===============================");
		logger.error("=========================TEST===============================");
		logger.error("=========================TEST===============================");
	}

	@Test
	public void contextLoads1() {
		logger.error("=========================TEST1===============================");
		logger.error("=========================TEST1===============================");
		logger.error("=========================TEST1===============================");
		logger.error("=========================TEST1===============================");
	}

	@Test
	public void contextLoads2() {
		logger.error("=========================TEST2===============================");
		logger.error("=========================TEST2===============================");
		logger.error("=========================TEST2===============================");
		logger.error("=========================TEST2===============================");
	}

}
