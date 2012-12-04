package si.kcclass.newslettersender.services;

import java.util.List;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

public interface SubscriberService {
	
	public List<Subscriber> findByAdvertiser(Advertiser advertiser);
	
}
