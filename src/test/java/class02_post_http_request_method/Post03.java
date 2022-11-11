package class02_post_http_request_method;

import base_url.AgroMonitoringBaseUrl;
import org.junit.Test;

public class Post03 extends AgroMonitoringBaseUrl {
    /*
           Given
            "http://api.agromonitoring.com/agro/1.0/polygons?appid=7bd9969a12d424af27967d6d821dc5f3"

                {
                   "name":"Polygon Sample",
                   "geo_json":{
                      "type":"Feature",
                      "properties":{},
                      "geometry":{
                            "type":"Polygon",
                            "coordinates":
                           [
                              [
                                   [-121.1958,37.6683],
                                   [-121.1779,37.6687],
                                   [-121.1773,37.6792],
                                   [-121.1958,37.6792],
                                   [-121.1958,37.6683]
                                ]
                             ]
                          }
                       }
                  }
        When
             I send POST Request to the Url
        Then
            Assert Status Code (201)
            And Response Body should be like {
                                                "id": "5fd8c383714b523b2ce1f154",
                                                "geo_json": {
                                                    "geometry": {
                                                        "coordinates":
                                                           [ [ [-121.1958, 37.6683],
                                                                [-121.1779, 37.6687],
                                                                [-121.1773, 37.6792],
                                                                [-121.1958, 37.6792],
                                                                [-121.1958, 37.6683] ] ],
                                                        "type": "Polygon"
                                                                },
                                                    "type": "Feature",
                                                    "properties": {}
                                                            },
                                                "name": "Polygon Sample",
                                                "center": [-121.1867, 37.67385],
                                                "area": 190.9484,
                                                "user_id": "5fd8c02a3da20c000759e0f8",
                                                "created_at": 1608041347
                                            }
         */
    @Test
    public void post03(){
        //1.adim: url'i set et
        spec.pathParams("first", "agro", "second", 1.0, "final", "polygons").
                queryParam("appid", "7bd9969a12d424af27967d6d821dc5f3");
        //2.adim expected data set et




    }
}
