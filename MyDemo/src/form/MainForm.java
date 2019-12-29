package form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import DAL.SerializeHelper;
import model.OperationType;
import model.Student;

public class MainForm {

	JDialog dialog = new JDialog();
	
	//panelMain上排列 label和Text
	JPanel  panelMain = new JPanel (); 
	JLabel labelName = new JLabel("姓名");
	JLabel labelID = new JLabel("学号");
	JLabel labelKM = new JLabel("科目");
	JTextField  textName = new JTextField (8);
	JTextField  textID = new JTextField (8);
	JTextField  textKM = new JTextField (8);
	
	//panelBtn上排列按钮
	JPanel panelBtn = new JPanel(new FlowLayout()); // 
	JButton btnOK = new JButton("确定");
	JButton btnCancel = new JButton("返回");
	
	public Student student;//
	public OperationType operation; //自定义的枚举，新增，修改，删除
	
	//新增不需要传对象进来
	public MainForm() {		
		this.operation = OperationType.New;
	}
	
	public MainForm(Student student,OperationType operation) {
		this.student = student;
		this.operation = operation;
	}
	
	public void ShowForm(Frame owner, Component parentComponent) {

		// 创建一个模态对话框
		dialog = new JDialog(owner, "提示", true);
		//设置主窗体的长宽
		dialog.setSize(400, 200);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);
		
		Container dialogContainer = dialog.getContentPane();
		
		//给主窗体添加Panel，并指定位置
		dialogContainer.add(BorderLayout.CENTER,panelMain);
		dialogContainer.add(BorderLayout.SOUTH,panelBtn);
			
		//panelMain添加组件
		panelMain.add(labelName);
		panelMain.add(textName);		
		panelMain.add(labelID);
		panelMain.add(textID);
		panelMain.add(labelKM);
		panelMain.add(textKM);
		//panelBtn添加组件
		panelBtn.add(btnOK);
		panelBtn.add(btnCancel);
				
		//确定
		btnOK.addActionListener(new BtnOkListener());
		//关闭
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();			
			}	
		});
		
		//显示主窗体
		dialog.setVisible(true);
	}
		
	public String filePath="studentList.ser";	
	//内部类
	//事件：实现btnOk事件
	class BtnOkListener implements ActionListener{

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
				
			//从文件中读取上次保存的数据			
			SerializeHelper ser = new  SerializeHelper();
			List<Student> list = new ArrayList<Student>();
			File file = new File(filePath);
			//如果文件存在，则从文件中读取内容
			if(file.exists()) {
				try {
					
					list = (List<Student>)ser.DeSerializeFromFile(filePath);
				}catch(ClassCastException ex) {
					JOptionPane.showMessageDialog(dialog, "类型转换异常！无法将文件中的内容转为指定的类型！把文件删了，这里将重新创建");					
					list = new ArrayList<Student>();
				}				
			}
						
			//1.新增
			if(operation == OperationType.New ) {				
				Student stu = new Student();			
				stu.ID = textID.getText();	
				stu.Name = textName.getText();
				list.add(stu);
			}
			//2.修改
			else if(operation == OperationType.Update) {
				//找到指定的记录，更新并保存
				Student theOne = null;
				for(Student item: list) {
					if(item.ID == student.ID) {
						theOne = item;
						break;
					}				
				}
				if(theOne!=null) {
					theOne.ID = textID.getText();
					theOne.Name = textName.getText();
				}else {
					JOptionPane.showMessageDialog(dialog, "该记录不存在，无法修改！");
				}	
			}
			//3.删除
			else if(operation == OperationType.Delete) {
				Student theOne = null;
				for(Student item: list) {
					if(item.ID == student.ID) {
						theOne = item;
						break;
					}				
				}
				if(theOne!=null) {
					list.remove(theOne);
				}				
			}			
			
			//将List内容保存到文件									
			boolean result = ser.SaveToFile(list, filePath);			
			if(result) 
			{
				//提示保存成功		
				JOptionPane.showMessageDialog(dialog, "保存成功"); 
			}			
		}
		
	}
}
