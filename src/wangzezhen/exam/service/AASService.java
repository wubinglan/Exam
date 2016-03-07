package wangzezhen.exam.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import wangzezhen.exam.domain.AnswerAndScorce;
import wangzezhen.exam.domain.IAnswerAndScorce;
import wangzezhen.exam.domain.Item;
import wangzezhen.exam.domain.Person;
import wangzezhen.exam.domain.Rocecard;

/**
 * 答案业务类
 * @author 王泽振
 *2016年3月6日 下午5:56:18
 */
public class AASService implements IAnswerAndScorce,Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1836580120344807622L;

	@Override
	public AnswerAndScorce  testExam(Item[] items,int io) {
		int ms = items.length;
		char[] answer = new char[items.length];
		AnswerAndScorce ass = null;
		int scare = 0;
		// //获得答案
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		opinionHandleService ohp = new opinionHandleService();
		int count = 1;
		boolean flag = true;
		while(flag){
			displayItem(count,items);
			System.out.println("N下一题，P上一题\n"+"R查询，M修改\n"+"L重新开始，F退出系统");
			char ch = ohp.getUserAction();
			switch (ch) {
				case 'A':
				case 'B':
				case 'C':
				case 'D':
					answer[count-1] = ch;
				case 'N':
					if(count < ms){
						++count;
					}else{
						System.out.println("已经是最后一题了");
						 getNotTitle(items,answer);
							System.out.println("确认要退出吗？YES:Y,NO:N");
							flag = ohp.isOut();
							if(!flag){
//								System.out.println("请输入你要修改的题号");
//								count = sc.nextInt();
								System.out.println("请输入你要修改的题号");
								count =  sc.nextInt();
								while(count > ms && count <=0){
									System.out.println("请输入正确的要修改的题号");
									count = sc.nextInt();
								}
								
								flag = true;
							}else{
								System.out.println("");
								scare = getScore(answer,items);
								ass = addList(scare, answer, items);
								System.out.println("谢谢使用再见");
								flag = false;
							}
					}
					break;
					
				case 'L':
					if(io>0){
						System.out.println("确认要重新开始吗？YES:Y,NO:N");
						if(ohp.isOut()){
							//保存分数
							System.out.println("重新开始");
							if(io!=1){
								System.out.println("你还剩余"+io+"次机会在本次测试中重新开始");
							}else{
								System.out.println("你还剩余最后一次机会在本次测试中重新开始");
							}
//							System.exit(0);
							flag = false;
							
						}
					}else{
						System.out.println("你已经没有机会了");
					}
					break;
					
				case 'P':
					if(count >1){
						--count;
					}else{
						System.out.println("已经是第一题了");
					}
					break;
				case 'F':
					int m = getNotTitle(items,answer);
					if(m<4&& m!=0){
						System.out.println("确认要退出吗？YES:Y,NO:N");
						if(ohp.isOut()){
							//保存分数
							scare = getScore(answer,items);
							ass = addList(scare, answer, items);
							System.out.println("考试完成，谢谢使用再见");
							
//							System.exit(0);
							flag = false;
						}
					}else{
						System.out.println("还没做够6道题呢！着啥急！！！");
					}
					break;
				case 'R'://查询当前未作的题号并查询当前成绩
					getNotTitle(items,answer);
					break;
				case 'M'://修改题
					System.out.println("请输入你要修改的题号");
					count = (Integer) sc.nextInt();
					while(count > ms && count <=0){
						System.out.println("请输入正确的要修改的题号");
						count = (Integer)sc.nextInt();
					}
					break;
					default:
						System.out.println("输入错误");
					break;
		
			
			}
		}
		return ass;
	}

	@Override
	public int getNotTitle(Item[] items,char[] answer) {
		//未做题查询显示 并获得当前成绩
		
		ItemService it = new ItemService();
			String str ="";
			int co = 0;
			int score = 0;
			int n = items.length;
			for(int i=1;i<=n;i++){
				char c = it.getItems(i, items).getAnswer();
				char ch = answer[i-1];
				if(ch==' '){
					str += "第"+i+"题、";
					co++;
				}else if(ch == c){
					score += 10; 
				}
				
			}
			if(co == 0){
				System.out.println("您当前得分是"+score);
			}else{
				System.out.println("您当前得分是"+score+",还有"+str+"没做");
			}
				return co;
		
		
	}

	@Override
	public void displayItem(int no,Item[] items) {
		// 方法显示参数no指定的考题内容。 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		char[] answer = new char[items.length];
		while(no<1 || no >items.length){
			System.out.println("题目不存在");
			System.out.println("请重新输入");
			
			no = sc.nextInt();
			
		}
			ItemService itemser = new ItemService();
		    Item item1 = itemser.getItems(no,items);
		    System.out.println(item1.getQuestion());
		    String[] s = item1.getOptions(); 
		    for(String e : s){
		    	System.out.println(e);
		    }
		    if(answer[no-1] != ' '){
		    	System.out.println(answer[no-1]);
		    }
//		
	}

	@Override
	public AnswerAndScorce addList(int scare,char[] answer,Item[] items) {
		//添加进入列表
		
		AnswerAndScorce ass = new AnswerAndScorce(scare, answer, items);
//		AAS.add(ass);
		return ass;
	
	}
