package cn.com.mvnbook.demo.tp01;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHelloWorld {
	private HelloWorld hello;
	@Before
	public void init(){
		hello = new HelloWorld();
	}
	@Test
    public void testSay(){
    	String name = "张三";
    	String exp = "张三"+" say HelloWorld";
    	String act = hello.say(name);
    	Assert.assertEquals(exp, act);
    }
	@After
    public void destory(){
    	hello = null;
    }
}
