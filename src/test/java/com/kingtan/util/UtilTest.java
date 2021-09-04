package com.kingtan.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;

import com.kingtan.store.inventory.config.AppConfig;
@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = AppConfig.class)
//@TestPropertySource("classpath:application-test.properties")
public class UtilTest {
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	private String data = "{\"products\": [" + 
			"{ \"id\": 1, \"name\": \"Kayak\", \"category\": \"Watersports\", \"price\": 275 }," + 
			"{ \"id\": 2, \"name\": \"Lifejacket\", \"category\": \"Watersports\", \"price\": 48.95 }," + 
			"{ \"id\": 3, \"name\": \"Soccer Ball\", \"category\": \"Soccer\", \"price\": 19.50 }," + 
			"{ \"id\": 4, \"name\": \"Corner Flags\", \"category\": \"Soccer\", \"price\": 34.95 }," + 
			"{ \"id\": 5, \"name\": \"Stadium\", \"category\": \"Soccer\", \"price\": 79500 }," + 
			"{ \"id\": 6, \"name\": \"Thinking Cap\", \"category\": \"Chess\", \"price\": 16 }," + 
			"{ \"id\": 7, \"name\": \"Unsteady Chair\", \"category\": \"Chess\", \"price\": 29.95 }," + 
			"{ \"id\": 8, \"name\": \"Human Chess Board\", \"category\": \"Chess\", \"price\": 75 }," + 
			"{ \"id\": 9, \"name\": \"Bling Bling King\", \"category\": \"Chess\", \"price\": 1200 }" + 
			"]}";

	@BeforeAll
	public static void init() {
		log.info("UtilTest: before class");
	}
	
	@BeforeEach
	public void beforeTest() {
		log.info("UtilTest: before test");
	}

	@Test
	public void testMethos() throws MalformedURLException, IOException {
		log.info("Util tests, start ....");
		
		Map<String, List<Map<String, String>>> map =JsonUtil.getMapSLMSS(data);
		log.info("JsonUtil.getMapSLMSS:\n" + map);
		Map<String, List<Map<String, String>>> map2 =JsonUtil.getMapSLMSS2(data);
		log.info("JsonUtil.getMapSLMSS2:\n" + map2);
	}
}
