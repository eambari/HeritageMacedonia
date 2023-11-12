package com.heritage.heritagemacedonia.Impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AttributeFilter implements Filter<String> {
    @Override
    public String execute(String input, String attributeName) {
        // Parse the input JSON string
        JsonParser parser = new JsonParser();
        JsonObject attributes = parser.parse(input).getAsJsonObject();

        // Check if the specified attribute is present
        if (attributes.has(attributeName)) {
            return attributes.get(attributeName).getAsString();
        }

        // Return an empty string if the attribute is not present
        return "";
    }
}
