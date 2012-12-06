package si.kcclass.newslettersender.web;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import si.kcclass.newslettersender.domain.Advertiser;
import si.kcclass.newslettersender.domain.Subscriber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-test.xml", "file:src/main/webapp/WEB-INF/spring/webmvc-config-test.xml"})
public class SubscriberControllerTest {
	
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	private final long ADVERTISER_ID = 1; 
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterSubscriberForm() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(
				"GET", "/subscribers/register-subscriber/" + ADVERTISER_ID);
		MockHttpServletResponse response = new MockHttpServletResponse();
		Object handler = handlerMapping.getHandler(request).getHandler();
		ModelAndView mav = handlerAdapter.handle(request, response, handler);
        Assert.assertNotNull(mav);
        ModelAndViewAssert.assertViewName(mav, "subscribers/registerSubscriber");
	}

	@Test
	public void testRegisterSubscriber() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(
				"POST", "/subscribers/register-subscriber");
		MockHttpServletResponse response = new MockHttpServletResponse();

	    /* a proper form data. */
	    request.setParameter("name", "User");
	    request.setParameter("surname", "Test");
	    request.setParameter("email", "test@gmail.com");
	    request.setParameter("advertiser", "1");
	    		
		Object handler = handlerMapping.getHandler(request).getHandler();
		ModelAndView mav = handlerAdapter.handle(request, response, handler);
        Assert.assertNotNull(mav);
        ModelAndViewAssert.assertViewName(mav, "redirect:/subscribers");
        
        /* an empty form should generate errors. */
        request = new MockHttpServletRequest("POST", "/subscribers/register-subscriber");

		handler = handlerMapping.getHandler(request).getHandler();
		mav = handlerAdapter.handle(request, response, handler);
        Assert.assertNotNull(mav);
        ModelAndViewAssert.assertViewName(mav, "subscribers/registerSubscriber");

        final BindingResult errors = ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
                "org.springframework.validation.BindingResult.subscriber",
                BindingResult.class);
        System.out.println(errors.getAllErrors());
        Assert.assertEquals(3, errors.getErrorCount());
        //ModelAndViewAssert.assertModelAttributeValue(mav, "subscriber.email", "may not be null");
	}

	@Test
	public void testListForAdvertiser() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(
				"GET", "/subscribers/list/" + ADVERTISER_ID);
		MockHttpServletResponse response = new MockHttpServletResponse();
		Object handler = handlerMapping.getHandler(request).getHandler();
		ModelAndView mav = handlerAdapter.handle(request, response, handler);
        Assert.assertNotNull(mav);
        ModelAndViewAssert.assertViewName(mav, "subscribers/list");
	}
}
