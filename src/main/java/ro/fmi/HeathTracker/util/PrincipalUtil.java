package ro.fmi.HeathTracker.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ro.fmi.HeathTracker.security.UserDetailsImpl;

public final class PrincipalUtil {

    private PrincipalUtil(){}

    public static String getPrincipal(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetailsImpl) principal).getEmail();
        } else {
            return principal.toString();
        }
    }
}
