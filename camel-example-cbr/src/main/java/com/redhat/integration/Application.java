/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.redhat.integration;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spi.RestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
@PropertySource("classpath:swagger.properties")
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * CORS configuration Start
     */

    private static final String CAMEL_URL_MAPPING = "/jobs-service/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration =
                new ServletRegistrationBean(new CORSServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);

        return registration;
    }


    private static Class<Application> applicationClass = Application.class;

    private class CORSServlet extends CamelHttpTransportServlet {
        @Override
        protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");
            String origin = request.getHeader("Origin");
            if (origin == null || origin.isEmpty()) {
                origin = "*";
            }

            if (authHeader == null || authHeader.isEmpty()) {
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Methods", RestConfiguration.CORS_ACCESS_CONTROL_ALLOW_METHODS);
                response.setHeader("Access-Control-Allow-Headers", "Authorization, " + RestConfiguration.CORS_ACCESS_CONTROL_ALLOW_HEADERS);
                response.setHeader("Access-Control-Max-Age", RestConfiguration.CORS_ACCESS_CONTROL_MAX_AGE);
                response.setHeader("Access-Control-Allow-Credentials", "true");
            }

            super.doService(request, response);
        }
    }

    /**
     * CORS configuration END
     */

}