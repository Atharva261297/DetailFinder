package com.atharva.detailfinder.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class ArrayClassConverter<T> {

    public List<T> arrayToClass(List<List<String>> input, Class<T> classType) {
        List<String> headers = input.get(0);

        List<T> output = new ArrayList<T>();

        for (int rowCounter=1 ; rowCounter<input.size() ; rowCounter++) {
            JsonObject jsonObject = new JsonObject();

            for (int columnCounter=0 ; columnCounter<headers.size() ; columnCounter++) {
                jsonObject.add(headers.get(columnCounter),new JsonPrimitive(input.get(rowCounter).get(columnCounter)));
            }

            output.add(new Gson().fromJson(jsonObject, classType));
        }

        return output;
    }
}
