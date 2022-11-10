package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get9 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/74
        When
            Url'e GET Request gonder
        Then
            Response body asagidaki gibi olmali;
            {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates":
                        {
                        "checkin": "2013-02-23",
                        "checkout": "2014-10-23"
                         }
                "additionalneeds": "Breakfast"
            }
     */
    @Test
    public void get09(){
        //1. adim Url'i set et
        spec.pathParams("first", "booking", "second", 74);
        //2.adim: expected datayi set et

        Map<String, String> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin", "2018-01-01");
        expectedBookingDates.put("checkout", "2019-01-01");

        System.out.println(expectedBookingDates);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "James");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", expectedBookingDates);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println(expectedData);

        //3.adim: request gonder, respond al
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

        Map<String, Object> actualData =   response.as(HashMap.class);
        System.out.println(actualData);
//        System.out.println(((Map)actualData.get("bookingdates")).get("checkin"));

        //4.adim: assertion yap

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        assertEquals(expectedBookingDates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));










    }
}
