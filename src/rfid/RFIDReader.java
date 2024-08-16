package rfid;

import javax.swing.SwingUtilities;

import com.phidgets.*;
import com.phidgets.event.*;

import userInterfaces.LocationGUI;
import dao.LocationDAO;
public class RFIDReader implements TagLossListener, TagGainListener {
	
	private  RFIDPhidget phid = new RFIDPhidget();
	private String location;
	public String id;
	static LocationGUI locSystem;
	private static LocationDAO locDB;
	
	
	public RFIDReader() throws PhidgetException {
		
		
		
		try {
		
		phid.addTagGainListener(this);
		phid.addTagLossListener(this);
		
		
		phid.openAny();

        phid.waitForAttachment();
        
        int tempId = phid.getSerialNumber();
        
        id = String.valueOf(tempId);
        
        getLocation(id);
        
        
        //Sleep around to stop the program closing
        while (true)
            try {
                Thread.sleep(1000);
            } catch (Throwable t) {
                t.printStackTrace();
            }
		}
		
		
		finally
		{
			phid.close();
			System.out.println("Closed and exiting...");
		}
	}
	
	private static void getLocation(String id) {
		
		locDB = LocationDAO.getInstance();
		String tempLoc = locDB.getLocation("1100608281");
		
		locSystem.locLabel.setText(tempLoc);
		
	}
	
	

	@Override
	public void tagGained(TagGainEvent arg0) {
		
		//System.out.println(arg0.getValue());
		
		
		
		
		System.out.println("Reader ID: " + id);
		LocationGUI.locLabel.setText(locDB.getLocation(id));
		
	}

	@Override
	public void tagLost(TagLossEvent arg0) {


		System.out.println("This is some placeholder text for when you remove a tag");
		LocationGUI.locLabel.setText("");
		
	}
	
public static void main(String[] args) throws PhidgetException {
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
        		locSystem = new LocationGUI();
        		
            	
            }
        });
		
		new RFIDReader();
		
	}
	

	
	
}
