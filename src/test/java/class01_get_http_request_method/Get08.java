package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
        APi teste en buyuk zorluk yada handikap data type'leridir
    1) Java,  Object( non-primitive), Maps ve Primitive data typleri kullanir
        API ise XML, Json gibi formatlari kullanir

        JAva ve API farkli data type kullanir dolayisiyla abunlarin birbirleriyle iletisim kurmasi icin
        birbirlerini anlayacak formata getirilmesi lazim. Aksi halde iletisim olmaz

        --Birbirlerini anlamalari icin yapacagimiz iki secenek var---

        i) Data type'i Json formatinda Java object formatina ceviririz ==> De-Serialization
        ii)  Data type'i Java object'ten Json formatina ceviririz ==> Serialization

        De-Serialization ve Serialization icin 2 tane secenek vardir

        a) Gson -- Google olusurur
        b ) object Mapper - daha populer

        Json ==> JavaScript object notation
     */
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
            Url'e GET Request gonder
    Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
         {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
         }
 */

    @Test
    public void get08(){
        //1. adim . url'i set et
        spec.pathParams("first", "todos", "second",2 );
        //2.adim: expected datayi set et
       Map<String, Object> expectedData = new HashMap<>();// HashMap<>() kullandim cunku Map'de en hizlisi HashMap'tir
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("Status Code", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");

        System.out.println(expectedData);//{Server=cloudflare, Status Code=200, completed=false, title=quis ut nam facilis et officia qui, userId=1, Via=1.1 vegur}

        //3. adim: reguest gonder, response al
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       //Gson kullarak, API'den gelen Json datayi Java object formatina ceviriyoruz
      Map<String, Object > actualData = response.as(HashMap.class);// de-serialization yaptik
        System.out.println(actualData);

        //4. adim: assertion yap
        assertEquals(expectedData.get("userId"),actualData.get("userId") );
        assertEquals(expectedData.get("title"),actualData.get("title") );
        assertEquals(expectedData.get("completed"),actualData.get("completed") );
        assertEquals(expectedData.get("Status Code"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));


    }

}
