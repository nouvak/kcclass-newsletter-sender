package si.kcclass.newslettersender.services;

import si.kcclass.newslettersender.domain.Advertiser;

public interface AdvertiserService {
	
	public Advertiser findByUsername(String username);

}
