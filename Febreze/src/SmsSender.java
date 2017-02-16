// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import java.util.concurrent.TimeUnit;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//Our phone number: +16503824431
//Account SID: AC5f30713e7cdf9d6e13ffbc19db6a87ce
//Auth Token: 03ce89bd64b71e39560b3e4e6a704379

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC5f30713e7cdf9d6e13ffbc19db6a87ce";
    public static final String AUTH_TOKEN = "03ce89bd64b71e39560b3e4e6a704379";

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+16507998047"),  // to
                         new PhoneNumber("+16503824431"),  // from
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
        if(current.getBody().toString().startsWith("change scent to ") || current.getBody().toString().startsWith("Change scent to ") || current.getBody().toString().startsWith("Change Scent To ")){
     	    String arr[] = current.getBody().toString().split(" ", 9);
        	scent = arr[3];
        	location = arr[5];
        	time = arr[7];
        }
        if(current.getBody().toString().startsWith("change temperature to ") || current.getBody().toString().startsWith("Change temperature to ") || current.getBody().toString().startsWith("Change Temperature To ")){
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
                            new PhoneNumber("+16503824431"),  // from
                           tempOutput )
                    .create();
        }
        if(scent != " " || location != " " || time != " "){
        String output = "The scent will be " + scent + " in the " + location + " in " + time + " minutes ";
        Message textBackInput = Message
                .creator(new PhoneNumber("+16507998047"),  // to
                        new PhoneNumber("+16503824431"),  // from
                       output)
                .create();
        }
        
    
    /** at this point in time (2/16/17) we have the scent, location, and time from the user
     *  now, we need to use those three to actually change the scent, location, and time in febreze home
     */
    
    
    
    }

    	/*
    	JSONObject json;
		try {
			json = new JSONObject(readUrl(replace("https://maps.googleapis.com/maps/api/directions/json?origin=" + StartLoc + "&destination=" + EndLoc + "l&key=AIzaSyD7M8x1hsi7HWKfOJPZRZFuIRsuQvcIe8M")));
			JSONArray test = (JSONArray) json.getJSONArray("routes").getJSONObject(0).get("legs");
			JSONObject test1 = (JSONObject) test.getJSONObject(0);
			ArrayList<String> directions = new ArrayList<String>();
			JSONArray counter = (JSONArray) test1.getJSONArray("steps");
			
			for(int i = 0; i < counter.length(); i++){
				JSONObject test2 = (JSONObject) test1.getJSONArray("steps").get(i);
				String PREHTMLSTRING = test2.getString("html_instructions");
				String feet = test2.getJSONObject("distance").getString("text");
				String FINALSTRING = PREHTMLSTRING.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
				System.out.println(FINALSTRING);
				directions.add(FINALSTRING + " Distance: " + feet);
			}
			
			
			
			for(int i = 0; i < directions.size(); i++){
				Message directionMessage = Message
		                .creator(new PhoneNumber("+16507998047"),  // to
		                        new PhoneNumber("+16503824431"),  // from
		                       directions.get(i))
		                .create();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
      
    }
    
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
        
    }
    


public static String replace(String str) {
    String[] words = str.split(" ");
    StringBuilder sentence = new StringBuilder(words[0]);

    for (int i = 1; i < words.length; ++i) {
        sentence.append("%20");
        sentence.append(words[i]);
    }

    return sentence.toString();
}
*/
    }
    


