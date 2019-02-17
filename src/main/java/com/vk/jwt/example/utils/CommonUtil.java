package com.vk.jwt.example.utils;

public class CommonUtil {
	
	public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

}
