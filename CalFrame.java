import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;



public class CalFrame extends JFrame{

	private JComboBox<String> yearBox=new JComboBox<String>();
	private ArrayList<OneYearPanel> yearList=new ArrayList<OneYearPanel>();
	private JButton finishBtn=new JButton("输入完成并计算");
	private JPanel downPanel=new JPanel();
	
	public CalFrame(){
		this.setTitle("学位等级计算器");
		
		yearList.add(new OneYearPanel(0.18,"大二"));
		yearList.add(new OneYearPanel(0.33,"大三"));
		yearList.add(new OneYearPanel(0.24,"大四"));
		yearList.add(new OneYearPanel(0.05,"专业实习"));
		yearList.add(new OneYearPanel(0.20,"毕设"));

		for(OneYearPanel oyp:yearList){
			yearBox.addItem(oyp.getYearName());
		}
		JPanel upPanel=new JPanel();
		upPanel.add(new JLabel("请选择学期："));
		upPanel.add(yearBox);
		upPanel.add(finishBtn);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(upPanel, BorderLayout.NORTH);
		downPanel.add(yearList.get(0));
		this.getContentPane().add(downPanel, BorderLayout.SOUTH);
		
		yearBox.addItemListener(new YearListener());
		finishBtn.addActionListener(new FinishListener());
		
		
		
		
		
		
		this.setSize(500,190);
		
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width; 
		int screenHeight = screenSize.height; 
		this.setLocation(screenWidth/4-windowWidth/4, screenHeight/4-windowHeight/4);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
	}
	
	class YearListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				JComboBox<String> myBox=(JComboBox<String>)e.getSource();
				int index=myBox.getSelectedIndex();
				downPanel.removeAll();
				downPanel.add(yearList.get(index));
				CalFrame.this.setSize(500,190);
				CalFrame.this.setVisible(true);
				CalFrame.this.repaint();
				
			}
			
		}
		
	}
	
	
	class FinishListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Period> pList=new ArrayList<Period>();
			boolean flag=false;
			boolean flag2=false;
			for(OneYearPanel oyp:yearList){
				pList.add(oyp.p);
				if(oyp.p.getAvailable()){
					flag=true;//至少有一个写了分，则为真
				}
				else{
					flag2=true;//出现没写分的学年，则为真
				}
				
			}
			if(!flag) return;
			if(!flag2){
				double score=MathSupport.calFullAverage(pList);
				JOptionPane.showMessageDialog(null, "平均分="+score);
				return;
			}
			String message=MathSupport.calPartialAverage(pList);
			JOptionPane.showMessageDialog(null, message);
			
		}
		
	}
	
	
}
