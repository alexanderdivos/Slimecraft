package me.mrCookieSlime.Slimecraft.Minecraft;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import me.mrCookieSlime.Slimecraft.Dialogs.Warning;
import me.mrCookieSlime.Slimecraft.Messages.Error;

import org.json.simple.JSONValue;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MinecraftAuthentication {
	
	public static String send(String username, String password) throws IOException {
		
		URL url = new URL("https://authserver.mojang.com/authenticate");
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		String request = 
				"{" +
		
				"\"agent\":" +
				
				"{"
					+ "\"name\": \"Minecraft\","
					+ "\"version\": \"13\""
					+
				"},"
				
				+
				"\"username\": \"" + JSONValue.escape(username) + "\","
				+
				"\"password\": \"" + JSONValue.escape(password) + "\","
				+
				"\"requestUser\": \"true\""
				
				+ "}";
		
		String replaced = "";
	    for (int i = 0; i < password.length(); i++) {
	    	replaced = replaced + "*";
	    }
	    
	    String replacedRequest = 
				"{" +
		
				"\"agent\":" +
				
				"{"
					+ "\"name\": \"Minecraft\","
					+ "\"version\": \"13\""
					+
				"},"
				
				+
				"\"username\": \"" + JSONValue.escape(username) + "\","
				+
				"\"password\": \"" + JSONValue.escape(replaced) + "\","
				+
				"\"requestUser\": \"true\""
				
				+ "}";
		
		System.out.println("Sending JsonArray: " + replacedRequest);
		
		connection.setConnectTimeout(15000);
	    connection.setReadTimeout(15000);
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/json");

	    connection.setRequestProperty("Content-Length", new StringBuilder().append("").append(request.getBytes().length).toString());

	    connection.setUseCaches(false);
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
        
	    DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
	    writer.write(request.getBytes());
	    writer.flush();
	    writer.close();
	    
	    BufferedReader reader = null;
	    try {
	      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    } catch (IOException e) {
	      reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	    }
	    String response = "";
	    String line;
	    while ((line = reader.readLine()) != null) {
	      response = response + line;
	    }
	    reader.close();
	    
	    System.out.println("Got Response: " + response.toString());
	    
	    if (response.startsWith("{\"error\":")) {
	    	JsonParser parser = new JsonParser();
	    	JsonObject object = parser.parse(response).getAsJsonObject();
	    	String error = object.get("errorMessage").getAsString();
	    	
	    	if (error.equalsIgnoreCase("Invalid credentials. Invalid username or password.")) {
	    		Warning.send(Error.BAD_LOGIN);
	    	}
	    	else if (error.equalsIgnoreCase("Invalid credentials. Account migrated, use e-mail as username.")) {
	    		Warning.send(Error.MIGRATED);
	    	}
	    	else {
	    		Warning.send(Error.BAD_REQUEST);
	    	}
	    	
	    	return "error";
	    }
	    else {
	    	return response;
	    }
		
	}
	
	public static void logout(String username, String password) throws IOException {
		URL url = new URL("https://authserver.mojang.com/signout");
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		String request = 
				"{" +
		
				"\"username\": \"" + username + "\","
				+
				"\"password\": \"" + password + "\""
				
				+ "}";
		
		connection.setConnectTimeout(15000);
	    connection.setReadTimeout(15000);
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/json");

	    connection.setRequestProperty("Content-Length", new StringBuilder().append("").append(request.getBytes().length).toString());

	    connection.setUseCaches(false);
	    connection.setDoInput(true);
	    connection.setDoOutput(true);
        
	    DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
	    writer.write(request.getBytes());
	    writer.flush();
	    writer.close();
	    
	    BufferedReader reader = null;
	    try {
	      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    } catch (IOException e) {
	      reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	    }
	    String response = "";
	    String line;
	    while ((line = reader.readLine()) != null) {
	      response = response + line;
	    }
	    reader.close();
	    
	    System.out.println(response);
	}
}
