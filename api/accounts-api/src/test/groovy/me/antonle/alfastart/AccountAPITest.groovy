package me.antonle.alfastart

import me.antonle.alfastart.common.api.AccountAPI
import me.antonle.alfastart.common.domain.Ccy
import me.antonle.alfastart.common.entity.Account
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Unroll

import javax.annotation.Resource

@Stepwise
@Unroll
class AccountAPITest extends SpringBootBaseSpec {

    @Resource(name = "accountAPI")
    AccountAPI accountAPI

    @Shared
    private Account account


    def "Should create account #accountName successfully"() {
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
        account.accountID == this.account.accountID
        account.balance == this.account.balance
        account.ccy == this.account.ccy

    }

    def "Should deposit #depositAmount successfully"() {
        when:
        def account = accountAPI.deposit(account.getAccountID(), depositAmount)

        then:
        account != null
        account.balance == accountAmount

        where:
        depositAmount || accountAmount
        10            || 10
        10            || 20
        100           || 120
    }

    def "Should withdraw #withdrawAmount successfully"() {
        when:
        def account = accountAPI.withdraw(account.getAccountID(), withdrawAmount)

        then:
        account != null
        account.balance == accountAmount

        where:
        withdrawAmount || accountAmount
        10             || 110
        15             || 95
        25             || 70
    }

}