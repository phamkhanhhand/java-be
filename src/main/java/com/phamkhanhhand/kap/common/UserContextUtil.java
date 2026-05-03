package com.phamkhanhhand.kap.common;

import com.phamkhanhhand.kap.security.DataUserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContextUtil {
    public static DataUserContext getCurrentUserContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof CustomUserDetails) {
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        if (auth != null){
            return new DataUserContext(
                    auth.getPrincipal().toString()
            );
        }
        return null;
    }


    public static String getCurrentUsername() {


        return "phamha";

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && Objects.nonNull(auth.getPrincipal())){
//            return   auth.getPrincipal().toString();
//        }
//        return null;
    }

}
