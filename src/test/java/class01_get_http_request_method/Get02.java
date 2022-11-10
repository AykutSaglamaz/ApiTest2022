package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 extends HerOkuAppBaseUrl {
/*
        Given
            https://restful-booker.herokuapp.com/booking/10012
        When
            Kullanici GET Request'i Url'e (APi) gonderir
        Then
            HTTP Status code 404 olmali
        And
            Status Line 'HTTP/1.1 404 Not Found' olmali
        And
            Response body "Not Found" icerir
        And
            Response body "ArcaneData" icermez
        And
            Server "Cowboy" olmali
     */
    @Test
    public void get02(){
        //1. set the url
        //String url = "https://restful-booker.herokuapp.com/booking/10012"; // onerilmez
        spec.pathParams("first", "booking", "second", 10012);
       // 2.adim : Beklenen datayi set et

       //3. adim: Get request gonder ve get response al
            Response response = given().spec(spec).when().get("/{first}/{second}");
            response.prettyPrint();

        //4.adim: Assertion
            response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

           /// assertTrue(true) ==> yesil tick      assertTrue(false) ==> carpi (hata) verecek
        assertTrue(response.asString().contains("Not Found"));

        assertFalse(response.asString().contains("ArcaneData"));


       //Beklenen data test case'den gelir, actual (gercekte olan)data API'den gelir
        assertEquals("Cowboy", response.getHeader("Server"));


    }
}
