package form;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAL.SerializeHelper;
import form.MainForm.BtnCancelListener;
import form.MainForm.BtnOkListener;
import model.Student;

public class FirstForm {

	JFrame frame = new JFrame();
	
	JPanel panelMain = new JPanel(new FlowLayout()); //显示List列表
	JPanel panelBtn = new JPanel(new FlowLayout()); //显示按钮列表
		
	JList listMain ;
	
	JButton btnNew = new JButton("新增");
	JButton btnUpdate = new JButton("修改");
	JButton btnDelete = new JButton("删除");
	
	@SuppressWarnings("unchecked")
	public void ShowForm() {
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container framContainer = frame.getContentPane();				
		framContainer.add(BorderLayout.CENTER,panelMain);
		framContainer.add(BorderLayout.SOUTH,panelBtn);
		
		//给 JList 造一些显示数据
		ArrayList<Student> list = new ArrayList<Student>();	
		SerializeHelper ser = new  SerializeHelper();
		String filePath = "studentList.ser";
		File file = new File(filePath);
		//如果文件存在，则从文件中读取内容。不存在则创建两条新的，保存到文件
		if(file.exists()) {
			try {				
				list = (ArrayList<Student>)ser.DeSerializeFromFile(filePath);
			}catch(ClassCastException ex) {
				JOptionPane.showMessageDialog(frame, "类型转换异常！无法将文件中的内容转为指定的类型！把文件删了，这里将重新创建");					
				list = new ArrayList<Student>();
			}				
		}else {		
			Student one = new Student();
			one.ID="1";
			one.Name="ksq";
			Student two = new Student();
			two.ID="2";
			two.Name="khq";
			list.add(one);
			list.add(two);	
			ser.SaveToFile(list, filePath);			
		}
		listMain = new JList(list.toArray());//重写了Student的toString方法，所以可以按自定义的格式显示数据				
		panelMain.add(listMain);
		
		
		//添加button
		panelBtn.add(btnNew);
		panelBtn.add(btnUpdate);
		panelBtn.add(btnDelete);
		
		//给按钮添加事件
		//btnNew事件
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				//在MainForm创建的是一个模态窗口
				MainForm frm = new MainForm();
				frm.ShowForm(frame,btnNew);
				
				//TODO:这里更新数据，从文件读数据
				
				
			}		
		});
		//btnUpdate事件
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
							
			}	
		});
		//btnDelete事件
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
							
			}	
		});

		//显示主窗体
		frame.setVisible(true);
	}
}
