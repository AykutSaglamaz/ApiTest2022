package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
      /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Dane&lastname=Dominguez
        When
            Kullanici GET requesti URL'e gonderir
        Then
            Status code : 200 olmali
	  	And
	  	    Data'lar arasinda ismi (firstname) “Dane” ve soyismi (lastname) “Dominguez” olan biri olmali
     */
    // Query Params spesifik parametreler secmek icin kullanilir ( orn: ?firstname=Dane&lastname=Dominguez)
    //Path Params  ise resource (kaynagi) kucultmek/daralmak icin kullanilir
    @Test
    public void get05(){
        //1.adim: url'e set et
            spec.pathParam("first", "booking").queryParams("firstname", "Dane", "lastname", "Dominguez");
        //2.adim:beklenen datayi set et

        // 3.adim: get request gonder ve get response al
           Response response=  given().spec(spec).when().get("/{first}");
           response.prettyPrint();

        //4. assertion yap
//        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("bookingid", hasItem(8933));

        response.then().assertThat().statusCode(200);
        assertTrue("Aradiginiz data bulunamadi",response.asString().contains("bookingid"));

    }
}
