package com.distribuida;

import io.helidon.config.Config;
import io.helidon.openapi.OpenAPISupport;
import io.helidon.webserver.Routing;

public class Servidor {
    public static void main(String[] args) {
        Config config = Config.create();

        Routing.builder()
                       .register(OpenAPISupport.create(config))
                       .build();
        io.helidon.microprofile.cdi.Main.main(args);
    }
}
