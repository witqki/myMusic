package com.example.myMusic.common.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import net.minidev.json.JSONObject;

public class Jwt {
	
	private static Logger logger = LoggerFactory.getLogger(Jwt.class);

	// 密钥
	private static final byte[] SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW".getBytes();
	
	// 初始化 header 部分的数据
	private static final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);
	
	/**
	 * 生成 token
	 * @param payload 存储用户 id, token 生成时间, token 过期时间等自定义字段
	 * @return token 字符串
	 */
	public static String createToken(Map<String, Object> payload) {
		
		String tokenString = null;
		JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
		
		try {
			
			// 将 jwsObject 进行 MAC 签名
			jwsObject.sign(new MACSigner(SECRET));
			tokenString = jwsObject.serialize();
			
		} catch(Exception e) {
			
			logger.error("Jwt.createToken 签名失败.", e);
			
		}
		
		logger.info("Jwt.createToken token: {}", tokenString);;
		return tokenString;
		
	}
	
	/**
	 * 校验 token 是否合法, 返回 Map 集合
	 * @param 请求头部携带的 token
	 * @return 包含 state 状态码, data 鉴权成功后从 token 中提取的数据
	 */
	public static Map<String, Object> validToken(String token) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			JWSObject jwsObject = JWSObject.parse(token);
			Payload payload = jwsObject.getPayload();
			JWSVerifier verifier = new MACVerifier(SECRET);
			
			if(jwsObject.verify(verifier)) {
				
				JSONObject jsonObject = payload.toJSONObject();
				
				// token 检验成功(此时没有校验是否过期)
				resultMap.put("state", TokenState.VALID.toString());
				
				// 若payload包含ext字段，则校验是否过期
				if(jsonObject.containsKey("ext")) {
					
					long extTime = Long.valueOf(jsonObject.get("ext").toString());
					long curTime = new Date().getTime();
					
					// 过期了
					if(curTime > extTime) {
						resultMap.clear();
						resultMap.put("state", TokenState.EXPIRED.toString());
					}
					
				}
				
				resultMap.put("data", jsonObject);
				
			} else {
				// 检验失败
				resultMap.put("state", TokenState.INVALID.toString());
			}
			
		} catch(Exception e) {
			
			resultMap.clear();
			resultMap.put("state", TokenState.INVALID.toString());
			logger.error("Jwt.validToken token 格式不合法. ", e);
			
		}
		
		logger.info("Jwt.validToken resultMap: ", resultMap);
		return resultMap;
		
	}
	
}
