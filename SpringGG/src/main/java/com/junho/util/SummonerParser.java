package com.junho.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junho.dto.Summoner;

public class SummonerParser {
	
	public Summoner getJsonData(String name) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 공백 처리
		String SummonerName = name.replaceAll(" ", "%20");
		System.out.println(SummonerName);
		
		String requestURL 	= "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ SummonerName + "?api_key=" + Key.API_KEY;
		Summoner summoner 	= null;
		
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
			HttpResponse response = client.execute(getRequest);
	
			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				
				summoner = objectMapper.readValue(body, Summoner.class);   // String to Object로 변환
				 		
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return summoner;
	}
}
