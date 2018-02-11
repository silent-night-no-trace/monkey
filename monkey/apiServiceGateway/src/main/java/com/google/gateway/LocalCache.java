package com.google.gateway;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 缓存工具
 * 
 * @author 秦瑞华
 *
 */
@Slf4j
public class LocalCache {

	/**
	 * 请求缓存
	 */
	private static LoadingCache<String, String> RequestCache = CacheBuilder.newBuilder()
            //当缓存项在指定的时间段内没有被读或写就会被回收
		.expireAfterAccess(1, TimeUnit.MINUTES)
            // 设置缓存个数
		.maximumSize(1000)
		.build(new CacheLoader<String, String>() {
		    @Override
		    /** 当本地缓存命没有中时，调用load方法获取结果并将结果缓存 **/
		    public String load(String key) {
		    	return "Y";
		    }
		
		}
	);
	
	
	/**
	 * 是否重复请求
	 * @param req  请求地址
	 */
	public static boolean repeatRequest(String req) {
    	if(RequestCache.getIfPresent(req) != null) {
    		//1分钟内有过借款请求,error
    		return true;
    	}
		//无借款请求
    	RequestCache.put(req, "Y");
    	return false;
	}
	
	/**
	 * 客户请求缓存<br>
	 * 同一客户5秒内只允许请求一次
	 * 过期时间5秒<br>
	 */
	private static Cache<String, Integer> CustSecondRequestCache = CacheBuilder.newBuilder()
            //当缓存项在指定的时间段内没有被读或写就会被回收
			.expireAfterAccess(5, TimeUnit.SECONDS)
            // 设置缓存个数
			.maximumSize(1000)
			.build();


	/**
	 * 请求太快
	 * 5000ms内不允许请求2次相同的请求
	 *
	 * @param req  请求地址
	 */
	public static boolean custSecondRequestTooSoon(String req) {
		Integer v = CustSecondRequestCache.getIfPresent(req);
		if(v  != null) {
			//5000ms内有该请求
			return true;
		}
		//缓存该请求
		CustSecondRequestCache.put(req, 0);
		return false;
	}
	

	/**
	 * 短信验证码缓存<br>
	 * <IP, 2小时内发送次数>
	 */
	private static Cache<String, Integer> VcodeCache2Hours = CacheBuilder.newBuilder()
            //缓存时间：6小时,复读缓存时间不会延时
			.expireAfterWrite(6, TimeUnit.HOURS)

            //设置缓存个数5k
			.maximumSize(5000)
			.build();

	/**
	 * IP黑名单，有效期7天
	 */
	private static Cache<String, Integer> BlackIPCache = CacheBuilder.newBuilder()
            //缓存时间：7天,复读缓存时间不会延时
			.expireAfterWrite(7, TimeUnit.DAYS)
            //设置缓存个数5k
			.maximumSize(5000)
			.build();

	/**
	 * 检测IP地址的短信发送情况
	 * 
	 * IP地址2小时内发送短信超过20次，自动加入IP黑名单
	 *
	 * @param ip ip地址
	 */
	public static void checkVcodeOfIp(String ip) {
		if(StringUtils.isEmpty(ip)) {
			return;
		}
		Integer count = VcodeCache2Hours.getIfPresent(ip);
		if(count == null) {
			count = 0;
		}
		int newCount = count+1;
		VcodeCache2Hours.put(ip, newCount);
		
		if(newCount >= 20) {
			//IP地址2小时内发送短信超过20次，自动加入IP黑名单
			putBlackIP(ip);
		}
        log.info(String.format("ip=%s, vcodeCount=%s", ip, newCount));
	}

	/**
	 * IP黑名单检测
	 *
	 * @param ip ip地址
	 */
	public static boolean hitBlackIP(String ip) {
		if(StringUtils.isEmpty(ip)) {
			return false;
		}
		Integer v = BlackIPCache.getIfPresent(ip);
		if(v != null) {
			//IP黑名单
			return true;
		}
		return false;
	}

	/**
	 * 录入IP黑名单
	 *
	 * @param ip ip地址
	 */
	public static void putBlackIP(String ip) {
		if(StringUtils.isEmpty(ip)) {
			return;
		}
		BlackIPCache.put(ip, 0);
	}
}
