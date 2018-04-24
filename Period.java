import java.util.ArrayList;

public class Period {

	private ArrayList<Double> scoreList=new ArrayList<Double>();
	private double average; 
	private double weight;
	private boolean avaliable;
	public String yearName;
	
	public Period(double d,String s){
		this.weight=d;
		this.yearName=s;
		this.avaliable=false;
	}
	
	public void addScore(double i){
		scoreList.add(i);
	}
	
	public boolean removeLast(){
		if(!scoreList.isEmpty()){
			scoreList.remove(scoreList.size()-1);
			return true;
		}
		return false;
	}
	
	public  void calAverage(){
		double temp=0.0;
		double size=scoreList.size();
		for(double s:scoreList){
			temp+=s;
		}
		this.average=temp/size;
	}
	
	public double getYearScore(){
		this.calAverage();
		return this.weight*this.average;
	}
	
	public double getWeight(){
		return this.weight;
	}
	
	public void setAvaliable(boolean b){
		this.avaliable=b;
	}
	
	public boolean getAvailable(){
		return this.avaliable;
	}
	
	public String getAllScores(){
		String temp="";
		for(double s:scoreList){
			temp+=" "+String.format("%.2f", s);
		}
		return temp;
	}
	
	public boolean isScoreEmpty(){
		if(scoreList.isEmpty())
			return true;
		return false;
	}
	
}
