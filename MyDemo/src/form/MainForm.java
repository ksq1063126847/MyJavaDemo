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
	
	//panelMain������ label��Text
	JPanel  panelMain = new JPanel (); 
	JLabel labelName = new JLabel("����");
	JLabel labelID = new JLabel("ѧ��");
	JLabel labelKM = new JLabel("��Ŀ");
	JTextField  textName = new JTextField (8);
	JTextField  textID = new JTextField (8);
	JTextField  textKM = new JTextField (8);
	
	//panelBtn�����а�ť
	JPanel panelBtn = new JPanel(new FlowLayout()); // 
	JButton btnOK = new JButton("ȷ��");
	JButton btnCancel = new JButton("����");
	
	public Student student;//
	public OperationType operation; //�Զ����ö�٣��������޸ģ�ɾ��
	
	//��������Ҫ���������
	public MainForm() {		
		this.operation = OperationType.New;
	}
	
	public MainForm(Student student,OperationType operation) {
		this.student = student;
		this.operation = operation;
	}
	
	public void ShowForm(Frame owner, Component parentComponent) {

		// ����һ��ģ̬�Ի���
		dialog = new JDialog(owner, "��ʾ", true);
		//����������ĳ���
		dialog.setSize(400, 200);
        // ���öԻ����С���ɸı�
        dialog.setResizable(false);
        // ���öԻ��������ʾ��λ��
        dialog.setLocationRelativeTo(parentComponent);
		
		Container dialogContainer = dialog.getContentPane();
		
		//�����������Panel����ָ��λ��
		dialogContainer.add(BorderLayout.CENTER,panelMain);
		dialogContainer.add(BorderLayout.SOUTH,panelBtn);
			
		//panelMain������
		panelMain.add(labelName);
		panelMain.add(textName);		
		panelMain.add(labelID);
		panelMain.add(textID);
		panelMain.add(labelKM);
		panelMain.add(textKM);
		//panelBtn������
		panelBtn.add(btnOK);
		panelBtn.add(btnCancel);
				
		//ȷ��
		btnOK.addActionListener(new BtnOkListener());
		//�ر�
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();			
			}	
		});
		
		//��ʾ������
		dialog.setVisible(true);
	}
		
	public String filePath="studentList.ser";	
	//�ڲ���
	//�¼���ʵ��btnOk�¼�
	class BtnOkListener implements ActionListener{

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
				
			//���ļ��ж�ȡ�ϴα��������			
			SerializeHelper ser = new  SerializeHelper();
			List<Student> list = new ArrayList<Student>();
			File file = new File(filePath);
			//����ļ����ڣ�����ļ��ж�ȡ����
			if(file.exists()) {
				try {
					
					list = (List<Student>)ser.DeSerializeFromFile(filePath);
				}catch(ClassCastException ex) {
					JOptionPane.showMessageDialog(dialog, "����ת���쳣���޷����ļ��е�����תΪָ�������ͣ����ļ�ɾ�ˣ����ｫ���´���");					
					list = new ArrayList<Student>();
				}				
			}
						
			//1.����
			if(operation == OperationType.New ) {				
				Student stu = new Student();			
				stu.ID = textID.getText();	
				stu.Name = textName.getText();
				list.add(stu);
			}
			//2.�޸�
			else if(operation == OperationType.Update) {
				//�ҵ�ָ���ļ�¼�����²�����
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
					JOptionPane.showMessageDialog(dialog, "�ü�¼�����ڣ��޷��޸ģ�");
				}	
			}
			//3.ɾ��
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
			
			//��List���ݱ��浽�ļ�									
			boolean result = ser.SaveToFile(list, filePath);			
			if(result) 
			{
				//��ʾ����ɹ�		
				JOptionPane.showMessageDialog(dialog, "����ɹ�"); 
			}			
		}
		
	}
}
