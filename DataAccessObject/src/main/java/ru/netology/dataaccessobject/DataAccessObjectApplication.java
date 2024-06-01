package ru.netology.dataaccessobject;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.netology.dataaccessobject.repository.MyRepository;


import java.sql.SQLException;

@AllArgsConstructor
@SpringBootApplication
public class DataAccessObjectApplication implements CommandLineRunner {

	private final MyRepository myRepository;

	public static void main(String[] args) {
		SpringApplication.run(DataAccessObjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws SQLException {
		getByCityAndPrint("George");
	}

	public void getByCityAndPrint(String name) {
		var result = myRepository.getProductName(name);
		result.forEach(System.out::println);
	}
}