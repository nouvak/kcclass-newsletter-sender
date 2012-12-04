package si.kcclass.newslettersender.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;
import si.kcclass.newslettersender.services.SubscriberService;

@RequestMapping("/subscribers")
@Controller
@RooWebScaffold(path = "subscribers", formBackingObject = Subscriber.class)
public class SubscriberController {
	
	@Autowired
	private SubscriberService subscriberService;
	
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
	public String registerSubscriber(@Valid Subscriber subscriber, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, subscriber);
            return "subscribers/registerSubscriber";
        }
        uiModel.asMap().clear();
    	subscriber.persist();
    	return "redirect:/subscribers";
	}
    
    @RequestMapping(value="list/{advertiser_id}", produces = "text/html")
    public String listForAdvertiser(
    		@PathVariable("advertiser_id") Long advertiserId,  
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel) {
    	Advertiser advertiser = Advertiser.findAdvertiser(advertiserId);
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
//            uiModel.addAttribute("subscribers", Subscriber.findSubscriberEntries(firstResult, sizeNo));
//            float nrOfPages = (float) Subscriber.countSubscribers() / sizeNo;
//            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
//            uiModel.addAttribute("subscribers", Subscriber.findAllSubscribers());
        }
        return "subscribers/list";
    }

}
