package in.aorder.qr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("in.aorder.qr")
public class QrServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrServiceApplication.class, args);
	}

}
