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
	
	//panelMain������ label��Text
	JPanel panelMain = new JPanel(new FlowLayout()); 
	JLabel labelName = new JLabel("����");
	JLabel labelID = new JLabel("ѧ��");
	JTextField  textName = new JTextField (8);
	JTextField  textID = new JTextField (8);
	
	//panelBtn�����а�ť
	JPanel panelBtn = new JPanel(new FlowLayout()); // 
	JButton btnOK = new JButton("ȷ��");
	JButton btnCancel = new JButton("����");
	
	public void ShowForm() {
		
		//����������ĳ���
		frame.setSize(600,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container framContainer = frame.getContentPane();
		
		//panelMain�������
		panelMain.add(labelName);
		panelMain.add(textName);
		panelMain.add(labelID);
		panelMain.add(textID);
				
		//panelBtn�������
		panelBtn.add(btnOK);
		panelBtn.add(btnCancel);
		
		//������������Panel����ָ��λ��
		framContainer.add(BorderLayout.CENTER,panelMain);
		framContainer.add(BorderLayout.SOUTH,panelBtn);
		
		//����ť�����¼�
		btnOK.addActionListener(new BtnOkListener());
		btnCancel.addActionListener(new BtnCancelListener());
		
		//��ʾ������
		frame.setVisible(true);
	}
		
	private String filePath="studentList.ser";	
	//�ڲ���
	//�¼���ʵ��btnOk�¼�
	class BtnOkListener implements ActionListener{

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
				
			Student stu = new Student();
			stu.Name = textName.getText();
			stu.ID = textName.getText();
			List<Student> list = new ArrayList<Student>();
			list.add(stu);
			//��List<Student>���л�֮�󱣴浽�ļ�
			SerializeHelper ser = new  SerializeHelper();						
			boolean result = ser.SaveToFile(list, filePath);			
			if(result) 
			{
				//��ʾ����ɹ�		
				JOptionPane.showMessageDialog(frame, "����ɹ�"); 
			}
			
			//�˴���ʾ���ļ���ȡ���ݣ������л�����	;			
			List<Student> newList = (List<Student>)ser.DeSerializeFromFile(filePath); 
			for(Student item : newList) {
				System.out.println(item.Name); 
			}  
		}
		
	}
	//�¼���ʵ��btnCancel�¼�
	class BtnCancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);;				
		}		
	}
}