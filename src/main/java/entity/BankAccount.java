package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bank_account")
@NamedQueries(
        @NamedQuery(name = "BankAccount.findAll",query = "SELECT b FROM BankAccount b")
)
public class BankAccount extends ActiveBankAccount implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "amount")
    private Double amount;

    public BankAccount() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        return accountNumber.equals(that.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}
