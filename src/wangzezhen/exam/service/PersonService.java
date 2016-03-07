package wangzezhen.exam.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import wangzezhen.exam.domain.IPerson;
import wangzezhen.exam.domain.Person;

public class PersonService implements IPerson {

	@SuppressWarnings("unchecked")
	@Override
	public void loadList() {
		// if(FL.exists()){
		// 读取文件FL中的数据并保存在列表 PERSON 中
		// 反序列化
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(new FileInputStream(FL));
			// list = in.readObject();
			try {
				PERSON.putAll((Map<String, Person>) in.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// }
		// return PERSON;
	}

	@Override
	public void saveList() {
		// 保存新的用户列表
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(FL));
			out.writeObject(PERSON);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean isFileExist() {

		if (FL.exists()) {
			return true;
		}
		return false;

	}

	@Override
	public boolean isExist(String username) {
		// 判断用户是否存在
		boolean flag = false;

		for (Iterator<String> it = PERSON.keySet().iterator(); it.hasNext();) {
			if (it.next().equalsIgnoreCase(username)) {
				flag = true;
				break;
			}

		}

		return flag;
	}

	
	@Override
	// 登陆实现
	public boolean isLogin(String username, String password) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		int i = 3;
		Person per;
		// for(Iterator<String> it = PERSON.keySet().iterator();it.hasNext();){
		if (isExist(username)) {
			per = PERSON.get(username);
			String psw = per.getPassward();
			while (!(password.equals(psw)) && i > 1) {
				i--;
				System.out.println("密码错误，您还有" + i + "次机会，请确认后再输入");
				password = in.next();
			}
			if (password.equals(psw)) {
				System.out.println("欢迎您" + per.getName());
				flag = true;
			} else {
				System.out.println("机会已经用完了 再见");
			}
		}

		// }

		return flag;
	}

	private boolean isExistl(String name) {

		// 判断用户是否存在
		boolean flag = false;

		for (Iterator<Person> it = PERSON.values().iterator(); it.hasNext();) {
			String srt = it.next().getName();
			if (srt.equalsIgnoreCase(name)) {
				flag = true;
				break;
			}

		}

		return flag;
	}
	@Override
	public Person register() {
		// 注册并保存到用户列表中
		String name;
		String username;
		String password;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("请输入你的账号");
		username = in.next();
		while (isExist(username)) {
			System.out.println("账号已经存在");
			System.out.println("请输入账号");
			username = in.next();
		}
		System.out.println("请输入你的密码");
		password = in.next();
		System.out.println("请输入你的昵称");
		name = in.next();
		while (isExistl(name)) {
			System.out.println("请输入你的昵称");
			name = in.next();
		}
		Person per = new Person(username, name, password);
		PERSON.put(username, per);
		return per;

	}

	@Override
	public void revisePassword(Person per) {
		// 修改密码
		opinionHandleService ohp = new opinionHandleService();
		int i = 3;
		boolean flag = false;
		String pass_old = "";
		String pass_new1 = "";
		String pass_new2 = "";
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("请输入你的旧密码");
		pass_old = in.next();
		System.out.println("请输入你的新密码");
		pass_new1 = in.next();
		System.out.println("请再次输入你的新密码");
		pass_new2 = in.next();
		flag = yanzheng(per, pass_old);
		while (!flag && i > 0) {
			i--;
			System.out.println("密码输入错误，" + i + "还有次机会请重新输入");
			System.out.println("请输入你的旧密码");
			pass_old = in.next();
			System.out.println("请输入你的新密码");
			pass_new1 = in.next();
			System.out.println("请再次输入你的新密码");
			pass_new2 = in.next();
			flag = yanzheng(per, pass_old);
		}
		while (!pass_new1.equals(pass_new2)) {
			System.out.println("两次输入不同，请重新输入");
			System.out.println("请输入你的新密码");
			pass_new1 = in.next();
			System.out.println("请再次输入你的新密码");
			pass_new2 = in.next();
		}
		System.out.println("确认修改吗？YES:Y.NO:N");
		if (ohp.isOut()) {
			per.setPassward(pass_new1);
			PERSON.put(per.getUsername(), per);
			System.out.println("修改成功");
			saveList();
		}

	}

	// 验证密码是否正确
	private boolean yanzheng(Person per, String pass_old) {
		String pass = per.getPassward();
		if (pass.equals(pass_old)) {
			return true;
		} else {

			return false;
		}

	}

	@Override
	public void reviseName(Person per) {
		// 修改用户名
		opinionHandleService ohp = new opinionHandleService();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int i = 3;
		boolean flag = false;
		String pass_old = "";
		String name_new1 = "";
		System.out.println("请输入你的密码");
		pass_old = in.next();
		System.out.println("请输入你的新昵称");
		name_new1 = in.next();
		flag = yanzheng(per, pass_old);
		while (!flag && i > 0) {
			i--;
			System.out.println("密码输入错误，" + i + "还有次机会请重新输入");
			System.out.println("请输入你的密码");
			pass_old = in.next();
			System.out.println("请输入你的新昵称");
			name_new1 = in.next();
			flag = yanzheng(per, pass_old);
		}
		System.out.println("确认修改吗？YES:Y.NO:N");
		if (ohp.isOut()) {
			per.setName(name_new1);
			PERSON.put(per.getUsername(), per);
			saveList();
			System.out.println("修改成功");
		}
	}

	// //判断是否确认退出
	// public boolean isOut(){
	// Scanner in = new Scanner(System.in);
	// String s = in.next();
	// if(s.equalsIgnoreCase("Y")){
	// return true;
	// }else if(s.equalsIgnoreCase("n")){
	// return false;
	// }else{
	// System.out.println("输入错误，默认为输入NO");
	// return false;
	//
	// }
	// }

	// @Override
	// public void savePerson(Person per) {
	// // 添加用户到列表中
	// PERSON.put(per.getUsername(), per);
	// }

}