//	@Override
	public void save(Person per) {
		// // 保存
		File file = new File("LINGSHIDANGAN"+File.separator+per.getName()+"bofy.ser");
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(AAS);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//文件是否存在
	public boolean isFileExits(Person per){
		File file = new File("LINGSHIDANGAN"+File.separator+per.getName()+"bofy.ser");
		if(file.exists()){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void load(Person per) {
		// TODO Auto-generated method stub
		File file = new File("LINGSHIDANGAN"+File.separator+per.getName()+"bofy.ser");
		
		ObjectInputStream in = null;
		try {
			 in = new ObjectInputStream(new FileInputStream(file));
			 List<AnswerAndScorce> list;
			try {
				list = (List<AnswerAndScorce>) in.readObject();
				for(AnswerAndScorce s :list){
					 AAS.add(s);
				 }
				
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
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	// 获地成绩
	public int getScore(char[] answer,Item[] items) {
		//遍历
		ItemService it = new  ItemService();
		int m = items.length;
		int[] co = new int[3];
		String[] str = new String[3];
		for(int i=0;i<3;i++){
			str[i] = "";
			co[i] = 0;
		}
		int score = 0;
		for(int i=1;i<=m;i++){
			char c = it.getItems(i,items).getAnswer();
			char ch = answer[i-1];
			System.out.println("第"+i+"题正确的答案是"+c+",你的答案是"+ch);
			if(ch == c){
				co[0]++;
				str[0] += "第"+i+"题、";
			}else if(ch==' '){
				co[1]++;
				str[1] += "第"+i+"题、";
			}else {
				co[2]++;
				str[2] += "第"+i+"题、";
			}
		}
		System.out.println();
		System.out.println("恭喜你，你答对了"+str[0]+"总共"+co[0]+"道题");
		if(str[1] != ""&&str[2] != ""){
			System.out.println("答错了"+str[2]+"共"+co[2]+"道题，\n "+str[1]+"没有做,共"+co[1]+"道题");
		}else if(str[1] != ""&&str[2] == ""){
			System.out.println(str[1]+"没有做,共"+co[1]+"道题");
		}else if(str[1] == ""&&str[2] != ""){
			System.out.println("答错了"+str[2]+"共"+co[2]+"道题,已经全部做完了");
		}		
		score = co[0]*10;
		System.out.println("总得分"+score);
		return score;
	}

	//查询历史成绩
	public AnswerAndScorce referHistoryMsg(Person per,int no){
		Scanner in = new Scanner(System.in);
		RocecardService rcs = new RocecardService();
		AnswerAndScorce ass = null;
		if(isFileExits(per)||rcs.isFileExit(per)){
			boolean bool = false;
			if(rcs.isFileExit(per)){
				rcs.load(per);
				Rocecard rd = rcs.ROVE.get(per.getUsername());
				List<AnswerAndScorce> sd = (List<AnswerAndScorce>)rd.getAss() ;
				for(Iterator<AnswerAndScorce> e = sd.iterator();e.hasNext();){
					AAS.add(e.next());
				}
				int n = AAS.size();
				while(no<=0 || no>n){
					System.out.println("题目不存在，请重新输入，记得要比"+(n+1)+"小");
					no = in.nextInt();
				}
				
				ass = AAS.get(no - 1);
				save(per);
				
			}
		}else if(isFileExits(per)){
			load(per);
			
//			for(Iterator<AnswerAndScorce> it = AAS.iterator();it.hasNext();){
//				System.out.println(it.next());
//			}
			int n = AAS.size();
			while(no<=0 || no>n){
				System.out.println("题目不存在，请重新输入，记得要比"+(n+1)+"小");
				no = in.nextInt();
			}
			
			ass = AAS.get(no - 1);
			
		}else{
			System.out.println("暂时没有您的记录");
		}
		return ass;
	}
	//重载
	public void referHistoryMsg(Person per){
		RocecardService rcs = new RocecardService();
		if(isFileExits(per)||rcs.isFileExit(per)){
			if(isFileExits(per)){
				load(per);
//				for(Iterator<AnswerAndScorce> it = AAS.iterator();it.hasNext();){
//					System.out.println(it.next());
//				}	
				
			}else if(rcs.isFileExit(per)){
				rcs.load(per);
				Rocecard rd = rcs.ROVE.get(per.getUsername());
				List<AnswerAndScorce> sd = (List<AnswerAndScorce>)rd.getAss() ;
				for(Iterator<AnswerAndScorce> e = sd.iterator();e.hasNext();){
					AAS.add(e.next());
				}
				
				save(per);
				
			}
		}else{
			System.out.println("暂时没有您的记录");
		}
	}
}
