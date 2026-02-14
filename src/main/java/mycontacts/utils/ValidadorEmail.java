/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycontacts.utils;

import java.util.regex.Pattern;

public class ValidadorEmail {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern pattern =
            Pattern.compile(EMAIL_REGEX);

    public static boolean validar(String email) {
        if (email == null) return false;
        return pattern.matcher(email).matches();
    }
}
