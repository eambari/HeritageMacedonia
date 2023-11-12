package com.heritage.heritagemacedonia.Impl;

public class AttributeFilter implements Filter<String> {
    @Override
    public String execute(String input, String attributeName) {
        int attributeIndex = input.indexOf("\"" + attributeName + "\":");

        // Check if the attribute is present in the JSON string
        if (attributeIndex != -1) {
            // Find the start and end indices of the attribute value
            int startIndex = input.indexOf("\"", attributeIndex + attributeName.length() + 2) + 1;
            int endIndex = input.indexOf("\"", startIndex);

            // Extract the attribute value
            if (startIndex != -1 && endIndex != -1) {
                return input.substring(startIndex, endIndex);
            }
        }

        // Return an empty string if the attribute is not present
        return "";
    }
}
