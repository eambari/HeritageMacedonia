package com.heritage.heritagemacedonia.Impl;

public class UppercaseFilter implements Filter<String>{
    @Override
    public String execute(String input, String attributeName) {
        if (input == ""){
            return "Unnamed";
        }
        String s1 = input.substring(0, 1).toUpperCase();
        String s2 = input.substring(1);
        String res = input.substring(0, 1).toUpperCase() + input.substring(1);
        return res;
    }
}
