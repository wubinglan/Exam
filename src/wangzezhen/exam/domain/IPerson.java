package wangzezhen.exam.domain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口类
 * @author 王泽振
 *2016年3月5日 下午2:19:32
 */
public interface IPerson {

//	List<Person> PERSON = new ArrayList<Person>(300);
	Map<String, Person> PERSON = new HashMap<String, Person>();
	
	File FL = new File("Username"+File.separator+"userList.ser");
	//判断用户是否存在
	boolean isExist(String username);
	//登陆 
	boolean isLogin(String username,String password);
	//注册
	Person register();
	//修改密码
	void  revisePassword(Person per);
	//修改用户名
	void  reviseName(Person per);
	//读取列表
	 void loadList();
	//判断FL文件是否存在
	 boolean isFileExist();
	 //添加用户到列表中
//	 void savePerson(Person per);
	 //保存列表
	 void saveList();
}
