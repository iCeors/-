import java.util.ArrayList;

public class MathSupport {

	public static double transToUK(double d){
		return 100*(13 - Math.sqrt(13*13 - 4 * 0.05*((9* d)-100))) / 10.0;
	}
	
	public static double calFullAverage(ArrayList<Period> list){
		double d=0;
		
		for(Period p:list){
			d+=p.getYearScore();
		}
		return d;
	}
	
	public static String calPartialAverage(ArrayList<Period> list){
		ArrayList<Period> subListAv=new ArrayList<Period>();
		ArrayList<Period> subListUnav=new ArrayList<Period>();
		for(Period p:list){
			if(p.getAvailable()){
				subListAv.add(p);
			}else{
				subListUnav.add(p);
			}
		}
		double score=calFullAverage(subListAv);
		String unavItems="";
		double weight=0;
		for(Period p:subListUnav){
			unavItems+=p.yearName+"、";
			weight+=p.getWeight();
		}
		int score1=(int)((70-score)/weight);
		int score2=(int)((60-score)/weight);
		int score3=(int)((50-score)/weight);
		String message="平均分="+score+"\n如要获得一等学位，"+unavItems+"须达到"+score1+"分";
		
		message+="\n如要获得2等1学位，"+unavItems+"须达到"+score2+"分";
		message+="\n如要获得2等2学位，"+unavItems+"须达到"+score3+"分";
		return message;
		
	}
	
}
