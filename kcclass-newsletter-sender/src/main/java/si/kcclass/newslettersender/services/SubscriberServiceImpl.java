package si.kcclass.newslettersender.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;
import si.kcclass.newslettersender.repositories.SubscriberRepository;

@Service
@Transactional(readOnly=true)
public class SubscriberServiceImpl implements SubscriberService {
	
	@Autowired
	private SubscriberRepository subscriberRepository;

	@Override
	public List<Subscriber> findByAdvertiser(Advertiser advertiser) {
		return subscriberRepository.findByAdvertiser(advertiser);
	}

}
