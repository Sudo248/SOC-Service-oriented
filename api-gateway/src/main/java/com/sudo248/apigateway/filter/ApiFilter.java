package com.sudo248.apigateway.filter;

import com.sudo248.apigateway.utils.TokenUtils;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.common.ErrorMessage;
import com.sudo248.domain.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class ApiFilter implements GatewayFilter {

    private final Logger log = LoggerFactory.getLogger(ApiFilter.class);

    private final TokenUtils tokenUtils;

    private final Predicate<ServerHttpRequest> isApiSecured;
    private final Predicate<ServerHttpRequest> isApiInternal;

    public ApiFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
        final List<String> unsecuredApiEndpoints = List.of(
                "/sign-in",
                "/sign-up",
                "/logout",
                "/generate-otp",
                "/verify-otp",
                "/images",
                "/return-vnpay",
                "/ipn-vnpay"
        );

        final List<String> internalApiEndpoint = List.of("/internal");

        this.isApiSecured = _request -> unsecuredApiEndpoints.stream().noneMatch(endpoint -> _request.getURI().getPath().contains(endpoint));

        this.isApiInternal = _request -> internalApiEndpoint.stream().anyMatch(endpoint -> _request.getURI().getPath().contains(endpoint));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("[" + request.getMethod() + "] " + request.getPath());

        if (isApiInternal.test(request)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, ErrorMessage.INTERNAL_API_NOT_ALLOW);
        }

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey(Constants.AUTHORIZATION)) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            String token = request.getHeaders().getOrEmpty(Constants.AUTHORIZATION).get(0);

            if (token.startsWith(Constants.TOKEN_TYPE)) {
                token = token.replace(Constants.TOKEN_TYPE + " ", "");
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessage.MUST_CONTAIN_TOKEN_TYPE);
            }

            try {
                if (tokenUtils.validateToken(token)) {
                    String userId = tokenUtils.getUserIdFromToken(token);
                    request = request.mutate().header(Constants.HEADER_USER_ID, userId).build();
                    exchange = exchange.mutate().request(request).build();
                }
            } catch (ApiException e) {
                log.error("Authentication failed :  : " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            }
        }
        return chain.filter(exchange);
    }
}
