package com.project.academy.dataProvider;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.project.academy.pojo.Parameter;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Provider {

    private static final String JSON_DATA_FILE = "src/test/java/com/project/academy/resources/data_tests_travelocity.json";

    /**
     * Read Json file and get set of data
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    private static JsonElement readJson(String fileName) throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader(fileName));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");

        return dataSet;
    }

    /**
     * Data provider of duration of video in seconds
     *
     * @return
     * @throws FileNotFoundException
     */
    @DataProvider(name = "dataTravelocity")
    public static Object[][] dataTravelocity() throws FileNotFoundException {

        JsonElement dataSet = readJson(JSON_DATA_FILE);
        List<Parameter> testData = new Gson().fromJson(dataSet, new TypeToken<List<Parameter>>() {
        }.getType());

        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

}
