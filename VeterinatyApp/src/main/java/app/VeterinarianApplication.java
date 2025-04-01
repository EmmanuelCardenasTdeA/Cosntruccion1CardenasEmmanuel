package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.adapters.inputs.LoginInput;

@SpringBootApplication
public class VeterinarianApplication implements CommandLineRunner{

	@Autowired
	private LoginInput loginInput;
	@Override
	public void run(String... args) throws Exception {
		loginInput.menu();
	}
		
		public static void main(String[] args) {
		SpringApplication.run(VeterinarianApplication.class, args);
	}



}
