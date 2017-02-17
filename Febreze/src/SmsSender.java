// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import java.util.concurrent.TimeUnit;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//Our phone number: +16503341722
//Account SID: AC374c1dfc3218a2cdd072c9f7e94eaf7b
//Auth Token: 130e2510cee82f16cce2060f28242e64

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;




public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC374c1dfc3218a2cdd072c9f7e94eaf7b";
    public static final String AUTH_TOKEN = "130e2510cee82f16cce2060f28242e64";

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
       
        
        Message message = Message
                .creator(new PhoneNumber("+16507998047"),  // to
                         new PhoneNumber("+16503341722"),  // from
                         "Welcome to your Febreze Home! How may I assist you?")
                .create();
        String scent = " ";
        String location = " ";
        String time = " "; // in minutes as string
        String temperature = " "; // in celsius
        
        
        //User example input: Change scent to strawberry in kitchen in 15 minutes
        // or Change temperature to 15 degrees
        /**
         * How we are going to parse that^:
         * Put string into array
         * String arr[] = mystring.split(" ", 2);
         * If input startsWith("Change scent to ")
         * scent = arr[3]
         * location = arr[5]
         * time = arr[7]
         */
        //TimeUnit.SECONDS.sleep(5);
        
        while((scent == " " || location == " " || time == " ") && temperature == " "){
        	

        ResourceSet<Message> messages = Message.reader().read();
        Iterator it = messages.iterator();
        Message current = (Message) it.next();
        

        // lowercase
        if(current.getBody().toString().toLowerCase().startsWith("change scent to ")){
     	    String arr[] = current.getBody().toString().split(" ", 9);
        	scent = arr[3];
        	location = arr[5];
        	time = arr[7];
        }
        if(current.getBody().toString().toLowerCase().startsWith("change temperature to ")){
     	    String arr[] = current.getBody().toString().split(" ", 5);
        	temperature = arr[3];
        }
        
        
        System.out.println(current.getBody().toString());
        
      
        }
        
        
        System.out.println(scent);
        System.out.println(location);
        System.out.println(time);
        System.out.println(temperature);
        String tempOutput = " ";
        if(temperature != " "){
        	tempOutput = "The temperature is now " + temperature + " degrees celsius";
        	Message textBackInput = Message
                    .creator(new PhoneNumber("+16507998047"),  // to
                            new PhoneNumber("+16503341722"),  // from
                           tempOutput )
                    .create();
        	System.out.println(readJSONfileForTemp(temperature));
        }
        if(scent != " " || location != " " || time != " "){
        String output = "The scent will be " + scent + " in the " + location + " in " + time + " minutes ";
        Message textBackInput = Message
                .creator(new PhoneNumber("+16507998047"),  // to
                        new PhoneNumber("+16503341722"),  // from
                       output)
                .create();
        System.out.println(readJSONfileForScent(scent, location, time));
        }
        
    
  
    
    }

	private static JSONObject readJSONfileForTemp(String temperature) {
		// TODO Auto-generated method stub
		JSONObject febrezeHomeFactors = new JSONObject();    
		febrezeHomeFactors.put("temperature",temperature);    
		return febrezeHomeFactors;
	}

	private static JSONObject readJSONfileForScent(String scent, String location, String time) {
		// TODO Auto-generated method stub
		JSONObject febrezeHomeFactors = new JSONObject();    
		febrezeHomeFactors.put("scent",scent);    
		febrezeHomeFactors.put("location",location);    
		febrezeHomeFactors.put("time",time);    
		return febrezeHomeFactors;
	}

    }
    


