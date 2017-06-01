package me.antonle.alfastart.common.entity;

import lombok.Data;
import me.antonle.alfastart.common.domain.Ccy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOUNTS")
@Data
public class Account implements Serializable {

    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue
    private long accountID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "CCY")
    @Enumerated(EnumType.STRING)
    private Ccy ccy;

    public Account() {
    }
}
