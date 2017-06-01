package me.antonle.alfastart

import me.antonle.alfastart.accounts.AccountsApplication
import me.antonle.alfastart.config.SpockTestConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@ContextConfiguration(classes = [AccountsApplication.class, SpockTestConfig.class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootBaseSpec extends Specification {

    @Value('${local.server.port}')
    int port

    @Autowired
    WebApplicationContext context

    def "Application boot up without errors"() {
        expect: "web application context exists"
        context != null
    }
}
