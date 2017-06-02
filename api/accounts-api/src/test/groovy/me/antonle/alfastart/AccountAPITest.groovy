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
class AccountAPITest extends AccountsBaseSpec {

    @Resource(name = "accountAPI")
    AccountAPI accountAPI

    @Shared
    private Account account1
    @Shared
    private Account account2


    def "Should create account `#accountName` successfully"() {
        when:
        account1 = accountAPI.create(accountName, ccy)

        then:
        this.account1 != null
        this.account1.accountID > 0
        this.account1.name == accountName
        this.account1.balance == 0
        this.account1.getCcy() == ccy

        where:
        accountName        | ccy
        "Test RUB account" | Ccy.RUB
        "Test USD account" | Ccy.USD
        "Test EUR account" | Ccy.EUR

    }

    def "Should get account successfully"() {
        when:
        def account = accountAPI.get(account1.getAccountID())

        then:
        account != null
        account.accountID == this.account1.accountID
        account.balance == this.account1.balance
        account.ccy == this.account1.ccy

    }

    def "Should deposit #depositAmount successfully"() {
        when:
        def account = accountAPI.deposit(account1.getAccountID(), depositAmount)

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
        def account = accountAPI.withdraw(account1.getAccountID(), withdrawAmount)

        then:
        account != null
        account.balance == accountAmount

        where:
        withdrawAmount || accountAmount
        10             || 110
        15             || 95
        25             || 70
    }

    def "Should create another account `#accountName` with same ccy successfully"() {
        when:
        account2 = accountAPI.create(accountName, ccy)

        then:
        account2 != null
        account2.accountID > 0
        account2.name == accountName
        account2.balance == 0
        account2.getCcy() == account1.getCcy()

        where:
        accountName          | ccy
        "Test EUR account 2" | Ccy.EUR
    }

    def "Should transfer #amount from one account to another successfully"() {
        when:
        account1 = accountAPI.transfer(account1.getAccountID(), account2.getAccountID(), amount)
        account2 = accountAPI.get(account2.getAccountID())

        then:
        account1.balance == accountAmount1
        account2.balance == accountAmount2

        where:
        amount || accountAmount1 | accountAmount2
        20     || 50             | 20
        10     || 40             | 30


    }
}