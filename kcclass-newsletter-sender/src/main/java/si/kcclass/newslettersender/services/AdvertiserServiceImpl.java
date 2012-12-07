package si.kcclass.newslettersender.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.repositories.AdvertiserRepository;

@Service
@Transactional(readOnly=true)
public class AdvertiserServiceImpl implements AdvertiserService {

	@Autowired
	private AdvertiserRepository advertiserRepository;
	
	@Override
	public Advertiser findByUsername(String username) {
		return advertiserRepository.findByUsername(username);
	}

}
