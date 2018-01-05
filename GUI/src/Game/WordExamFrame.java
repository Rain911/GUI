package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class WordExamFrame extends JFrame 
                           implements ActionListener
{
	//����һ����ź�ѡ�ַ�������
	final char[] choiceChar={'q','w','e','r','t','y','u','i','o','p','[',']','a','s','d','f','g','h','j','k','l'
			,';','z','x','c','v','b','n','m',',','.','/','Q','W','E','R','T','Y','U','I','O','P',
			'{','}','A','S','D','F','G','H','J','K','L',':','"','Z','X','C','V','B','N','M','<','>',
			'?','|','!','@','#','$','%','^','&','(',')','_','-','+','=','`','~','*'};
	//�����¼�û��÷ֵı���
	int score =0;
	//��������ϳ��ֵ�Ԫ��
	JButton btnBegin=new JButton("��ʼ");
	JButton btnClose=new JButton("�ر�");
	JLabel lblScore=new JLabel("�÷֣�");
	
	JLabel lblWord=new JLabel("�ȴ��������ַ�...");
	
	JLabel lblTime=new JLabel("����ʱ��");
	JTextField tfdWord=new JTextField(20);
	
	//��������Init��ʼ������
	public void Init()
	{
		setSize(400,300);
		setTitle("���ֲ��Գ��򣬼�����Ƿ�Ϊ���ˣ�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*****������6������Ԫ�ؼ��뵽��Ļ��*****/
		
		//1.�������崰�ڵĲ��ַ�ʽ--�߿򲼾�
		setLayout(new BorderLayout());
		
		//2.���Ӷ���Ԫ��
		//2.1����һ����������JPanel
		JPanel pTop=new JPanel();
		pTop.setBackground(Color.yellow);
		pTop.add(btnBegin);
		pTop.add(btnClose);
		pTop.add(lblScore);
		//2.2��pTop�ŵ���Ļ�Ķ���
        add(pTop,BorderLayout.NORTH);	
        
        //3.�����м�Ԫ��
        add(lblWord,BorderLayout.CENTER);
        
        //4.���ӵײ�Ԫ��
        JPanel pBottom =new JPanel();
        pBottom.setBackground(Color.green);
        pBottom.add(lblTime);
        pBottom.add(tfdWord);
        add(pBottom,BorderLayout.SOUTH);
        
        lblWord.setFont(new Font("����",1,50));
        lblWord.setHorizontalAlignment(JLabel.CENTER);
	}
	//���幹�췽��
	public WordExamFrame()
	{
		//1.����Init���н���ĳ�ʼ��
		Init();
		
		//2.���ö�ʱ��ļ���
		//2.1�������Ļس��¼����м���
		tfdWord.addActionListener(this);
		
		//���������������ַ�������
		TaskOfCreateWord t1=new TaskOfCreateWord();
		t1.start();
		
		//��������������ʱ�߳�
		new TaskOfCountTime().start();
	}
	
	//�����������ַ����߳�������--�ڲ���
	class TaskOfCreateWord extends Thread
	{   
		@Override
		public void run(){
			Random rd =new Random();
			while(totalTime>0)
			{	
			//�������һ���ַ�
				int index =rd.nextInt(choiceChar.length);
			String x=""+ choiceChar[index];
			//��ʾ����Ļ����
			lblWord.setText(x);
			
			//�������߼�ͣ�ټ���
			try {
				sleep(1000*2);
			} catch (Exception e) { 
				e.printStackTrace();
			}
			}//end while
			
			
		}//end run
	}//end class
	
	//��дmain��Ϊ��������
	public static void main(String[] args) {
		WordExamFrame f1 =new WordExamFrame();
	                  f1.setVisible(true);	
	}
//�û��¼�����
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// �����¼�Դ
		if(arg0.getSource()== tfdWord)//�û���������а��س�
		{
			//����û����������
			String s1 =tfdWord.getText();
			//��õ�ǰ��ʾ������
			String s2 =lblWord.getText();
			//�Ƚ�
			if(s1.equals(s2))
			{   
				score++;
				lblScore.setText("�÷֣�"+score);
			}
		}//���������е�����
		tfdWord.setText("");
		
	}//end actionOerfirmed
	//�����ʱ����
	int totalTime= 2*60;
	//����һ������ʱ�߳�������TaskOfCountTime
	class TaskOfCountTime extends Thread
	{
		public void run()
		{   
			do{
			try{
			sleep(1000);
			   }catch(Exception e){}
			totalTime--;
			lblTime.setText("����ʱ��"+totalTime);
		      }while(totalTime>0);
			JOptionPane.showMessageDialog(null, "���β��Խ�����");
		}//end run
	}
	}//end class TaskOfCountTime

