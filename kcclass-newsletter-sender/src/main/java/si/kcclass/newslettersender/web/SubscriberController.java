package si.kcclass.newslettersender.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import si.kcclass.newslettersender.domain.Subscriber;

@RequestMapping("/subscribers")
@Controller
@RooWebScaffold(path = "subscribers", formBackingObject = Subscriber.class)
public class SubscriberController {
	
	public void registerSubscriber() {
		
	}
}
