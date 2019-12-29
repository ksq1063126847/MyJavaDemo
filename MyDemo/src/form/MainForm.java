package form;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import DAL.SerializeHelper;
import model.Student;

public class MainForm {

	JFrame frame = new JFrame();
	
	//panelMain上排列 label和Text
	JPanel panelMain = new JPanel(new FlowLayout()); 
	JLabel labelName = new JLabel("姓名");
	JLabel labelID = new JLabel("学号");
	JTextField  textName = new JTextField (8);
	JTextField  textID = new JTextField (8);
	
	//panelBtn上排列按钮
	JPanel panelBtn = new JPanel(new FlowLayout()); // 
	JButton btnOK = new JButton("确定");
	JButton btnCancel = new JButton("返回");
	
	public void ShowForm() {
		
		//设置主窗体的长宽
		frame.setSize(600,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container framContainer = frame.getContentPane();
		
		//panelMain添加组件
		panelMain.add(labelName);
		panelMain.add(textName);
		panelMain.add(labelID);
		panelMain.add(textID);
				
		//panelBtn添加组件
		panelBtn.add(btnOK);
		panelBtn.add(btnCancel);
		
		//给主窗体添加Panel，并指定位置
		framContainer.add(BorderLayout.CENTER,panelMain);
		framContainer.add(BorderLayout.SOUTH,panelBtn);
		
		//给按钮添加事件
		btnOK.addActionListener(new BtnOkListener());
		btnCancel.addActionListener(new BtnCancelListener());
		
		//显示主窗体
		frame.setVisible(true);
	}
		
	private String filePath="studentList.ser";	
	//内部类
	//事件：实现btnOk事件
	class BtnOkListener implements ActionListener{

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
				
			Student stu = new Student();
			stu.Name = textName.getText();
			stu.ID = textName.getText();
			List<Student> list = new ArrayList<Student>();
			list.add(stu);
			//将List<Student>序列化之后保存到文件
			SerializeHelper ser = new  SerializeHelper();						
			boolean result = ser.SaveToFile(list, filePath);			
			if(result) 
			{
				//提示保存成功		
				JOptionPane.showMessageDialog(frame, "保存成功"); 
			}
			
			//此处演示从文件读取数据（反序列化对象）	;			
			List<Student> newList = (List<Student>)ser.DeSerializeFromFile(filePath); 
			for(Student item : newList) {
				System.out.println(item.Name); 
			}  
		}
		
	}
	//事件：实现btnCancel事件
	class BtnCancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);;				
		}		
	}
}
