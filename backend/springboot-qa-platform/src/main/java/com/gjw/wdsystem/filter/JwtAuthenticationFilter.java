package com.gjw.wdsystem.filter;


import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.gjw.wdsystem.dto.security.UserDetailsImpl;
import com.gjw.wdsystem.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * Adding a Custom Filter to the Filter Chain.
 *
 * 这个过滤器主要是用来拦截需要token的url，并验证token
 *
 * 您可以从 OncePerRequestFilter 进行扩展，而不是实现 Filter (不过也不是不可以实现Filter)。
 *      它是每个请求仅调用一次的过滤器的基类，并提供带有 HttpServletRequest 和 HttpServletResponse 参数的 doFilterInternal 方法。
 */
@Data
@ConfigurationProperties(prefix = "jwt-authentication-filter")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private String[] notInterceptUrls; // 不需要被JwtAuthenticationFilter拦截的url,也就是可以直接不用验证token的url

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        for(String url : notInterceptUrls){
            if(requestURL.toString().endsWith(url)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(Objects.isNull(header) || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return ;
        }

        String token = header.substring("Bearer ".length()); // (Bearer null)

        // verify that the token
        if(Objects.nonNull(token) && !token.equals("null")){
            try{
                Claim usernameClaim = JwtUtil.parseToken(token);
                String username = usernameClaim.asString();

                if (Objects.isNull(username))
                    throw new MissingClaimException("您的令牌异常,请重新登录!");

                UserDetailsImpl customUserDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

                if(Objects.isNull(customUserDetails))
                    throw new IncorrectClaimException("您的令牌异常,请重新登录!","username",usernameClaim);

                /**
                 * We start by creating an empty SecurityContext.
                 * You should create a new SecurityContext instance instead of using SecurityContextHolder.getContext().setAuthentication(authentication) to avoid race conditions across multiple threads.
                 */
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                customUserDetails.setPassword(null);
                securityContext.setAuthentication(UsernamePasswordAuthenticationToken.authenticated(customUserDetails,null,customUserDetails.getAuthorities()));
                SecurityContextHolder.setContext(securityContext);
            }catch (TokenExpiredException ex){
                returnResponse(response,"登录已过期,请重新登录!");
                return ;
            } catch (JWTVerificationException ex){
                returnResponse(response,"身份异常,请重新登录!");
                return ;
            }
        }

        filterChain.doFilter(request,response);
    }
    private void returnResponse(HttpServletResponse response,String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(String.format("{ \"message\": \"%s\" }",message));
    }
}
