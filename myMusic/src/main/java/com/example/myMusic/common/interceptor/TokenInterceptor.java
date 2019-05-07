package com.example.myMusic.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.myMusic.common.jwt.Jwt;
import com.example.myMusic.common.jwt.TokenState;

import net.minidev.json.JSONObject;

public class TokenInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	//public static final String[] NO_MATCH_PATH = {".*/(user).*",""};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");
		response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");

		if (request.getMethod().equals("OPTIONS")) {

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("OPTIONS returns OK");

			return true;
		}

		String token = request.getHeader("token");
//        for(String str:NO_MATCH_PATH) {
//			if (request.getServletPath().matches(str)) {
//	
//				return true;
//	
//			}
//		}
		if (token != null) {

			Map<String, Object> resultMap = Jwt.validToken(token);

			logger.info("TokenInterceptor 获取 token 解析结果: {}", resultMap);

			TokenState tokenState = TokenState.getTokenState((String) resultMap.get("state"));

			switch (tokenState) {

			case VALID:
				logger.info("TokenInterceptor 有效token. resultMap: {}", resultMap);
				Map<String, Object> payload = (Map<String, Object>) resultMap.get("data");
				Long uid = (Long) payload.get("uid");
				Long flag = (Long)payload.get("flag");
				request.setAttribute("uid", uid);
				request.setAttribute("flag", flag);
				return true;

			case EXPIRED:
			case INVALID:
				logger.info("TokenInterceptor 无效token. resultMap: {}", resultMap);
				break;
			}

		}
		else {
			return true;
		}
		// token过期或者无效，则输出错误信息返回给客户端
		JSONObject outputMSg = new JSONObject();
		outputMSg.put("success", false);
		outputMSg.put("msg", "请先登陆!");
		response.getWriter().write(outputMSg.toJSONString());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
