package si.kcclass.newslettersender.repositories;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/applicationContext.xml"})
@Transactional
public class AdvertiserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private AdvertiserRepository advertiserRepository;
	
	private final String USERNAME = "marko";

	@Before
	public void setUp() throws Exception {
		if (countRowsInTable("subscriber") == 0) {
			executeSqlScript("classpath:import.sql", false);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByUsername() {
		Advertiser advertiser = advertiserRepository.findByUsername(USERNAME);
		assertNotNull(advertiser);
		assertEquals(advertiser.getUsername(), USERNAME);
	}

}
