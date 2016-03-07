package wangzezhen.exam.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 档案
 * @author 王泽振
 *2016年3月5日 下午2:19:14
 */
public class Rocecard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313852380652750225L;
	private Person person;
	private List<AnswerAndScorce> ass;
	
	/**
	 * 构造
	 */
	public Rocecard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rocecard(Person person,List<AnswerAndScorce> ass) {
		super();
		this.person = person;
//		this.item = item;
		this.ass = ass;
	}

	/**
	 * 封装
	 * @return
	 */
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

//	public Item[] getItem() {
//		return item;
//	}
//
//	public void setItem(Item[] item) {
//		this.item = item;
//	}

	public List<AnswerAndScorce> getAss() {
		return ass;
	}

	public void setAss(List<AnswerAndScorce> ass) {
		this.ass = ass;
	}

	@Override
	public String toString() {
		return "用户：" + person +  ", ass=" + ass
				;
	}
	
	
}
