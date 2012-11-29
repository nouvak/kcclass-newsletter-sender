package si.kcclass.newslettersender.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import si.kcclass.newslettersender.domain.SecurityRole;

@RequestMapping("/securityroles")
@Controller
@RooWebScaffold(path = "securityroles", formBackingObject = SecurityRole.class)
public class SecurityRoleController {
}
