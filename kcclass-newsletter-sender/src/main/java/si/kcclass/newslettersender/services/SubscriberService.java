package si.kcclass.newslettersender.services;

import java.util.List;

import org.springframework.data.domain.Page;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

public interface SubscriberService {
	
	public List<Subscriber> findByAdvertiser(Advertiser advertiser);
	
	public Page<Subscriber> findByAdvertiser(Advertiser advertiser, int page, int pageSize);
	
}
