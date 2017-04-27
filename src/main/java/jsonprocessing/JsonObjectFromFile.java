package jsonprocessing;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by dovan on 4/27/2017.
 */
public class JsonObjectFromFile {
    static String json = "{\n" +
            "  \"array\": [\n" +
            "    1,\n" +
            "    2,\n" +
            "    3\n" +
            "  ],\n" +
            "  \"boolean\": true,\n" +
            "  \"null\": null,\n" +
            "  \"number\": 123,\n" +
            "  \"object\": {\n" +
            "    \"a\": \"b\",\n" +
            "    \"c\": \"d\",\n" +
            "    \"e\": \"f\"\n" +
            "  },\n" +
            "  \"string\": \"Hello World\"\n" +
            "}";

    static File createFile(String fileName, String content) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(content.getBytes());
        }
        return file;
    }


    public static void main(String[] args) throws IOException {
        File jsonFile = createFile("test", json);
        try (FileReader reader = new FileReader(jsonFile)) {
            JsonObject jsonObject = Json.parse(reader).asObject();
            System.out.println("String: " + jsonObject.get("string").asString());
            System.out.println("Number: " + jsonObject.get("number").asInt());
            JsonArray array = jsonObject.get("array").asArray();
            System.out.println("Array: ");
            array.forEach(each -> System.out.print(each.asInt() + " "));
            JsonObject object = jsonObject.get("object").asObject();
            System.out.println("\nObject: " + object);

        }
    }
}
