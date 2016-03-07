package wangzezhen.exam.view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import wangzezhen.exam.domain.AnswerAndScorce;
import wangzezhen.exam.domain.Item;
import wangzezhen.exam.domain.Person;
import wangzezhen.exam.domain.Rocecard;
import wangzezhen.exam.service.AASService;
import wangzezhen.exam.service.ItemService;
import wangzezhen.exam.service.RocecardService;
import wangzezhen.exam.service.opinionHandleService;

/**
 *  应用程序的主控类，负责与用户交互，完 成考试及成绩查询功能 
 * @author 王泽振
 *2016年3月3日 上午11:21:53
 */
public class ExamView {
opinionHandleService ohp = new opinionHandleService();

//	
	//主菜单
	private void mainMeau(){
		System.out.println("=====================单机考试=========================");
		System.out.println();
		System.out.println();
		System.out.println("                    1    进入考试                                                          ");
		System.out.println("                    2  查询历史成绩                                                    ");
		System.out.println("                    3   再次考试                                                          ");
		System.out.println("                    4   退出系统                                                          ");
		System.out.println();
		System.out.println();
		System.out.println("=================================================== ");
	
	}
//	
	
	//查询历史成绩信息界面
	private int refHistoryMsg(){
		
		int i = 0;
		boolean flag = false;

		System.out.println("\t\t     1\t查询上次记录");
		System.out.println("\t\t     2\t查询某次记录");
		System.out.println("\t\t     3\t查询最高记录");
		System.out.println("\t\t     4\t查询所有记录");
		System.out.println("输入你要进行的操作");
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
	
	//选择题型界面
	private int dispalyChoice(){
		int i = 0;
		System.out.println("=====================选择题型=======================");
		System.out.println();
		System.out.println();
		System.out.println("                1            计算机类                                                ");
		System.out.println("                2            语文类                                                           ");
		System.out.println("                3            数学类                                                           ");
		System.out.println();
		System.out.println();
		System.out.println("===================输入类型（1-3）进入考试======================");
		boolean flag = false;
		
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
	
				default:
					System.out.println("请输入正确操作");
					flag = false;
					break;
			}
		}
		return i;
	
	}
	//帮助信息
	private void dispalyInfo(){
		System.out.println("=====================帮助页面=======================");
		System.out.println();
		System.out.println();
		System.out.println("               1   A——D 为答案选项                                                ");
		System.out.println("               2   N 为下一页                                                           ");
		System.out.println("               3   P 为上一页                                                           ");
		System.out.println("               4   R 为查询当前未作的题号并查询当前成绩           ");
		System.out.println("               5   M 为修改                                                                ");
		System.out.println("               6   L 为结束当前考试，不计成绩，重新开始            ");
		System.out.println("               7   F 为退出系统                                                        ");
		System.out.println();
		System.out.println();
		System.out.println("===================输入n进入考试======================");
		while(true){
			char ch = ohp.getUserAction();
			if(ch == 'N'){
				break;
			}else{
				System.out.println("请输入正确操作");
			}
		}
	}

//	//主要操作
	@SuppressWarnings("static-access")
	public void  mainOperate(Person per){
		AASService asce =new AASService();
		AnswerAndScorce aas = null;
		ItemService it = new ItemService();
		RocecardService rcs = new RocecardService();
		boolean bool = false;
		boolean bo = false;
		int n = 0;
		int io = 3;
		int[] ns = new int[3];
//		for(int i=0;i<3;i++){
//			ns[i]=0;
//		}
		while(!bo){
			mainMeau();//主菜单
			char ch = ohp.getUserAction();
			switch (ch) {
			case '1':
				dispalyInfo();
				while(!bool&& io>0&&aas == null){
					ns[n] = dispalyChoice();
					Item[] items = it.operationTitleTest(ns[n]-1);//获得题
					io--;
					aas = asce.testExam(items,io);//答题
				}
					asce.AAS.add(aas);
					asce.save(per);
				
				break;
	
			case '2':
				//2  查询历史成绩
				int key = refHistoryMsg();
				switch (key) {
					case 1:
						asce.referHistoryMsg(per);
						int w = asce.AAS.size();
						if(w>=0){
							System.out.println(asce.AAS.get(w-1));
						}
						break;
					case 2:
						System.out.println("输入你要查询信息的次数");
						Scanner in = new Scanner(System.in);
						int mn = in.nextInt();
						AnswerAndScorce ssa = asce.referHistoryMsg(per, mn);
						System.out.println(ssa);
						break;
					case 3:
						asce.referHistoryMsg(per);
						int smax = 0;
						int si = asce.AAS.size();
						int[] scare = new int[si];
						for(int i =0;i<si;i++){
							scare[i] = asce.AAS.get(i).getScore();
						}
						for(int i =0;i<si;i++){
							if(scare[i] > scare[smax]){
								smax = i;
							}
						}
						System.out.println(asce.AAS.get(smax));
						break;
					case 4:
						asce.referHistoryMsg(per);
						for(Iterator<AnswerAndScorce> it1 = asce.AAS.iterator();it1.hasNext();){
							System.out.println(it1.next());
						}	
						break;
					default:
						break;
				}
			
				
//				
				
				break;
			case '3':
				boolean lean = false;
				aas = null;
				n++;
				io = 3;
				bool = false;
				if(n<3){
					dispalyInfo();
				
					while(!bool&& io>0&&aas == null&&n<3){
						
						ns[n] = dispalyChoice();
						for(int i=0;i<n;i++){
							if(ns[i] == ns[n]){
								lean = true;
								break;
							}
						}
						while(lean){
							System.out.println("你已经选过该类的题了");
							ns[n] = dispalyChoice();
							for(int i=0;i<n;i++){
								if(ns[i] == ns[n]){
									lean = true;
									break;
								}else{
									lean =false;
								}
							}
						
						}
						Item[] items = it.operationTitleTest(ns[n]-1);//获得题
						io--;
						
						aas = asce.testExam(items,io);//答题
						
					}
						asce.AAS.add(aas);
						asce.save(per);
				}else{
					System.out.println("你已经做完了所有类型的题目");
				}

				break;
			case '4':
				System.out.println("再见，欢迎再次使用");
				asce.save(per);
				rcs.createList(per, asce.AAS);
				rcs.save(per);
				
				bo = true;
//				System.exit(0);
				break;
				
			default:
				System.out.println("输入错误");
				break;
			}
		}
	}

	
	
}
