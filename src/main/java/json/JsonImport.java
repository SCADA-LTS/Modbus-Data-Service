package json;

import dataPoint.DataPoint;
import modbus.Modbus;
import org.apache.log4j.Logger;

import javax.json.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonImport {

    final static Logger logger = Logger.getLogger(JsonImport.class);

    InputStream fis = null;

    public DataPoint[] getDataPoints(String filename){
        try {
            fis = new FileInputStream(filename);
            logger.info("File " + filename +  " opened");
        } catch (FileNotFoundException e) {
            logger.error("Connot find file " + filename + " at default directory");
            e.printStackTrace();
        }

        JsonReader jsonReader = Json.createReader(fis);
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        try {
            fis.close();
            logger.error("Connot close the file:" + filename);
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
