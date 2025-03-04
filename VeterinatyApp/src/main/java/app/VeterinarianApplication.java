package app;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeterinarianApplication implements CommandLineRunner{
	@Autowired
	private ListableBeanFactory beanFactory;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Beans registrados en la aplicación:");
		String[] beanNames = beanFactory.getBeanDefinitionNames();
	for (String beanName : beanNames) {
		System.out.println(beanName);
	}
	}
		
		public static void main(String[] args) {
		SpringApplication.run(VeterinarianApplication.class, args);
	}



}
