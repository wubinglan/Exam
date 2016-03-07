package wangzezhen.exam.service;

import java.util.Scanner;

/**
 * 操作判断业务类
 * @author 王泽振
 *2016年3月6日 下午6:55:24
 */
public class opinionHandleService {

	//输入判断操作
	public char getUserAction(){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		char  m =' ';
		while(!flag){
			System.out.println("请输入你要进行的操作");
			String s = in.next();
				String st = s.toUpperCase();//转换为大写
				m = st.charAt(0);
				 flag = isTrue(m);
				 if(!flag)
				 System.out.println("你的输入有误请重新输入");
		}
		return m;
	}
	
	//判断是否输入正确
	private boolean isTrue(char m){
		boolean flag = false;
		char[] ch = {'A','B','C','D','N','P','F','1','2','3','4','R','M','L'};
		for(char c : ch){
			if(c==m){
				flag = true;
				break;
			}
		}
		return flag;
		
	}
	
	//判断是否确认退出
		public boolean isOut(){
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String s = in.next();
			if(s.equalsIgnoreCase("Y")){
				return true;
			}else if(s.equalsIgnoreCase("n")){
			return false;
			}else{
				System.out.println("输入错误，默认为输入NO");
				return false;
				
			}
		}
}
