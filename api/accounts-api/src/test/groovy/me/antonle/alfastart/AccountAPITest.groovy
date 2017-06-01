package me.antonle.alfastart

import me.antonle.alfastart.common.api.AccountAPI
import me.antonle.alfastart.common.domain.Ccy
import me.antonle.alfastart.common.entity.Account
import spock.lang.Shared
import spock.lang.Stepwise

import javax.annotation.Resource

@Stepwise
class AccountAPITest extends SpringBootBaseSpec {

    @Resource(name = "accountAPI")
    AccountAPI accountAPI

    @Shared
    private Account account

    def "Should create account successfully"() {
        when:
        account = accountAPI.create(accountName, ccy)

        then:
        this.account != null
        this.account.accountID > 0
        this.account.name == accountName
        this.account.balance == 0
        this.account.getCcy() == ccy

        where:
        accountName        | ccy
        "Test RUB account" | Ccy.RUB
        "Test USD account" | Ccy.USD
        "Test EUR account" | Ccy.EUR

    }

    def "Should get account successfully"() {
        when:
        def account = accountAPI.get(account.getAccountID())

        then:
        account != null
        account.accountID > 0
        account.balance >= 0
        account.ccy == Ccy.EUR

    }

    def "Should deposit money successfully"() {
        when:
        def account = accountAPI.deposit(account.getAccountID(), BigDecimal.TEN)

        then:
        account != null
        account.balance == BigDecimal.TEN
    }

}