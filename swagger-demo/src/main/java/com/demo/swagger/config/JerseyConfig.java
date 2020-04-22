package com.demo.swagger.config;


import com.demo.swagger.rest.UserRest;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.AcceptHeaderApiListingResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {

        // 项目服务
        register(UserRest.class);

        // swagger服务
        this.register(ApiListingResource.class);
        this.register(AcceptHeaderApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-example");
        config.setTitle("Demo api document");
        config.setVersion("v1");
        config.setContact("abc");
        config.setSchemes(new String[]{"http", "https"});
        config.setBasePath("/rest");
        config.setResourcePackage("com.demo.swagger.rest");
        config.setPrettyPrint(true);
        config.setScan(true);

    }

}
