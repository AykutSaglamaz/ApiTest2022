package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetVePojo01 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/2
    When
       Url'e GET Request gonder
    Then
        Status code is 200
    And response body is like
            {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 2400,
                "depositpaid": true,
                "bookingdates": {
                                   "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                    }
                 "additionalneeds": "Breakfast"
              }
     */

    @Test
    public void getVePojo01(){
        //1. adim url'i set et
        spec.pathParams("ilk", "booking", "ikinci", 2);
        //2. adim expected datayi set et

        BookingDatesPojo bookingDates = new BookingDatesPojo("2017-09-11", "2021-03-14");
        BookingPojo expectedData = new BookingPojo("Mark", "Jackson", 554, true, bookingDates, "additionalneeds" );
        System.out.println(expectedData);

        //3.adim: request gonder, respond al
       Response response = given().spec(spec).when().get("/{ilk}/{ikinci}");
       response.prettyPrint();

       //4.adim: assertion yap
      BookingPojo actualData =  response.as(BookingPojo.class);
        System.out.println(actualData);

      assertEquals(200, response.getStatusCode() );

      assertEquals("isimler eslesmiyor", expectedData.getFirstname(), actualData.getFirstname());
      assertEquals("Toplam ucret eslesmiyor", expectedData.getTotalprice(), actualData.getTotalprice());

      assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

    }
}
