package com.dotuian.springmvc.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dotuian.springmvc.common.annotations.Layout;

/**
 * 用于设置页面渲染的拦截器 
 * 
 * 通过配置拦截器，设置页面渲染的layout。
 * <mvc:interceptors>
 * 	<bean class="com.dotuian.springmvc.common.interceptors.ThymeleafLayoutInterceptor" />
 * </mvc:interceptors>
 * 
 * 在Controller或Controller中的Action通过注解 @Layout("layouts/layout1")设置使用渲染的layout
 * 没有设置@Layout是，使用默认布局 "layouts/default"
 * 
 */
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_LAYOUT = "layouts/default";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    private String defaultLayout = DEFAULT_LAYOUT;
    private String viewAttributeName = DEFAULT_VIEW_ATTRIBUTE_NAME;

    public void setDefaultLayout(String defaultLayout) {
        Assert.hasLength(defaultLayout);
        this.defaultLayout = defaultLayout;
    }

    public void setViewAttributeName(String viewAttributeName) {
        Assert.hasLength(defaultLayout);
        this.viewAttributeName = viewAttributeName;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }

        String originalViewName = modelAndView.getViewName();
        if (isRedirectOrForward(originalViewName)) {
            return;
        }
        String layoutName = getLayoutName(handler);
        modelAndView.setViewName(layoutName);
        modelAndView.addObject(this.viewAttributeName, originalViewName);
    }
    
    /**
     * 不通过Thymeleaf渲染的请求
     * @param viewName
     * @return
     */
    private boolean isRedirectOrForward(String viewName) {
        return viewName.startsWith("redirect:") || viewName.startsWith("forward:") || "pdfView".equals(viewName);
    }

    private String getLayoutName(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Layout layout = getMethodOrTypeAnnotation(handlerMethod);
        if (layout == null) {
            return this.defaultLayout;
        } else {
            return layout.value();
        }
    }

    private Layout getMethodOrTypeAnnotation(HandlerMethod handlerMethod) {
        Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
        if (layout == null) {
            return handlerMethod.getBeanType().getAnnotation(Layout.class);
        }
        return layout;
    }
}
