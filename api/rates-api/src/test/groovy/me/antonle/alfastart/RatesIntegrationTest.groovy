package me.antonle.alfastart

import groovy.json.JsonSlurper
import me.antonle.alfastart.common.domain.Ccy
import me.antonle.alfastart.rates.entity.RateResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import spock.lang.Title
import spock.lang.Unroll

@Title("Integration via REST with http://fixer.io/")
class RatesIntegrationTest extends RatesBaseSpec {

    private JsonSlurper jsonSlurper = new JsonSlurper()

    @Autowired
    private RestTemplate restTemplate

    def "Should return latest rates"() {
        when:
        def response = restTemplate.getForEntity("http://api.fixer.io/latest", String.class)

        then:
        response.statusCode == HttpStatus.OK

        def body = jsonSlurper.parseText(response.body)
        body instanceof Map
        body.base == 'EUR'
        body.rates instanceof Map

    }

    @Unroll
    def "Should return rates for #baseCcy"() {
        when:
        def response = restTemplate.getForEntity("http://api.fixer.io/latest?base=$baseCcy", String.class)

        then:
        response.statusCode == HttpStatus.OK

        def body = jsonSlurper.parseText(response.body)
        body instanceof Map
        body.base == baseCcy.toString()
        body.rates instanceof Map

        where:
        baseCcy << [Ccy.EUR, Ccy.USD, Ccy.RUB]

    }

    @Unroll
    def "Should return rates for #baseCcy and #conversionCcy"() {
        when:
        def response = restTemplate.getForEntity("http://api.fixer.io/latest?base=$baseCcy&symbols=$conversionCcy", String.class)

        then:
        response.statusCode == HttpStatus.OK

        def body = jsonSlurper.parseText(response.body)
        println(body)
        body instanceof Map
        body.base == baseCcy.toString()
        body.rates instanceof Map
        body.rates.size() == 1
        body.rates.get(conversionCcy.toString()) != null
        body.rates.get(conversionCcy.toString()) instanceof BigDecimal

        where:
        baseCcy | conversionCcy
        Ccy.EUR | Ccy.USD
        Ccy.RUB | Ccy.EUR
        Ccy.USD | Ccy.RUB

    }

    @Unroll
    def "Should return rates for #baseCcy and #conversionCcy with mapped response entity"() {
        when:
        def response = restTemplate.getForEntity("http://api.fixer.io/latest?base=$baseCcy&symbols=$conversionCcy", RateResponse.class)

        then:
        response.statusCode == HttpStatus.OK

        def body = response.body
        println(body)
        body.base == baseCcy.toString()
        body.rates instanceof Map
        body.rates.size() == 1
        body.rates.get(conversionCcy.toString()) != null
        body.rates.get(conversionCcy.toString()) instanceof BigDecimal

        where:
        baseCcy | conversionCcy
        Ccy.EUR | Ccy.USD
        Ccy.RUB | Ccy.EUR
        Ccy.USD | Ccy.RUB

    }


}