
package com.crosspay.payment.util;

/**
 * @author Reddy
 *
 */
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;


public class SendSMS{
	Logger logger = Logger.getLogger(SendSMS.class);
	
	/*public static void main(String args[]) {
		String mobiles = "8008273176";
		try {
			sendMessage(mobiles);
		} catch (Exception e) {
			System.out.println("error in sending sms "+e.toString());
		}
	}*/
	
        
        public static void sendMessage(String mobiles,String message) throws Exception{
    		
        	 String authkey = "170446AX9bPUsyQ59969aff";
             //Sender ID,While using route4 sender id should be 6 characters long.
             String senderId = "CROSSP";
             //define route
             String route="4";
             
             String country="91";
     
        	URLConnection myURLConnection=null;
            URL myURL=null;
            BufferedReader reader=null;

            //Send SMS API
            String mainUrl="https://api.msg91.com/api/sendhttp.php?";
            
        	/*VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			velocityEngine.init();	*/	
			
		
			Map model = new HashMap();
		//	model.put("customTemplate", custom);
			
			Constants cn = new Constants();
			/*String notificationMsg = VelocityEngineUtils
					.mergeTemplateIntoString(velocityEngine,
							cn.SMS_TEMPLATE
							+ custom.getTemplateName(),
							 model);*/
			
			String notificationMsg = message;
			 //encoding message
			String encoded_message=URLEncoder.encode(notificationMsg.replace("+", "%2b"),"UTF-8");
            //Prepare parameter string
            StringBuilder sbPostData= new StringBuilder(mainUrl);
            sbPostData.append("authkey="+authkey);
            sbPostData.append("&mobiles="+mobiles);
            sbPostData.append("&message="+encoded_message);
            sbPostData.append("&route="+route);
            sbPostData.append("&sender="+senderId);
            sbPostData.append("&country="+country);

            //final string
            mainUrl = sbPostData.toString();
            try
            {
                //prepare connection
                myURL = new URL(mainUrl);
                myURLConnection = myURL.openConnection();
                myURLConnection.connect();
                reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                //reading response
            
               // new VersionDM().sms(custom.getUserId(), "SMS", notificationMsg);
                //finally close connection
                reader.close();
            }
            catch (IOException e)
            {
            	System.out.println("error in sending message "+e.toString());
                    e.printStackTrace();
        }
        }
}