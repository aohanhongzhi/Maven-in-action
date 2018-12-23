package cn.com.mvnbook.demo.tp02;

/**
 * 这是为了研究Maven，写的第一个java代码
 * 功能很简单，输出一个HelloWorld的问候
 * 
 * @author Noble
 * @version 1.0
 * */
public class HelloWorld {
	/**
	 * 输出问候
	 * @param name String，说话人名称
	 * @return String 格式是：xxx say HelloWorld
	 * */
    public String say(String name){
    	return name + " say HelloWorld";
    }
}

