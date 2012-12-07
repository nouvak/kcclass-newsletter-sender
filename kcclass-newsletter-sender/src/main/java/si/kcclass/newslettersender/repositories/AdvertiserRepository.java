package si.kcclass.newslettersender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;

@Transactional(readOnly = true)
public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {

	public Advertiser findByUsername(String username);
	
}
