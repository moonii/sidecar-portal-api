package org.kpaas.sidecar.api.login;

import org.container.platform.api.accessInfo.AccessTokenService;
import org.container.platform.api.common.CommonUtils;
import org.container.platform.api.common.model.Params;
import org.container.platform.api.login.CustomAuthenticationProvider;
import org.container.platform.api.login.CustomUserDetailsService;
import org.container.platform.api.users.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Sidecar Authentication Provider 클래스
 *
 * @author sidecar
 * @version 1.0
 * @since 2024.02.21
 */
@Component("sidecarAuthenticationProvider")
public class SidecarAuthenticationProvider extends CustomAuthenticationProvider {
    //private CustomUserDetailsService customUserDetailsService;
    //private UsersService usersService;
    //private AccessTokenService accessTokenService;

    public SidecarAuthenticationProvider(CustomUserDetailsService customUserDetailsService, UsersService usersService, AccessTokenService accessTokenService) {
        super(customUserDetailsService, usersService, accessTokenService);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SidecarAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Authentication result = super.authenticate(authentication);
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(null, null, null);
        result.setDetails(authentication.getDetails());

        LOGGER.info("authenticate END, result : " + CommonUtils.loggerReplace(result.toString()));
        return result;
    }
}

