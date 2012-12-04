package si.kcclass.newslettersender.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

@Transactional(readOnly = true)
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	public Page<Subscriber> findByAdvertiser(Advertiser advertiser, Pageable pageable);
	
}
