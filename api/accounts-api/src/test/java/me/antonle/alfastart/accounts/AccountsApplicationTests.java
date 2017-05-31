package me.antonle.alfastart.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan({
		"me.antonle.alfastart.common.entity",
		"me.antonle.alfastart.accounts.*"
})
public class AccountsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
