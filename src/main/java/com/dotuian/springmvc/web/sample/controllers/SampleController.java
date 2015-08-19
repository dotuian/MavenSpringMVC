package com.dotuian.springmvc.web.sample.controllers;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dotuian.springmvc.common.annotations.AuthPassport;
import com.dotuian.springmvc.service.SampleService;
import com.dotuian.springmvc.web.base.controllers.BaseController;
import com.dotuian.springmvc.web.sample.dto.Book;
import com.dotuian.springmvc.web.user.forms.User;


/**
 * @Controller，类级别上的注解。我们定义的类可以只是一个 javabean，不需要实现任何接口。 
 * @Controller，借助 <context:component-scan>，框架能自动识别到这就是一个 Controller 
 * 
 * @RequestMapping 可以出现在类级别上，也可以出现在方法上。
 * 类级别的 @RequestMapping 不是必需的。  
 * 
 * 
 * 
 * 
 */
@AuthPassport
@Controller
@RequestMapping(value = "/sample")
public class SampleController extends BaseController {
	
	// 自动装配
	@Autowired
	private SampleService sampleService;

	
	/**
	 *  在方法级别使用 @RequestMapping 来限定请求处理的时候，可以指定两个属性
	 * 1. method 属性 : 
	 * 2. params 属性 : params 有两种表达形式
	 *   -> 表达式一 "parameterName=parameterValue" : 请求的参数和参数的值完全匹配时执行 
     *   -> 表达式二 "parameter" : 只要具有请求的参数，不管参数是什么值。都会执行
	 * @param name
	 * @return
	 */
    @RequestMapping(params="hello=world", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String hello(@RequestParam String hello){
    	return "params 属性1 : " + hello;
    }
    
    @RequestMapping(params="world", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String world(@RequestParam String world){
    	return "params 属性2" + world;
    }
	
    /**
     * 默认的参数绑定
     * 默认的绑定行为需要我们严格遵守命名一致性原则
     * 
     * 方法的参数名为 userId， 那么请求参数中一定有名为 userId 的参数，且值为整数，这也是默认的绑定行为。
     * 它是根据名称匹配原则进行的数据绑定。当请求中的参数名与方法名一致的时候，相应的参数值将被绑定到相应的方法参数上。
     * 
     * 如果没有传递 userId 参数，框架会传入 null。 但是定义的是int，会抛出异常，将userid类型改为Integer就可以了。
     * 如果userId传递参数不是int，则会出现数据类型转换失败，ExceptionResolver 会接手处理，请求是不会进入该方法。
     * 
     * @param userid
     * @return
     */
	@RequestMapping(value = "/param1")
	@ResponseBody
	public String testParam1(int userid) {
		return String.valueOf(userid);
	}
    
	/**
	 * 
	 * 请求参数中有 age，和方法的参数名称一致，故 age 参数不需要 @RequestParam 标注,
	 * 如果请求中没有设置age的参数，则会传入null到int类型，则会异常，这是由于 @RequestParam 的 required 属性决定的，默认就是 true
	 * 可以通过在参数前设置 @RequestParam(required=false)，即便请求中没有设置age的参数，也不会出错
	 * 
	 * 
	 * @param userid
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/param2")
	public String testParam2(int userid, @RequestParam(required=false) int age){
		
		return userid + "  " + age;
	}
	
	/**
	 * 在参数中，还可以绑定对象
	 * 
	 * @RequestBody : HttpMessageConverter 从请求正文到对象转换的方向
	 * @ResponseBody: 将处理完请求后返回的对象绑定到响应正文，将处理完请求后返回的对象序列化成字符串
	 * 
	 * @param id
	 * @param age
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/param3")
	@ResponseBody 
	public String testParam3(@RequestParam("userId") int id, int age, Date date){
		return date.toString();
	}
	
	
	@RequestMapping(value = "/param4/body")
	@ResponseBody
	public String testParam4(@RequestBody String body) {  
		// 这里的 body 的内容就是 hello  
		System.out.println(body);  
		return body;  
	}

	
    /**
     * @PathVariable 是 url 模板，需要和 @RequestMapping 配合起来使用
     * 在下面的方法中，请求URL必须匹配   /sample/param4/shoukii/29 的格式，该方式才会被调用
     * 
     * @param name
     * @return
     */
	@RequestMapping(value = "/param4/{nickname}/{age}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable("nickname") String name, @PathVariable int age) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("stringList", sampleService.getStringList());
		model.addObject("intArray", sampleService.getArray());

		return model;
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public User getUser() {
		User user = new User();
		user.setUserid(RandomUtils.nextLong());
		user.setUsername(String.valueOf(RandomUtils.nextDouble()));
		user.setAge(RandomUtils.nextInt());
		user.setSex(RandomUtils.nextInt(2));
		return user;
	}
	
	
	/**
	 * @ModelAttribute 注释的方法会在此controller每个方法执行前被执行
	 * 因此可以用来做权限控制，
	 * 
	 * 
	 * 通过 model.addAttribute("username", abc); 加入到Model中的数据，可以再视图中通过 {username} 来获取
	 */
	@ModelAttribute
	public void model1(@RequestParam(required = false) String abc, Model model){
		logger.error("====>>> model");
		
		model.addAttribute("username", abc);
	}
	
	/**
	 * 返回类型隐含表示，如这个方法返回User类型，那么这个model属性的名称是user
	 * 在视图和处理请求的方法中，通过 ${user}来调用，如 <c:out value="${user.username}"></c:out>
	 * 
	 * 
	 * 也可以通过 @ModelAttribute(value="MyUser")来指定属性名，
	 * 在视图和处理请求的方法中，通过 ${MyUser.username} 来引用
	 * 
	 * @return
	 */
	@ModelAttribute
	public User model2() {
		logger.error("====>>> mode2");
		User user = new User();
		user.setUserid(RandomUtils.nextInt());
		user.setUsername(String.valueOf(RandomUtils.nextDouble()));
		user.setAge(RandomUtils.nextInt());
		user.setSex(RandomUtils.nextInt(2));
		return user;
	}
	
	@RequestMapping(value = "/helloWorld")  
    public ModelAndView modelAttribute(User user, String username) {
		logger.error("====>>> modelAttribute");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sample/index");
		mv.addObject("stringList", sampleService.getStringList());
		mv.addObject("intArray", sampleService.getArray());
		
		logger.info(user.toString());
		logger.info("username = " + username);
		user.setUsername("SHOU");
		
		return mv;
    }  	
	
	@RequestMapping(value = "list")
	@ResponseBody
	public List<String> list() {
		return sampleService.getStringList();
	}

	@RequestMapping(value = "/string", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String string() {

		return "String var";
	}
	
	
	
	
	
	
	//==============================================================================
	// 
	//==============================================================================
	
	// 示例代码
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sample/index");
		return mv;
	}
	
	
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public ModelAndView exception() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sample/index");
		
		Book book = null;
		System.out.println(book.toString());
		
		return mv;
	}
	
	
	
	
//	//PDF文件生成示例
//	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
//	public ModelAndView pdf() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/sample/pdf");
//		return mv;
//	}
//
//	//XML文件生成示例
//	@RequestMapping(value = "/xml", method = RequestMethod.GET)
//	public ModelAndView xml() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/sample/xml");
//		return mv;
//	}
//	
//	//JSON文件生成示例
//	@RequestMapping(value = "/json", method = RequestMethod.GET)
//	public ModelAndView json() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/sample/json");
//		return mv;
//	}
	
	
}
