package com.example.demo;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by pv on 19/2/18.
 */

@EnableRedisHttpSession
public class MySessionConfig extends AbstractHttpSessionApplicationInitializer {
}
