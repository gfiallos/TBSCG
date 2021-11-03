package com.gf.notesapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class DocumentationConfig {
  /**
   * The Description.
   */
  @Value("${info.app.description}")
  private String description;
  /**
   * The Name.
   */
  @Value("${info.app.name}")
  private String name;

  /**
   * Custom open api open api.
   *
   * @return the open api
   */
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title(this.name)
        .description(this.description));
  }
}
