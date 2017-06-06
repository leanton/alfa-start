package me.antonle.alfastart

import me.antonle.alfastart.common.api.RateAPI
import me.antonle.alfastart.common.domain.Ccy
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Title
import spock.lang.Unroll

@Title("Rate API test")
class RatesAPITest extends RatesBaseSpec {

    @Autowired
    private RateAPI rateAPI

    @Unroll
    def "Should return rate from #fromCcy to #toCcy"() {
        when:
        def rate = rateAPI.getRate(fromCcy, toCcy)

        then:
        rate != null
        rate instanceof BigDecimal

        where:
        fromCcy | toCcy
        Ccy.EUR | Ccy.USD
        Ccy.RUB | Ccy.EUR
        Ccy.USD | Ccy.RUB
    }
}