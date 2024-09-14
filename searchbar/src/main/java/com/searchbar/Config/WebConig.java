package com.searchbar.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConig {
    public WebClient.Builder webClientBuilder()
    {
        return WebClient.builder();
    }
}
