package PACKAGE_NAME;public class TransactionReportGenerator {
}
public class TransactionReportGenerator {
}
import java.util.List;

public class TransactionReportGenerator {

    // Виведення загального балансу
    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    // Виведення звіту по кількості транзакцій за місяць
    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    // Виведення звіту по 10 найбільших витратах
    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }
}
