package si.kcclass.newslettersender.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

@Transactional(readOnly = true)
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	public List<Subscriber> findByAdvertiser(Advertiser advertiser);
	public Page<Subscriber> findByAdvertiser(Advertiser advertiser, Pageable pageable);
	
}
