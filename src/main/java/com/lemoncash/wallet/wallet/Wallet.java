package com.lemoncash.wallet.wallet;

import com.lemoncash.wallet.currency.Currency;
import com.lemoncash.wallet.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount;

    @JoinColumn(name = "currency_id")
    @ManyToOne
    private Currency currency;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

}
