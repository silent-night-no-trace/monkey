package com.style.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流 controller
 *
 * @author leon
 * @date 2019-11-15 13:26:12
 */
@RestController
public class LimitController {

	@GetMapping("/say")
	@SentinelResource("say")
	public String say() {
		return "i say";
	}
}
