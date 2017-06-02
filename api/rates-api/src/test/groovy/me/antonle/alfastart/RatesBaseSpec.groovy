package me.antonle.alfastart

import me.antonle.alfastart.config.RatesTestConfig
import me.antonle.alfastart.rates.RatesApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@ContextConfiguration(classes = [RatesApplication.class, RatesTestConfig.class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RatesBaseSpec extends Specification {

    @Value('${local.server.port}')
    int port

    @Autowired
    WebApplicationContext context

    def "Rates API boot up without errors"() {
        expect: "web application context exists"
        context != null
    }
}

