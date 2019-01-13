package com.sczhaoqi.nwsd.router;

import com.sczhaoqi.nwsd.handler.TimeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class TimeRouter
{
    @Bean
    public RouterFunction<ServerResponse> route(TimeHandler timeHandler)
    {
        return RouterFunctions
                .route(GET("/time"), timeHandler::time)
                .andRoute(GET("/now"), timeHandler::now);
    }
}
