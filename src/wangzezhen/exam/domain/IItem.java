package wangzezhen.exam.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 题库接口类
 * @author 王泽振
 *2016年3月5日 下午2:19:47
 */
public interface IItem {
	
	//题目列表
	List<File> ITEMS = new ArrayList<File>();
	File[] ITSS = {new File("EXAMITEMS"),new File("YUWEN"),new File("SHUXUE")};;
//	private List<String> readTextFile(String filename) ;
		// 该方法可读取参数指定的文本文件内容 （不使用包 装），并打印输出到屏幕上。
	
//	//获取Item对象
//	 Item getItems(int no) ;
//
//	//将数组中的内 容以对象形式写入到文件中保存
//	 void saveAnswer(char[] answer,String str);
//	
//	//方法重写
//	 void saveAnswer(int score,String str) ;
//	//生成分数并保存
//	int getScore(char[] answer);
//	//读取文件
//	void load(String string,String str2);
//	
//	//读取个人成绩
//	int loadScoer(String string);
//	
//	//读取个人答案选项
//	char[] loadAnsewer(String string);
//	//比较最大成绩与最后成绩
//	void compareScore(char[] answer_last,int score_last);
//	
	
	//历题库文件夹下的所有题库文件
//	boolean getAllItems(int no);
//	//随机获得一套题  遍历题库文件夹下的所有题库文件 并从中获得一套题
//	File getItemss();
//	//获得题中的所有题并存放在一个数组中
//	Item[] getItem(File file);
//	//随机打乱题顺序
//	Item[] getNewItem(Item[] items);
//	//随机打乱选项 并将标准答案随之变化
//	Item getNewItemAnswer(Item item);
	//考试出题操作
	Item[] operationTitleTest(int no);
}
