package si.kcclass.newslettersender.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Newsletter;
import si.kcclass.newslettersender.domain.Subscriber;
import si.kcclass.newslettersender.services.AdvertiserService;
import si.kcclass.newslettersender.services.SubscriberService;

@RequestMapping("/subscribers")
@Controller
@RooWebScaffold(path = "subscribers", formBackingObject = Subscriber.class)
public class SubscriberController {
	
	protected static Logger logger = Logger.getLogger(SubscriberController.class);
	
	@Autowired
	private SubscriberService subscriberService;
	
	@Autowired
	private AdvertiserService advertiserService;
	
	@Autowired
	private MailSender mailSender;
	
    private String registerSubscriberForm(Long advertiserId, Model uiModel) {
    	Advertiser advertiser = Advertiser.findAdvertiser(advertiserId);
    	Subscriber subscriber = new Subscriber();
    	subscriber.setAdvertiser(advertiser);
    	uiModel.addAttribute("subscriber", subscriber);
    	uiModel.addAttribute("advertiserId", advertiserId);
        return "subscribers/registerSubscriber";
    }
    
    @RequestMapping(value = "register-subscriber/{advertiser_id}", method=RequestMethod.GET, produces = "text/html")
    public String registerSubscriberFormWithId(
    		@PathVariable("advertiser_id") Long advertiserId, 
    		Model uiModel) {
    	return registerSubscriberForm(advertiserId, uiModel);
    }
    
    @RequestMapping(value = "register-subscriber", method=RequestMethod.GET, produces = "text/html")
    public String registerSubscriberFormWithoutId(Model uiModel, Principal principal) {
    	Advertiser advertiser = advertiserService.findByUsername(principal.getName());
    	return registerSubscriberForm(advertiser.getId(), uiModel);
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
    
    @RequestMapping(value="list", produces = "text/html")
    public String listForAdvertiser(  
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel,
    		Principal principal) {
    	Advertiser advertiser = advertiserService.findByUsername(principal.getName());
    	int sizeNum = size == null ? 10 : size.intValue();
    	int pageNum = page == null ? 1 : page.intValue();
    	Page<Subscriber> subscribers = subscriberService.findByAdvertiser(advertiser, pageNum, sizeNum);
    	uiModel.addAttribute("subscribers", subscribers.getContent());
    	uiModel.addAttribute("maxPages", subscribers.getTotalPages());
        return "subscribers/listForAdvertiser";
    }

    @RequestMapping(value="send-newsletter/{advertiser_id}", produces = "text/html")
    public String sendNewsletterForm(
    		@PathVariable("advertiser_id") Long advertiserId,
    		Model uiModel) {
    	uiModel.addAttribute("advertiserId", advertiserId);
    	uiModel.addAttribute("newsletterMessage", new Newsletter());
    	return "subscribers/createMessage";
    }
    
    @RequestMapping(value="send-newsletter")
    public String sendNewsletter(
    		Long advertiserId,
    		Newsletter newsletter,
    		Model uiModel) {
    	Advertiser advertiser = Advertiser.findAdvertiser(advertiserId);
    	List<Subscriber> subscribers = subscriberService.findByAdvertiser(advertiser);
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom(advertiser.getEmail());
    	message.setSubject(newsletter.getTitle());
    	for (Subscriber subscriber: subscribers) {
    		message.setTo(subscriber.getEmail());
    		message.setText(String.format("Dear %s %s, this is a test message:\n%s", 
    				subscriber.getSurname(), subscriber.getName(), newsletter.getContent()));
    		try{
                mailSender.send(message);
            }
            catch(MailException ex) {
            	logger.error("Mail sending failed for recipient: " + subscriber.getEmail(), ex);
            }
    	}
    	return "redirect:/subscribers";
    }
}
