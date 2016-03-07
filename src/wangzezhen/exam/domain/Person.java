package wangzezhen.exam.domain;

import java.io.Serializable;

/**
 * 考试人员类
 * @author 王泽振
 *2016年3月4日 下午8:10:11
 */
public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5905020138537817598L;

	private String username;//账号
	private String name;//用户名
	private String passward;//密码
	private AnswerAndScorce aas;//成绩
	
	/**
	 * 构造
	 */
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Person(String name, String passward, AnswerAndScorce aas) {
//		super();
//		this.name = name;
//		this.passward = passward;
//		this.aas = aas;
//	}

	public Person(String username, String name, String passward) {
		super();
		this.username = username;
		this.name = name;
		this.passward = passward;
	}

	public Person(String username, String name, String passward,
			AnswerAndScorce aas) {
		super();
		this.username = username;
		this.name = name;
		this.passward = passward;
		this.aas = aas;
	}


	/**
	 * 封装
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public AnswerAndScorce getAas() {
		return aas;
	}

	public void setAas(AnswerAndScorce aas) {
		this.aas = aas;
	}
	
	public String getUsername() {
		return username;
	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
	@Override
	public String toString() {
		return "用户：" + name + ", 成绩和答案"
				+ aas ;
	}
}
/**

将答案 成绩什么的保存为列表

创建一个1档案类

2用户类

3答案 成绩类

4题库类
答案成绩  和  用户之间是多多的关系

利用用户类实现一对多


问题
**/