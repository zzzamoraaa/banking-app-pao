package Accounts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private final String fromIBAN;
    private final String toIBAN;
    private final float amount;
    private final String details;
    private final Date transactionDate;

    public Transaction(String fromIBAN, String toIBAN, float amount, String details) throws Exception{
        if (amount <= 0)
            throw new Exception("Invalid amount");

        this.fromIBAN = fromIBAN;
        this.toIBAN = toIBAN;
        this.amount = amount;
        this.details = details;
        this.transactionDate = new Date();
    }

    public Transaction(ResultSet in) throws SQLException {
        this.fromIBAN = in.getString("fromIBAN");
        this.toIBAN = in.getString("toIBAN");
        this.amount = in.getFloat("amount");
        this.details = in.getString("details");
        this.transactionDate = in.getDate("transactionDate");
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + fromIBAN +
                ", to=" + toIBAN +
                ", amount=" + amount +
                ", details='" + details + '\'' +
                ", transactionDate=" + (new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss")).format(transactionDate) +
                '}';
    }

    public String toCSV() {
        return fromIBAN +
                "," + toIBAN +
                "," + amount +
                "," + details +
                "," + (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(transactionDate);
    }

    public String getFromIBAN()
    {
        return fromIBAN;
    }
    public String getToIBAN()
    {
        return toIBAN;
    }
    public float getAmount()
    {
        return amount;
    }
    public String getDetails()
    {
        return details;
    }
    public Date getTransactionDate()
    {
        return transactionDate;
    }
}
