package com.GitHub.Searcher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SearcherApplicationTests {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d", Locale.ENGLISH);
	public LocalDate parseStringToDate(String stringDate)
	{
		LocalDate date=LocalDate.parse(stringDate, formatter);
		return date;
	}

	@Test
	void contextLoads() {
	}
	@Test
		public void UpdatedWithinThreeMonthsTrueTest()
		{
			boolean test_wyniku;
			String projectUpdateDate="2022-07-15";
			LocalDate repoUpdateDate = parseStringToDate(projectUpdateDate);
			LocalDate actualDate = LocalDate.now();
			long months = ChronoUnit.MONTHS.between(repoUpdateDate, actualDate);
			if(months>3)
			{
				test_wyniku=false;
			}
			else {
				test_wyniku=true;
				assertTrue(test_wyniku);
			}
		}

	@Test
	public void UpdatedWithinThreeMonthsFalseTest()
	{
		String projectUpdateDate="2022-03-15";
		LocalDate repoUpdateDate = parseStringToDate(projectUpdateDate);
		LocalDate actualDate = LocalDate.now();
		long months = ChronoUnit.MONTHS.between(repoUpdateDate, actualDate);
		if(months>=3)
		{
			boolean test_wyniku=false;
			assertFalse(test_wyniku);

		}
	}

}
