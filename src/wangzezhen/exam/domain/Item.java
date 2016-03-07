package wangzezhen.exam.domain;

import java.io.Serializable;

/**
 * 考试题目类，每个Item对象对应一道题目 
 * 封装Item对象
 * @author 王泽振
 *2016年3月3日 上午11:22:34
 */
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8865990071803893536L;
	//问题
	private String question;
	//选项
    private String[] options;
	//答案
    private char answer;
    /*
     * 构造
     */
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(String question, String[] options, char answer) {
		super();
		this.question = question;
		this.options = options;
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public char getAnswer() {
		return answer;
	}
	public void setAnswer(char answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return  question + "\n"
				+options[0] + "\n"
						+options[1] +"\n"
								+options[2] +"\n"
										+options[3] +"\n标准答案" + answer+"\n" ;
	}
	
	
}
