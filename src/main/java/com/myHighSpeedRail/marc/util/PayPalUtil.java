package com.myHighSpeedRail.marc.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.myHighSpeedRail.marc.dto.paypalapi.CreatePayPalOrderDto;

@Service
public class PayPalUtil {
	@Value("${paypal.api.basic.token}")
	private String basicToken;// ="QWFGOUFodi1XNWJ5eDlPeVJJMXBTWWNxTEE3aURqSXRtUlVPTFlvdG5wam1YQ1Q3S2RmRVBRLXNVOWViTUIzSU5hN1NQMG1ITjlwSVdQMnU6RUFBRGEtMExKaV9mU1FPbVdGU25vRHdtRDAyNWtZdEpkUWVzWDdCc0FESUVVbWZMNjBhUTVHSlFKMm0tTDBIbzBnMnNqY0RVQ3QwZmU3elQ=";
	private String bearerToken=null;
	private Date latestTokenUpdate=null;
	
	public @ResponseBody ResponseEntity<String> getTokenUtil(){
		// check if old token is expire? if less than 5 minute will not get now token
		if( latestTokenUpdate==null) {
			//haven't get a token yet
		}
		else if( new Date().getTime() -latestTokenUpdate.getTime() <= 1000*60*5) {
			return new ResponseEntity<String>("old token not expire yet:"+bearerToken, HttpStatus.OK);
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			String url = "https://api.sandbox.paypal.com/v1/oauth2/token";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "application/json");
			con.setRequestProperty("accept-language", "en_US");
			con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			con.setRequestProperty("authorization", "basic "+basicToken);
			String body = "grant_type=client_credentials";

			// Send request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(body);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// Print the response
//			System.out.println(response.toString());
			JsonNode root = mapper.readTree(response.toString());
			Map<String, String> map = new HashMap<>();
			addKeys("", root, map, new ArrayList<>());

//			map.entrySet()
//			     .forEach(System.out::println);
			bearerToken = map.get("access_token");
			latestTokenUpdate= new Date();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("getToken"+bearerToken,HttpStatus.OK);
	}
	
	private void addKeys(String currentPath, JsonNode jsonNode, Map<String, String> map, List<Integer> suffix) {
	    if (jsonNode.isObject()) {
	        ObjectNode objectNode = (ObjectNode) jsonNode;
	        Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
	        String pathPrefix = currentPath.isEmpty() ? "" : currentPath + "-";

	        while (iter.hasNext()) {
	            Map.Entry<String, JsonNode> entry = iter.next();
	            addKeys(pathPrefix + entry.getKey(), entry.getValue(), map, suffix);
	        }
	    } else if (jsonNode.isArray()) {
	        ArrayNode arrayNode = (ArrayNode) jsonNode;

	        for (int i = 0; i < arrayNode.size(); i++) {
	            suffix.add(i + 1);
	            addKeys(currentPath, arrayNode.get(i), map, suffix);

	            if (i + 1 <arrayNode.size()){
	                suffix.remove(arrayNode.size() - 1);
	            }
	        }

	    } else if (jsonNode.isValueNode()) {
	        if (currentPath.contains("-")) {
	            for (int i = 0; i < suffix.size(); i++) {
	                currentPath += "-" + suffix.get(i);
	            }

	            suffix = new ArrayList<>();
	        }

	        ValueNode valueNode = (ValueNode) jsonNode;
	        map.put(currentPath, valueNode.asText());
	    }
	}
	
	public @ResponseBody ResponseEntity<String>createOrderUtil(@RequestBody CreatePayPalOrderDto dto){
		getTokenUtil();// will refresh the bearer token and update Date;

		try {
			String url = "https://api.sandbox.paypal.com/v2/checkout/orders";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "application/json");
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("accept-language", "en_US");
			con.setRequestProperty("authorization", "Bearer "+bearerToken);
//			System.out.println(token);
			ObjectMapper mapper = new ObjectMapper();
			
			String body= mapper.writeValueAsString(dto);
//			System.out.println(body);
			// Send request
			con.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(body);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return new ResponseEntity<String>(response.toString(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("no work",HttpStatus.BAD_REQUEST);
	}
	
	public Boolean captureOrderUtil(String orderid){
		getTokenUtil();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String url = "https://api.sandbox.paypal.com/v2/checkout/orders/"+orderid+"/capture";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("authorization", "Bearer "+bearerToken);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
//			System.out.println( response.toString());
			JsonNode root = mapper.readTree(response.toString());
			Map<String, String> map = new HashMap<>();
			addKeys("", root, map, new ArrayList<>());
			String status = map.get("status");
			in.close();
			if(status.equals("COMPLETED")) {
				System.out.println("status is completed");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
















