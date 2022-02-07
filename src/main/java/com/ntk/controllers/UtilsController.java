package com.ntk.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UtilsController {
    public static String parseUTF8(String item) {
        byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);

    }
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
}
