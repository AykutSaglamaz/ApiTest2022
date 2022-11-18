package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostVePojo03 extends HerOkuAppBaseUrl {
    /*
    Given
             https://restful-booker.herokuapp.com/booking/

             {
                "firstname": "Aykut",
                "lastname": "Saglam",
                "totalprice": 998,
                "depositpaid": true,
                "bookingdates":
                        {
                         "checkin": "2022-11-05",
                         "checkout": "2022-11-21"
                         },
                "additionalneeds": "Breakfast with coffee, Dragon Juice"
              }
    When
            URL'e POST Request gonder
            URL'e GET request gonder ( yeni olusturulan datayi almak icin)
    Then
            Status code 200 olmali
    And
            Response body asagidaki gibi olmali
                  {
                 "firstname": "Aykut",
                 "lastname": "Saglam",
                 "totalprice": 998,
                 "depositpaid": true,
                 "bookingdates": {
                                "checkin": "2022-11-05",
                                "checkout": "2022-11-21"
                                   },
                 "additionalneeds": "Breakfast with coffee, Dragon Juice"
                     }
 */
    @Test
    public void test01(){
        //1. adim: url'e set et
        spec.pathParam("first", "booking");
        //2. adim: request body set et yada expected data set et

        BookingDatesPojo bookingDates= new BookingDatesPojo("2022-11-05","2022-11-21");

        BookingPojo requestBody = new BookingPojo("Aykut","Saglam", 998, true, bookingDates, "Breakfast with coffee, Dragon Juice");
        System.out.println(requestBody);

        //3. adim: request gonder, response al

        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
//        response.prettyPrint();

    // DB'de yeni bir data olusturduktan sonra, eger "GET" veya baska bir methodk kulllanmak istersek "bookingId" ye ihtiyacimiz var
        // bookingID yeni olusturulan "POST" methodun response body'sinden alinir
        JsonPath json = response.jsonPath();
        Integer bookingId =  json.getInt("bookingid");

        //ilk adim: GET methodu icin url'i set
        spec.pathParams("first", "booking", "ikinci", bookingId );

        //ikinci adim: Get request gonder, response al
        Response response1 =given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{ikinci}");
//        response1.prettyPrint();

        //ucuncu adim: assertion yap
        BookingPojo actualData =   response1.as(BookingPojo.class);
        System.out.println(actualData);

        response1.then().assertThat().statusCode(200);
        assertEquals(200, response1.getStatusCode());

        assertEquals(" Isimler uyusmuyor",requestBody.getFirstname(), actualData.getFirstname());
        assertEquals(requestBody.getLastname(), actualData.getLastname());

        assertEquals(requestBody.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

        assertEquals(requestBody.getAdditionalneeds(), actualData.getAdditionalneeds());







    }


}
