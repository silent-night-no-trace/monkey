package com.style.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.node.Node;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leon
 * @date 2019-11-14 15:40:21
 */
public class SentinelMain {

	@SentinelResource("printHello")
	public void printHello() {

		System.out.println("everyone hello");
	}

	public static void main(String[] args) throws Exception {
		initLimitConfig();
		for (; ; ) {
			try {
				Entry entry = SphU.entry("printHello");
				Node curNode = entry.getCurNode();

				System.out.println("总共请求:" + curNode.totalRequest() + ", 总qps: " + curNode.totalPass()+", 通过的qps:" + curNode.passQps()   + ",当前qps: " + curNode.passQps()+
						",阻塞qps:"+curNode.blockQps()+",线程数:"+curNode.curThreadNum()+",阻塞请求:"+curNode.blockRequest());

			} catch (BlockException e) {
				e.printStackTrace();
			}
		}

	}

	private static void initLimitConfig() {
		List<FlowRule> flowRules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		//设置资源
		rule.setResource("printHello");
		rule.setCount(1000);
		//表示流量控制的阈值类型。
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		//设置策略
		//rule.setStrategy(RuleConstant.STRATEGY_RELATE);
		//rule.setLimitApp("test-main");

		flowRules.add(rule);
		FlowRuleManager.loadRules(flowRules);

	}


}
