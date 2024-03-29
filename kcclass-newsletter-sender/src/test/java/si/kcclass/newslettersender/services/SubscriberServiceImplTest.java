package si.kcclass.newslettersender.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/applicationContext.xml"})
@Transactional
public class SubscriberServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private SubscriberService subscriberService;
	
	private final long ADVERTISER_ID = 1;
	private Advertiser advertiser;

	@BeforeTransaction
	public void setupData() throws Exception {
		if (countRowsInTable("subscriber") == 0) {
			executeSqlScript("classpath:import.sql", false);
		}
	}

	@Before
	public void setUp() throws Exception {
		advertiser = Advertiser.findAdvertiser(ADVERTISER_ID);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByAdvertiserPaged() {
		Page<Subscriber> subscribers = subscriberService.findByAdvertiser(advertiser, 1, 10);
		assertNotNull(subscribers);
		assertTrue(subscribers.getTotalElements() == 1);
	}
	
	@Test
	public void testFindByAdvertiserAll() {
		List<Subscriber> subscribers = subscriberService.findByAdvertiser(advertiser);
		assertNotNull(subscribers);
		assertTrue(subscribers.size() == 1);
	}

}
