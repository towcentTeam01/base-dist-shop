package com.towcent.dist.shop.portal;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 所有Service层测试请继承这个类
 * @author HuangTao
 * @version 2015年4月25日
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring-context-test.xml")
public abstract class BaseTest {
	
}