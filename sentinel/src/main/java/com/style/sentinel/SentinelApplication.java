package com.style.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * sentinel
 *
 * @author leon
 */
@SpringBootApplication
public class SentinelApplication {

	public static void main(String[] args) {
		initLimitConfig();
		SpringApplication.run(SentinelApplication.class, args);
	}

	private static void initLimitConfig() {
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule =new FlowRule();
		//设置资源
		rule.setResource("say");
		//限流阈值 类型
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setCount(1000);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}

}
