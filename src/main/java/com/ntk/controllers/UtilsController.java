package com.ntk.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class UtilsController {
    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
    }
    public static String decodeBase64(String str){
        return new String(DatatypeConverter.parseBase64Binary(str));
    }
    public static String encodeBase64(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static List<List<String>> splitStr(String str, String... sep){
        List<List<String>> result = new ArrayList<>();
        if(sep.length==1){
            result.add(Arrays.stream(str.split(sep[0])).toList());
            return result;
        }
        if(sep.length>=2) {
            Arrays.stream( str.split(sep[0])).toList().forEach(e-> result.add(
                    Arrays.stream(e.split(sep[1])).toList()));

            return result;
        }

        return null;
    }
}
