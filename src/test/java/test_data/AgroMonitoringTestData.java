package test_data;

import java.util.HashMap;
import java.util.Map;

public class AgroMonitoringTestData {

    public float coordinates [][][]= { {
                                    {-121.1958f,37.6683f},
                                    {-121.1779f,37.6687f},
                                    {-121.1773f,37.6792f},
                                    {-121.1958f,37.6792f},
                                    {-121.1958f,37.6683f}
                                     } };

    public Map<String, Object>  geometrySetUp(){
        Map<String, Object> geometry = new HashMap<>();
        geometry.put("type","Polygon");
        geometry.put("coordinates",coordinates );
        return geometry;
    }
    Map<String, Object> properties = new HashMap<>();

}
