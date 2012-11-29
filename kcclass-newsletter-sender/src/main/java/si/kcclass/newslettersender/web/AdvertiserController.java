package si.kcclass.newslettersender.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import si.kcclass.newslettersender.domain.Advertiser;

@RequestMapping("/advertisers")
@Controller
@RooWebScaffold(path = "advertisers", formBackingObject = Advertiser.class)
public class AdvertiserController {
}
