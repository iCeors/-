import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OneYearPanel extends JPanel{

	public Period p;
	private JTextField scoreField=new JTextField(5);
	private JButton transBtn=new JButton("转为英方成绩并输入下一科");
	private JButton nextBtn=new JButton("确认，输入下一科");
	private JButton cancelBtn=new JButton("撤销上一科");
	private JPanel downPanel2=new JPanel();
	private JLabel scoreLabel=new JLabel();
	
	public OneYearPanel(double d,String s){
		p=new Period(d,s);
		JPanel upPanel=new JPanel();
		JPanel midPanel=new JPanel();
		upPanel.add(new JLabel("请输入一门"+p.yearName+"成绩："));
		upPanel.add(scoreField);
		
		midPanel.add(transBtn);
		midPanel.add(nextBtn);
		midPanel.add(cancelBtn);
		
		transBtn.addActionListener(new TransListener());
		nextBtn.addActionListener(new NextListener());
		cancelBtn.addActionListener(new CancelListener());
		
		
		
		String allScores="已输入的成绩："+p.getAllScores();
		scoreLabel.setText(allScores);
		downPanel2.add(scoreLabel);
		
		this.add(upPanel);
		this.add(midPanel);
		this.add(downPanel2);
		
		this.setLayout(new GridLayout(3,1));
	}
	
	public String getYearName(){
		return this.p.yearName;
	}
	
	public boolean checkInput(){
		String s=scoreField.getText();
		double i=0;
		try{
			i=Double.parseDouble(s);
		}
		catch(Exception e){
			return false;
		}
		if(i<=0||i>100){
			return false;
		}
		return true;
	}
	
	class TransListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!checkInput()) return;
			double d=MathSupport.transToUK(Double.parseDouble(scoreField.getText()));
			p.addScore(d);
			String allScores="已输入的成绩："+p.getAllScores();
			scoreLabel.setText(allScores);
			p.setAvaliable(true);
			scoreField.setText("");
			
		}
		
	}
	
	class NextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!checkInput()) return;
			double d=Double.parseDouble(scoreField.getText());
			p.addScore(d);
			String allScores="已输入的成绩："+p.getAllScores();
			scoreLabel.setText(allScores);
			p.setAvaliable(true);
			scoreField.setText("");
		}
		
	}
	
	class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			p.removeLast();
			String allScores="已输入的成绩："+p.getAllScores();
			scoreLabel.setText(allScores);
			if(p.isScoreEmpty())
				p.setAvaliable(false);
			
		}
		
	}
	
	
}
