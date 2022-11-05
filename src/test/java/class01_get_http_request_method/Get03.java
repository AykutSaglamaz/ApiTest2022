package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {
     /*
       Given
           https://jsonplaceholder.typicode.com/todos/23
       When
           Kullanici GET Request'i Url'e (APi) gonderir
       Then
           HTTP Status Code 200 olmali
       And
           Response formati “application/json” olmali
       And
           “title”, “et itaque necessitatibus maxime molestiae qui quas velit” olmali,
       And
           “completed” is false
       And
           “userId”  2 olmali
    */
    @Test
    public void get03(){
        //1.adim: url set et
        spec.pathParams("ilk", "todos", "ikinci", 23 );

        //2. adim: beklenen (expected) data set et

        //3.adim: Get request yapilir ve Get response alinir

       Response response =  given().spec(spec).when().get("/{ilk}/{ikinci}");
       response.print();

        //4.adim: Assertion yap
        //1.yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit") ).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));

        //2.yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));


    }
}
