package si.kcclass.newslettersender.services;

import org.springframework.data.domain.Page;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

public interface SubscriberService {
	
	public Page<Subscriber> findByAdvertiser(Advertiser advertiser, int page, int pageSize);
	
}
