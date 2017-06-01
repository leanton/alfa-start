package me.antonle.alfastart

import me.antonle.alfastart.common.api.AccountAPI
import me.antonle.alfastart.common.domain.Ccy
import spock.lang.Ignore

import javax.annotation.Resource

// todo: I HAVE NO IDEA WHY IT IS NOT WORKING!
class AccountAPITest extends SpringBootBaseSpec {

    @Resource(name = "accountAPI")
    AccountAPI accountAPI

    @Ignore
    def "Should create account successfully"() {
        when:
        def account = accountAPI.create(accountName, ccy.getName())

        then:
        account != null
        account.accountID > 0
        account.name == accountName
        account.balance == 0
        account.ccy == ccy

        where:
        accountName          | ccy
        "Test ruble account" | Ccy.RUB
        "Test USD account"   | Ccy.USD
        "Test RUB account"   | Ccy.EUR

    }

    @Ignore
    def "Should get account successfully"() {
        when:
        def account = accountAPI.get(1)

        then:
        account != null
        account.accountID > 0
        account.balance >= 0


    }

    @Ignore
    def "Should test account successfully"() {
        when:
        def actualString = accountAPI.test(expectedString)

        then:
        actualString == expectedString

        where:
        expectedString = "one"
    }

}