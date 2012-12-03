package si.kcclass.newslettersender.web;

import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

@RequestMapping("/subscribers")
@Controller
@RooWebScaffold(path = "subscribers", formBackingObject = Subscriber.class)
public class SubscriberController {
	
    @RequestMapping(value = "register-subscriber/{advertiser_id}", method=RequestMethod.GET, produces = "text/html")
    public String registerSubscriberForm(
    		@PathVariable("advertiser_id") Long advertiserId, Model uiModel) {
    	Advertiser advertiser = Advertiser.findAdvertiser(advertiserId);
    	Subscriber subscriber = new Subscriber();
    	subscriber.setAdvertiser(advertiser);
    	uiModel.addAttribute("subscriber", subscriber);
    	uiModel.addAttribute("advertiserId", advertiserId);
        return "subscribers/registerSubscriber";
    }

    @RequestMapping(value = "register-subscriber", method = RequestMethod.POST, produces = "text/html")
	public String registerSubscriber(@Valid Subscriber subscriber) {
    	return "redirect:/subscribers";
	}
	
}
