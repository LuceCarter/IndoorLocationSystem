package techInterfaces;

public class TechFactory {
	
	public Tech createTech() {
		Tech tech = null;
		
		if(type.equals("RFID")) 
		{
			tech = new RFIDTech();
		}
	}

}
