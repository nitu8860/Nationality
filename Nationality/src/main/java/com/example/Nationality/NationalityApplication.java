package com.example.Nationality;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NationalityApplication {

	public static void main(String[] args) throws Exception {

		URL getUrl = new URL("https://api.nationalize.io/?name=nathaniel");
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();

		if (responseCode == 200) {

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder jsonResponseData = new StringBuilder();
			String readLine = null;

			while ((readLine = in.readLine()) != null) {
				jsonResponseData.append(readLine);
			}

			in.close();

			JSONObject jsonObject = new JSONObject(jsonResponseData.toString());

			JSONArray countryArray = jsonObject.getJSONArray("country");

			for (int i = 0; i < countryArray.length(); i++) {
				JSONObject country = countryArray.getJSONObject(i);
				System.out.println("Country ID: " + country.getString("country_id"));
				System.out.println("Probability: " + country.getDouble("probability"));
			}

			System.out.println("Name: " + jsonObject.getString("name"));

		} else {
			System.out.println("This is not valid URL- " + responseCode);
		}

	}
}
