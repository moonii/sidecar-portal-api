package org.kpaas.sidecar.api.login;

import org.container.platform.api.common.model.CommonStatusCode;
import org.container.platform.api.login.support.PortalGrantedAuthority;
import org.kpaas.sidecar.api.common.Constants;
import org.kpaas.sidecar.api.exception.SidecarException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class SidecarAuthenticationFilter extends org.container.platform.api.login.CustomJwtAuthenticationFilter{
    public SidecarAuthenticationFilter(AuthUtil authUtil) {
        super(authUtil);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String uri =  request.getRequestURI();
        if (uri.contains(Constants.URI_SIDECAR_API_PREFIX)) {
            String jwtToken = jwtTokenUtil.extractJwtFromRequest(request);
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> list = (List<GrantedAuthority>) authentication.getAuthorities();

            PortalGrantedAuthority aAuthority = null;
            String clusterId = null;
            for (GrantedAuthority authority : list) {

                if(authority instanceof PortalGrantedAuthority ){
                    PortalGrantedAuthority portalAuthority = (PortalGrantedAuthority)authority;

                    if (portalAuthority.equals(portalAuthority.getId(), Constants.ContextType.CLUSTER.name())){
                        if (clusterId == null)
                            clusterId = portalAuthority.getId();
                    }

                    if (Constants.URI_SIDECAR_API_PREFIX.equals(portalAuthority.getId())){
                        if (aAuthority == null)
                            aAuthority = (PortalGrantedAuthority)authority;
                    }
                }
            }
            boolean validateI = ((AuthUtil) jwtTokenUtil).whoami();
            if (!validateI){
                throw new SidecarException(org.container.platform.api.common.Constants.RESULT_STATUS_FAIL,"failed to get identity", HttpStatus.UNAUTHORIZED.value(), CommonStatusCode.UNAUTHORIZED.getMsg());
            }
        }
        chain.doFilter(request, response);
    }
}
