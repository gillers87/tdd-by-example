package guru.springframework;

public class Money implements Expression {

    final int amount;
    private final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String currency() {
        return currency;
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money) object;

        return amount == money.amount
                && this.currency == money.currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {
        //int rate = (currency.equals("CHF") && toCurrency.equals("USD")) ? 2:1;
        return new Money(amount/bank.rate(this.currency, toCurrency), toCurrency);
    }

}
