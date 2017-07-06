package com.anwarzalek.assignment;


import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by anwar on 06/07/17.
 */
public class Utilities {
    private static Reader connectionInit(HttpServletRequest request) {
        URL base = null;
        try {
            base = new URL("https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLConnection con = null;
        request.getParameter("destinationName");
        Map<String, String[]> params = request.getParameterMap();
        List<NameValuePair> k = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            k.add(new BasicNameValuePair(entry.getKey(), entry.getValue()[0]));
        }
        try {
            try {
                con = new URIBuilder(String.valueOf(base))
                        .addParameters(k)
                        .build()
                        .toURL()
                        .openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Reader Rd = null;

        if (con != null) {
            try {
                Rd = new InputStreamReader(con.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Rd;
    }


    public static String response(HttpServletRequest request) {
        JSONParser parser = new JSONParser();

        String hotelsInfo = "";
        try {
            Object obj = parser.parse(connectionInit(request));

            JSONObject jsonObject = (JSONObject) obj;

            JSONObject offers = (JSONObject) jsonObject.get("offers");

            JSONArray Hotels = (JSONArray) offers.get("Hotel");

            for (int i = 0; i < Hotels.size(); i++) {
                JSONObject tempHotelInfo = (JSONObject)
                        ((JSONObject) Hotels.get(i))
                                .get("hotelInfo");
                JSONObject dateInfo = (JSONObject)
                        ((JSONObject) Hotels.get(i))
                                .get("offerDateRange");
                JSONObject PriceInfo = (JSONObject)
                        ((JSONObject) Hotels.get(i))
                                .get("hotelPricingInfo");
                hotelsInfo += "<b>Hotel Name : </b>" + tempHotelInfo.get("hotelName") + ",<br/>" +
                        "<b>Hotel Destination : </b>" + tempHotelInfo.get("hotelLongDestination") + ",<br/>" +
                        "<b>Hotel Star Rating : </b>" + tempHotelInfo.get("hotelStarRating") + ",<br/>" +
                        "<b>Description : </b>" + tempHotelInfo.get("description") + ",<br/>" +
                        "<b>Offer Start Date : </b>" + dateInfo.get("travelStartDate")  + ",<br/>" +
                        "<b>Offer End Date : </b>" + dateInfo.get("travelEndDate")  + ",<br/>" +
                        "<b>Total Price(with Taxes and Fees) : </b>" + PriceInfo.get("totalPriceValue")  + " $,<br/>" +
                        
                        "<br/><br/><br/>";
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hotelsInfo;
    }
}
