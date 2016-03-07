package wangzezhen.exam.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import wangzezhen.exam.domain.AnswerAndScorce;
import wangzezhen.exam.domain.IRocecard;
import wangzezhen.exam.domain.Person;
import wangzezhen.exam.domain.Rocecard;

/**
 * 档案业务类
 * @author 王泽振
 *2016年3月6日 下午5:55:47
 */
public class RocecardService implements Serializable,IRocecard{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4503220666513623478L;
	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		File file = new File(STR+person.getUsername()+".ser");
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ROVE);
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

	public void saveAll(){
		File file = new File(STR+"ALL.ser");
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ROVE);
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
	
	@SuppressWarnings("unchecked")
	public void loadAll(){
		File file = new File(STR+"ALL.ser");
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			try {
				ROVE.putAll((Map<String, Rocecard>) in.readObject());
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
	@SuppressWarnings("unchecked")
	@Override
	public void load(Person person) {
		// TODO Auto-generated method stub
		File file = new File(STR+person.getUsername()+".ser");
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			try {
				ROVE.putAll((Map<String, Rocecard>) in.readObject());
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

	@Override
	public void createList(Person person, List<AnswerAndScorce> aas) {
		
		Rocecard roce = new Rocecard(person,aas);
		// 生成列表
		ROVE.put(person.getUsername(), roce);
	}

	@Override
	public boolean isFileExit(Person per) {
		// TODO Auto-generated method stub
		File fi = new File("DANGAN"+File.separator+per.getUsername()+".ser");
		
		if(fi.exists()){
			return true;
			
		}
		return false;
		
	}
	public boolean isAllFileExit(){
		if(FE.exists()){
			return true;
		}
		return false;
		
	};
}
