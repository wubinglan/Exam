package wangzezhen.exam.view;

import java.util.Scanner;

import wangzezhen.exam.domain.Person;
import wangzezhen.exam.service.PersonService;
import wangzezhen.exam.service.RocecardService;
import wangzezhen.exam.service.opinionHandleService;

public class MainExamView {

	opinionHandleService ohp = new opinionHandleService();
	public void mainExamView(){
		PersonService per = new PersonService();
		RocecardService rcs = new RocecardService();
		if(per.isFileExist()){
			per.loadList();
		}
		
		if(rcs.isAllFileExit()){
			rcs.loadAll();
		}
//		Scanner in = new Scanner(System.in);
		boolean flag = true ;
		
		ExamView view = new ExamView();
		opinionHandleService ohp = new opinionHandleService();
		Person pson = null;
		while(flag){
			boolean f2 = false;
			
			System.out.println();
//			System.out.println("=================*****************====================");
			System.out.println("=================******** ********====================");
			System.out.println("=================******     ******====================");
			System.out.println("=================****         ****====================");
			System.out.println("=================**             **====================");
			System.out.println("===================欢迎来到考试系统=======================");
			System.out.println();
			System.out.println();
			System.out.println("\t\t     1\t登陆\t\t");
			System.out.println("\t\t     2\t注册\t\t");
			System.out.println("\t\t     3\t退出\t\t");
			System.out.println("======================================================");
			System.out.println("=================**             **====================");
			System.out.println("=================****         ****====================");
			System.out.println("=================******     ******====================");
			System.out.println("=================******** ********====================");
			System.out.println();
			System.out.println();
			char ch = ohp.getUserAction();
			switch (ch) {
				case '1':
					pson = isLogin();
					
					if(pson == null){
						System.out.println("从新来过");
						
					}else{
						int n = 0;
						while(!f2){
							n = twoooo();
							switch (n) {
								case 1:
									view.mainOperate(pson);
									f2 = true;
									break;
								case 2:
									per.revisePassword(pson);
									break;
								case 3:
									per.reviseName(pson);
									break;
								case 4:
									System.out.println("say you good bay");
									f2 = true;
									break;
						}
						}
						
					}
					break;
					
				case '2':
					pson = per.register();
					per.saveList();	
//					if(rcs.isFileExit()){
//						rcs.load(pson);
//						
//					}
					view.mainOperate(pson);
					break;
							
				case '3':
					System.out.println("再见，欢迎再次使用");
					
					rcs.saveAll();
					flag = false;
					break;
					
		
				default:
					break;
			}
		}
	}
	//登陆
	@SuppressWarnings("static-access")
	private Person isLogin(){
		Person pson = null;
		PersonService per = new PersonService();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		boolean flag ;
		int i = 3;
		if(per.isFileExist()){
			System.out.println("输入账号");
			String username = in.next();
			boolean flag1 = per.isExist(username);
			if(flag1){
				System.out.println("您有"+i+"次机会");
				System.out.println("输入密码");
				String password = in.next();
				flag = per.isLogin(username, password);
				
				if(flag){
					pson = per.PERSON.get(username);
					String name = per.PERSON.get(username).getName();
					System.out.println("欢迎"+name+"的到来");
					
				}else{
					System.out.println("再见");
				}
			}else{
				System.out.println("文件不存在是否确认要注册，YES:Y,NO:N");
				if(ohp.isOut()){
					pson = per.register();
					per.saveList();
				}
			}
		}else{
			System.out.println("是否要注册，YES:Y,NO:N");
			if(ohp.isOut()){
				pson = per.register();
				per.saveList();
			}
		}
		return pson;
		
	}
	
	private int twoooo(){
		System.out.println("===================欢迎来到考试系统=======================");
		System.out.println("\t\t1\t考试");
		System.out.println("\t\t2\t修改密码");
		System.out.println("\t\t3\t修改名称");
		System.out.println("\t\t4\t不想考了退出");
		System.out.println("=======================================================");
		boolean flag = false;
		int i = 0;
		while(!flag){
			char ch = ohp.getUserAction();
			switch (ch) {
				case '1' :
					 i = 1;
					 flag = true;
					break;
				case '2' :
					i = 2;
					flag = true;
					break;
				case '3' :
					i = 3;
					flag = true;
					break;
				case '4' :
					i = 4;
					flag = true;
					break;
				default:
					System.out.println("请输入正确操作");
					flag = false;
					break;
			}
		}
		return i;
	}
}
