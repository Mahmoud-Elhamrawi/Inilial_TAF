package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class DataUtility {
    private static final String test_data_path ="src/test/resources/TestData/" ;

    //TODO: read data from json file
public static String getJsonData(String fileName , String key)
{
    try {
        FileReader reader = new FileReader(test_data_path+fileName+".json") ;
        JsonElement jsonElement = JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(key).getAsString();

    }catch (Exception e)
    {
        e.printStackTrace();
    }
    return "";
}

    //TODO:read data from properties file
    private static String getDataFromFileProperty(String fileName ,String key)
    {
        try {
            Properties properties = new Properties();

            properties.load(new FileInputStream(test_data_path+fileName+".properties"));
            return properties.getProperty(key);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }



}
