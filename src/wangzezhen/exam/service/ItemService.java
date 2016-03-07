package wangzezhen.exam.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import wangzezhen.exam.domain.IItem;
import wangzezhen.exam.domain.Item;

/**
 * 业务类
 * 封装了与考试题目访问相关的业务方法 
 * @author 王泽振
 *2016年3月3日 上午11:21:41
 */
public class ItemService implements Serializable,IItem{


/**
	 * 
	 */
	private static final long serialVersionUID = 3139047797820804005L;
	//	
	private  final  int SUM = 6;
//	
//

	private List<String> readTextFile(File file) {
		// 该方法可读取参数指定的文本文件内容 （不使用包 装），并打印输出到屏幕上。
//		File file = new File(filename);
		List<String> list = new ArrayList<String>();
		Reader read = null;
		BufferedReader br = null;
		try {
			read = new FileReader(file);
			br = new BufferedReader(read);
			String str = null;
			try {
				
				if(file.exists()){
					str = br.readLine();
					if(str != null){
						while (str != null) {
							
							if(!str.trim().equals("")){
								list.add(str);
							
							}
							
							str = br.readLine();
						}
					}else{
						System.out.println("文件为空");
					}
				}else{
					System.out.println("文件不存在");
				}

				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
		
	}

	 
	private boolean getAllItems(int no) {
		boolean flag = false;
		// 历题库文件夹下的所有题库文件
//		ITS.isDirectory();//没有用判断是否是文件夹
		File[] files = ITSS[no].listFiles();//得到给目录下的文件等
		if(files.length != 0){
			for(File f : files){
				ITEMS.add(f);
			}
			flag = true;
		}		
		return flag;
	}


	 
	private File getItemss() {
		// 随机获得一套题  遍历题库文件夹下的所有题库文件 并从中获得一套题
		int i = ITEMS.size();
		Random ran = new Random();
		int n = ran.nextInt(i)+1;
		File file = ITEMS.get(n);
		return file;
	}




	 
	private Item[] getItem(File file) {
		
		// 获得题中的所有题并存放在一个数组中
		List<String> list = readTextFile(file);
		int size = list.size()/SUM;
		Item[] items = new Item[size];
		
		for(int i=0;i<size;i++){
			String question = list.get(i * SUM );
			String[] options = {
					list.get(i * SUM + 1),
					list.get(i * SUM + 2),
					list.get(i * SUM + 3),
					list.get(i * SUM + 4),
			};
			char answer = list.get(i * SUM +5).charAt(0);
			items[i] = new Item(question,options,answer);			
		}
		return items;
	}

	//获取Item对象
	public Item getItems(int no,Item[] items) {
		
		 if (no <= 0 || no >items.length) {
	            return null;
	        }

	        return items[no - 1];
	}


	 
	private Item[] getNewItem(Item[] items) {
		Random ran = new Random();
		int n = items.length;
		String[] st = new String[n]; 
		String[] sr = new String[n];
		for(int i=0;i<n;i++){
			st[i] = items[i].getQuestion().substring(0,4);
			sr[i] = items[i].getQuestion().substring(4);
			items[i].setQuestion(sr[i]);
		}
		
		
		Item itemtotal = null;
		// 随机打乱题顺序
		for(int i = 0;i < n;i++){
			int m = ran.nextInt(n);
			itemtotal = items[i];
			items[i] = items[m];
			items[m] = itemtotal;
		}
		for(int i=0;i<n;i++){
			sr[i] = items[i].getQuestion().substring(4);
			items[i].setQuestion(st[i]+sr[i]);
		}
		return items;
	}

	 
	private Item getNewItemAnswer(Item item) {
		// 随机打乱选项 并将标准答案随之变化
		int oldI = 0;//表示原来标准答案的下标
		char c = item.getAnswer();
		Random ran = new  Random();
		char[] ch = new char[4];
		String[] str = item.getOptions();
		String[] st = new String[4];
		//选号（A-D）
		for(int i=0;i<4;i++){
			ch[i] = str[i].charAt(0);
		}
		//截取选项 去掉选号（A-D）
		for(int i=0;i<4;i++){
			st[i] = str[i].substring(3);
		}
		//比对选项和答案
		for(int i=0;i<4;i++){
			if(ch[i] == c){
				oldI = i;
				break;
			}
		}
		//打乱顺序
		for(int i=0;i<4;i++){
			int m =	ran.nextInt(4);
			if(oldI == i){
				oldI = m;
			}else if(oldI == m){
				oldI = i;
			}
			String temp = null;
			temp = st[i];
			st[i] = st[m];
			st[m] = temp;
			
		}
		for(int i=0;i<4;i++){
			str[i] = ch[i]+". " + st[i];
		}
		item.setOptions(str);
		item.setAnswer(ch[oldI]);
		return item;
	}
	
	//考试出题操作
	@Override
	public Item[] operationTitleTest(int no){
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
 			Item[] items = null;
			
			
			boolean flag = getAllItems(no);
			while(!flag){
				System.out.println("题库中还没有题，请去选择别的题型");
				 no = in.nextInt();
				flag = getAllItems(no);
			}
		
				File file = getItemss();
				items = getItem(file);
				//打乱题号顺序
				items = getNewItem(items);
				//打乱选项
				for(int i=0;i<items.length;i++){
					items[i] = getNewItemAnswer(items[i]);
				}
			
			return items;
			
		}
	
}
