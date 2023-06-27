package io.incondensable.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * @author abbas
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Invoice APIs",
                version = "0.0.1",
                description = "This is documentation of Invoice APIs"
        )
)
public class OpenApiConfiguration {
}
