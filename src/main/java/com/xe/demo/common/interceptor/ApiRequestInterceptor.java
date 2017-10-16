package com.xe.demo.common.interceptor;

import com.xe.demo.common.conf.Constants;
import com.xe.demo.common.support.DataCache;
import com.xe.demo.common.support.redis.IRedisService;
import com.xe.demo.common.support.redis.TokenModel;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限拦截器
 * @author czh
 */
public class ApiRequestInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private IRedisService manager;


	private final static Logger LOG = LoggerFactory.getLogger(ApiRequestInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//启动支持@Autowired注解
		WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		//从header中得到token
		String authorization = request.getHeader(Constants.AUTHORIZATION);
		//验证token
		TokenModel model = manager.getToken(authorization);
		if (manager.checkToken(model)) {
			//如果token验证成功，将token对应的用户id存在request中，便于之后注入
			request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
			return true;
		}
		//如果验证token失败，并且方法注明了Authorization，返回401错误
		if (method.getAnnotation(Authorization.class) != null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		return true;
	}

}
