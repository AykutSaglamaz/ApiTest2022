package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {
   /*
    Given
     https://restful-booker.herokuapp.com/booking
        {
    "firstname": "Eyup",
    "lastname": "Yilmaz",
    "totalprice": 2222,
    "depositpaid": true,
    "bookingdates": {
            "checkin": "2022-11-11",
            "checkout": "2022-11-12"
                }
        }
    When
         URL'e POST Request gonder
    Then
        Status code is 200
        And response body asagidaki olmali
              {
              "firstname": "Eyup",
              "lastname": "Yilmaz",
              "totalprice": 2222,
              "depositpaid": true,
              "bookingdates": {
                    "checkin": "2022-11-11",
                    "checkout": "2022-11-12"
                        }
                 }
     */
@Test
    public void post01() {
    //1.adim: url'i set et
    spec.pathParam("first", "booking");

    //2.adim: expected data set et
    Map<String, String> expectedBookingDates = new HashMap<>();
    expectedBookingDates.put("checkin", "2022-11-11");
    expectedBookingDates.put("checkout", "2022-11-12");

    System.out.println(expectedBookingDates);

    Map<String, Object> expectedData = new HashMap<>();
    expectedData.put("firstname", "Eyup");
    expectedData.put("lastname", "Yilmaz");
    expectedData.put("totalprice", 2222);
    expectedData.put("depositpaid", true);
    expectedData.put("bookingdates", expectedBookingDates);
//    expectedData.put("additionalneeds", "Breakfast");

    System.out.println(expectedData);

    //3.adim: Post request ve response al
     Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
     response.prettyPrint();

     //4. assertion yap
    response.then().assertThat().statusCode(200);

    Map<String, Object> actualData = response.as(HashMap.class);
    System.out.println(actualData);

    assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
    assertEquals(expectedData.get("lastname") , ((Map) actualData.get("booking")).get("lastname"));
    assertEquals(expectedData.get("totalprice") , ((Map) actualData.get("booking")).get("totalprice"));
    assertEquals(expectedData.get("depositpaid") , ((Map) actualData.get("booking")).get("depositpaid"));

    assertEquals(expectedBookingDates.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin") );
    assertEquals(expectedBookingDates.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout") );



    }
}
