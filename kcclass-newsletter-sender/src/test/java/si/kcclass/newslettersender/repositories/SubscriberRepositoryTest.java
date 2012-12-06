package si.kcclass.newslettersender.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/applicationContext.xml"})
@Transactional
public class SubscriberRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	private final long ADVERTISER_ID = 1;
	private Advertiser advertiser;

	@Before
	public void setUp() throws Exception {
		if (countRowsInTable("subscriber") == 0) {
			executeSqlScript("classpath:import.sql", false);
		}
		advertiser = Advertiser.findAdvertiser(ADVERTISER_ID);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByAdvertiserPaged() {
		PageRequest page = new PageRequest(0, 10);
		Page<Subscriber> subscribers = subscriberRepository.findByAdvertiser(advertiser, page);
		assertNotNull(subscribers);
		assertTrue(subscribers.getTotalElements() == 1);
	}

	@Test
	public void testFindByAdvertiserAll() {
		List<Subscriber> subscribers = subscriberRepository.findByAdvertiser(advertiser);
		assertNotNull(subscribers);
		assertTrue(subscribers.size() == 1);
	}

}
