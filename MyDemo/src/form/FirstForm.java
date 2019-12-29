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
	
	JPanel panelMain = new JPanel(new FlowLayout()); //��ʾList�б�
	JPanel panelBtn = new JPanel(new FlowLayout()); //��ʾ��ť�б�
		
	JList listMain ;
	
	JButton btnNew = new JButton("����");
	JButton btnUpdate = new JButton("�޸�");
	JButton btnDelete = new JButton("ɾ��");
	
	@SuppressWarnings("unchecked")
	public void ShowForm() {
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container framContainer = frame.getContentPane();				
		framContainer.add(BorderLayout.CENTER,panelMain);
		framContainer.add(BorderLayout.SOUTH,panelBtn);
		
		//�� JList ��һЩ��ʾ����
		ArrayList<Student> list = new ArrayList<Student>();	
		SerializeHelper ser = new  SerializeHelper();
		String filePath = "studentList.ser";
		File file = new File(filePath);
		//����ļ����ڣ�����ļ��ж�ȡ���ݡ��������򴴽������µģ����浽�ļ�
		if(file.exists()) {
			try {				
				list = (ArrayList<Student>)ser.DeSerializeFromFile(filePath);
			}catch(ClassCastException ex) {
				JOptionPane.showMessageDialog(frame, "����ת���쳣���޷����ļ��е�����תΪָ�������ͣ����ļ�ɾ�ˣ����ｫ���´���");					
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
		listMain = new JList(list.toArray());//��д��Student��toString���������Կ��԰��Զ���ĸ�ʽ��ʾ����				
		panelMain.add(listMain);
		
		
		//���button
		panelBtn.add(btnNew);
		panelBtn.add(btnUpdate);
		panelBtn.add(btnDelete);
		
		//����ť����¼�
		//btnNew�¼�
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				//��MainForm��������һ��ģ̬����
				MainForm frm = new MainForm();
				frm.ShowForm(frame,btnNew);
				
				//TODO:����������ݣ����ļ�������
				
				
			}		
		});
		//btnUpdate�¼�
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
							
			}	
		});
		//btnDelete�¼�
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
							
			}	
		});

		//��ʾ������
		frame.setVisible(true);
	}
}
