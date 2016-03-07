package wangzezhen.exam.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 成绩和用户答案接口类
 * @author 王泽振
 *2016年3月5日 下午2:21:06
 */
public interface IAnswerAndScorce {

	List<AnswerAndScorce> AAS = 
				new ArrayList<AnswerAndScorce>();//存放每次的题，用户答案，成绩
	
	//获得答案
	AnswerAndScorce testExam(Item[] items,int io);
	//没做的题目
	int getNotTitle(Item[] items,char[] answer);
	//获得成绩
	int getScore(char[] answer,Item[] items);
	//方法显示参数no指定的考题内容。 
	void displayItem(int no,Item[] items);
	//添加列表
	AnswerAndScorce addList(int scare,char[] answer,Item[] items);	
	//读取
	void load(Person per);
	//保存
	void save(Person per);
}
