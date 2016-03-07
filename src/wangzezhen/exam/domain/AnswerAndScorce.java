package wangzezhen.exam.domain;

import java.io.Serializable;

/**
 * 成绩和用户答案类
 * @author 王泽振
 *2016年3月5日 下午2:20:05
 */
public class AnswerAndScorce implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4615775244411508077L;
	private  int score;
	private char[] answer;
	private Item[] items;
	

	/**
	 * 构造
	 */
	public AnswerAndScorce() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerAndScorce(int score, char[] answer, Item[] items) {
		super();
		this.score = score;
		this.answer = answer;
		this.items = items;
	}

	/**
	 * 封装
	 * @return
	 */
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public char[] getAnswer() {
		return answer;
	}

	public void setAnswer(char[] answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		String str = "";
		int i =0;
		for(Item s :items){
			
			str+=(s+"你的答案"+answer[i]+"\n"+"\n");
			i++;
		}
		
		str += "成绩：" + score+"\n===================================================\n";
		
		return str;
				
	}
	
	
}
