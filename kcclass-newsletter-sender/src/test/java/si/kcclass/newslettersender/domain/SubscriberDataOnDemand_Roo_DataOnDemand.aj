// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package si.kcclass.newslettersender.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import si.kcclass.newslettersender.domain.AdvertiserDataOnDemand;
import si.kcclass.newslettersender.domain.Subscriber;
import si.kcclass.newslettersender.domain.SubscriberDataOnDemand;

privileged aspect SubscriberDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SubscriberDataOnDemand: @Component;
    
    private Random SubscriberDataOnDemand.rnd = new SecureRandom();
    
    private List<Subscriber> SubscriberDataOnDemand.data;
    
    @Autowired
    private AdvertiserDataOnDemand SubscriberDataOnDemand.advertiserDataOnDemand;
    
    public Subscriber SubscriberDataOnDemand.getNewTransientSubscriber(int index) {
        Subscriber obj = new Subscriber();
        setEmail(obj, index);
        setName(obj, index);
        setSurname(obj, index);
        return obj;
    }
    
    public void SubscriberDataOnDemand.setEmail(Subscriber obj, int index) {
        String email = "foo" + index + "@bar.com";
        obj.setEmail(email);
    }
    
    public void SubscriberDataOnDemand.setName(Subscriber obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public void SubscriberDataOnDemand.setSurname(Subscriber obj, int index) {
        String surname = "surname_" + index;
        obj.setSurname(surname);
    }
    
    public Subscriber SubscriberDataOnDemand.getSpecificSubscriber(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Subscriber obj = data.get(index);
        Long id = obj.getId();
        return Subscriber.findSubscriber(id);
    }
    
    public Subscriber SubscriberDataOnDemand.getRandomSubscriber() {
        init();
        Subscriber obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Subscriber.findSubscriber(id);
    }
    
    public boolean SubscriberDataOnDemand.modifySubscriber(Subscriber obj) {
        return false;
    }
    
    public void SubscriberDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Subscriber.findSubscriberEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Subscriber' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Subscriber>();
        for (int i = 0; i < 10; i++) {
            Subscriber obj = getNewTransientSubscriber(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
