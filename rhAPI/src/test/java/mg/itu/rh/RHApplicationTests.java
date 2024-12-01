package mg.itu.rh;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

@SpringBootTest
class RHApplicationTests {

	@Test
	void contextLoads() {
		Period period=Period.between(LocalDate.of(2023,8,12),LocalDate.now());
		//Duration duration=Duration.between(LocalDate.of(2023,8,12),LocalDate.now());
		System.out.println(period.getYears()+" "+period.getMonths()+" "+period.getDays());
		//System.out.println(duration.toYears()+" "+period.getMonths()+" "+period.getDays());
	}

}
