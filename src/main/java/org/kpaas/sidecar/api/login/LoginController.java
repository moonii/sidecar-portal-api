package org.kpaas.sidecar.api.login;


import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.container.platform.api.common.Constants;
import org.container.platform.api.common.MessageConstant;
import org.container.platform.api.common.model.CommonStatusCode;
import org.container.platform.api.common.model.Params;
import org.container.platform.api.common.model.ResultStatus;
import org.container.platform.api.config.NoAuth;
import org.container.platform.api.login.AuthenticationResponse;
import org.container.platform.api.login.CustomUserDetailsService;
//import org.container.platform.api.login.JwtUtil;
import org.container.platform.api.login.support.PortalGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Login Controller 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2020.09.28
 */
@Api(value = "LoginController v1")
@RestController("sLoginController")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private org.kpaas.sidecar.api.organizations.OrganizationsService organizationsService;

    @Autowired
    @Qualifier("authUtil")
    private AuthUtil jwtTokenUtil;

    @ApiOperation(value = "Org 목록조회(Get Org List)", nickname = "getOrganizationsList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "request parameters", required = true, dataType = "object", paramType = "body")
    })
    @GetMapping("/sidecar/orgs")
    @ResponseBody
    public Object getOrganizationsList() {

        org.cloudfoundry.client.v3.organizations.Organization org ;
        Object result ;
        try {

            result = organizationsService.getROrganizationsList( AuthUtil.sidecarAuth() );

        } catch (Exception e) {
            return new ResultStatus(Constants.RESULT_STATUS_FAIL, MessageConstant.LOGIN_FAIL.getMsg(),
                    CommonStatusCode.UNAUTHORIZED.getCode(), e.getMessage());
        }

        return result;
    }

}