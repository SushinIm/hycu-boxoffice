package com.hycu.common.config;


import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {

    private static final int CONNECT_TIMEOUT_IN_MILLIS = 10 * 1000;
    private static final int MAX_TOTAL_CONNECTIONS = 50;

    @Value("${api.kobis.url}")
    private String kobisApiUrl;

    @Bean
    public WebClient webClient(HttpClient httpClient, ExchangeStrategies exchangeStrategies) {
        return WebClient.builder()
                .baseUrl(kobisApiUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

    @Bean
    @Primary
    public HttpClient httpClientForWebClient(ConnectionProvider connectionProvider) {
        return HttpClient.create(connectionProvider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_IN_MILLIS);
    }

    @Bean
    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("http-pool")
                .maxConnections(MAX_TOTAL_CONNECTIONS)
                .build();
    }

    @Bean
    public ExchangeStrategies exchangeStrategies() {
        return ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
                .build();
    }
}
