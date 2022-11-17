package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostVePojo01 extends JsonPlaceHolderBaseUrl {
        /*
   Given
            https://jsonplaceholder.typicode.com/todos
            {
              "userId": 55,
              "title": "Tidy your room",
              "completed": false
              }
    When
            Url'e POST Request gonder
    Then
        Status code is 201
    And
         response body asagidaki gibi olmali
         {
              "userId": 55,
              "title": "Tidy your room",
              "completed": false,
              "id": 201
         }
 */

    @Test
    public void postVePojo01(){
        //1.adim
        spec.pathParam("first", "todos");
        //2.adim
        JsonPlaceHolderPojo requestBodyPojo = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //3. adim
       Response response = given().spec(spec).contentType(ContentType.JSON).when().body(requestBodyPojo).post("/{first}");

       //4. adim
        //1. yol
        response.then().assertThat().statusCode(201).body("userId", equalTo(requestBodyPojo.getUserId()),
            "title", equalTo(requestBodyPojo.getTitle()),  "completed", equalTo(requestBodyPojo.getCompleted())   );

        //2. yol: de-serialization

        JsonPlaceHolderPojo actualData =  response.as(JsonPlaceHolderPojo.class);
        assertEquals(requestBodyPojo.getUserId(), actualData.getUserId() );
        assertEquals(requestBodyPojo.getTitle(), actualData.getTitle());
        assertEquals(requestBodyPojo.getCompleted(), actualData.getCompleted());



    }

}
