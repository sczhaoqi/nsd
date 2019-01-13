package com.sczhaoqi.nwsd.handler;

import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class TimeHandler
{
    private final Flux<String> timeStream;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:m:s S");

    public TimeHandler()
    {
        // 周期性生成值并返回
        this.timeStream = Flux.interval(Duration.ofMillis(100))
                .map(l -> String.format("当前时间 %s",dateFormat.format(System.currentTimeMillis())));
    }

    public Mono<ServerResponse> now(ServerRequest request)
    {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", "charset=utf-8")
                .body(Flux.just(String.format("当前时间 %s", dateFormat.format(System.currentTimeMillis()))), String.class)
                .log();
    }

    public Mono<ServerResponse> time(ServerRequest request)
    {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .header("Content-Type", "charset=utf-8")
                .body(Flux.interval(Duration.ofSeconds(1)).
                                map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }
}
