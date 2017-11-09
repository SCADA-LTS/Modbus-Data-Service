package json;

import dataPoint.DataPoint;

import javax.json.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonImport {

    InputStream fis = null;

    public DataPoint[] getDataPoints(String filename){
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonReader jsonReader = Json.createReader(fis);
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonArray jsonArray = jsonObject.getJsonArray("dataPoints");
        DataPoint[] dataPoints = new DataPoint[jsonArray.size()];
        int index = 0;
        for(JsonValue value : jsonArray){
            JsonObject innerJsonObject = (JsonObject) value;
            JsonObject innerPointLocator = innerJsonObject.getJsonObject("pointLocator");
            dataPoints[index] = new DataPoint(innerJsonObject.getString("xid"), 2, innerPointLocator.getInt("offset"), innerPointLocator.getInt("bit"), innerJsonObject.getString("name"));
            index++;
        }

        return dataPoints;
    }
}
