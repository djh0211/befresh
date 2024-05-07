package com.a307.befresh;

import java.util.TimeZone;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BefreshApplicationTests {

	@Test
	void contextLoads() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

}
