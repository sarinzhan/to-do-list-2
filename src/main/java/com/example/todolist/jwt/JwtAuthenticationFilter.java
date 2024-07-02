package com.example.todolist.jwt;


import com.example.todolist.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtHandler jwtHandler;
    private final UserService userService;


    /**
     * Фильтрует каждый HTTP-запрос для аутентификации пользователя на основе JWT-токена.
     *
     * Этот метод извлекает JWT-токен из запроса, проверяет его, извлекает информацию о пользователе
     * и устанавливает аутентификацию в контексте безопасности, если токен действителен.
     *
     * @param request HTTP-запрос, который обрабатывается
     * @param response HTTP-ответ, который создается
     * @param filterChain цепочка фильтров, частью которой является этот фильтр
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException если возникает ошибка ввода-вывода при обработке запроса
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = this.parseJwt(request);
        if(Objects.nonNull(token) && jwtHandler.validateToken(token)){
            String userName = jwtHandler.getUsernameFromToken(token);
            UserDetails userDetails = this.userService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authToken =
                    UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request,response);
    }


    /**
     * Извлекает JWT-токен из заголовка авторизации HTTP-запроса.
     *
     * Этот метод проверяет наличие заголовка "Authorization" в запросе,
     * проверяет, что заголовок начинается с "Bearer", и возвращает JWT-токен,
     * если условия выполнены. В противном случае возвращает null.
     *
     * @param request HTTP-запрос, из которого извлекается заголовок авторизации
     * @return JWT-токен, если он присутствует и начинается с "Bearer", иначе null
     */
    private String parseJwt(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(Objects.nonNull(authHeader) && authHeader.startsWith("Bearer")){
            return authHeader.substring(7);
        }
        return null;
    }
}
