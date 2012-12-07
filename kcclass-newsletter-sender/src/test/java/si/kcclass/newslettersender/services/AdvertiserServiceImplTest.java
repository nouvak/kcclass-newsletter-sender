package si.kcclass.newslettersender.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/applicationContext.xml"})
@Transactional
public class AdvertiserServiceImplTest {

	@Autowired
	private AdvertiserService advertiserService;
	
	private final String USERNAME = "marko";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Advertiser advertiser = advertiserService.findByUsername(USERNAME);
		assertNotNull(advertiser);
		assertEquals(advertiser.getUsername(), USERNAME);
	}

}
