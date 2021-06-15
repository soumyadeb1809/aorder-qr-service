package in.aorder.qr.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("in.aorder.qr.entity")
@EnableJpaRepositories("in.aorder.qr.repository")
public class JpaConfig {
}
