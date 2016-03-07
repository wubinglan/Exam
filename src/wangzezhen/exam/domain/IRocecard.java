package wangzezhen.exam.domain;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 档案接口
 * @author 王泽振
 *2016年3月5日 下午2:21:28
 */
public interface IRocecard {

	//档案列表
	Map<String, Rocecard> ROVE = new HashMap
							<String, Rocecard>();
//	File file = new File("DANGAN"+File.separator+""+".ser");
	String STR = new String("DANGAN"+File.separator);
	File FE = new File(STR+"ALL.ser");
	//生成列表
	void createList(Person person,List<AnswerAndScorce> aas);
	//保存档案
	void save(Person person);
	//读取档案
	void load(Person person);
	//判读是否存在
	boolean isFileExit(Person per);
}
