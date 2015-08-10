package listPreparation;


public class Item {
private static boolean errors =false;
private  String stimulus;
private String response;
private int timesCorrect;
private int timesIncorrect;
private int date;
private int interval;
  public static Item apply(String line){
	  final String[] parts = line.split(" *\\|\\| *");
	  switch (parts.length) {
	case 6: return new Item(parts[0], parts[1],  Integer.valueOf(parts[2]), Integer.valueOf(parts[3]),
			Integer.valueOf(parts[4]), Integer.valueOf(parts[5]));
	case 2: return new Item(parts[0], parts[1], 0, 0, 0, 0);
    
	default:
		errors = true;
        return new Item("// " + line, "?", 0, 0, 0, 0);
	}
  }


Item( final String stimulus, String response,
                int timesCorrect, int timesIncorrect,
                int  interval,  int date) {
  this.setStimulus(stimulus);
  this.setResponse(response);
  this.timesCorrect = 0;
  this.timesIncorrect = 0;
  this.date =0;
  this.interval = 0;
  
  }

public boolean equalStimulus (Item that){
	if(this.getStimulus().equals(that.getStimulus()))
		return true;
	else
		return false;
}
public boolean equalResponse (Item that){
	if(this.getResponse().equals(that.getResponse()))
		return true;
	else
		return false;
}


@Override
public String toString(){
	if (timesCorrect != 0 || timesIncorrect != 0)
	   return getStimulus() + " || " + getResponse() + " || " + timesCorrect + 
			   " || " + timesIncorrect +   " || " + interval + " || " + date;
	else
		return getStimulus() + " || " + getResponse() + "";
}

public boolean equals(Item that){
	if( equalResponse(that) && equalStimulus(that) && (this.timesCorrect == that.timesCorrect) && 
			(this.timesIncorrect == that.timesIncorrect) && (this.date == that.date) && 
			(this.interval == that.interval)) return true;
	else return false;
	
}

public int hashCode(Item that){
	return date*interval + timesCorrect;
	
}


public String getStimulus() {
	return stimulus;
}


public void setStimulus(String stimulus) {
	this.stimulus = stimulus;
}


public String getResponse() {
	return response;
}


public void setResponse(String response) {
	this.response = response;
}

}





