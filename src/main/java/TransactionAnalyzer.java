package PACKAGE_NAME;public class TransactionAnalyzer {
}
public class TransactionAnalyzer {
}
import java.util.List;

public class TransactionAnalyzer {
    private List<Transaction> transactions;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Метод для розрахунку загального балансу
    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }
}
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionAnalyzer {
    private final List<Transaction> transactions;
    private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    // Метод для підрахунку транзакцій за конкретний місяць і рік
    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }
}
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private List<Transaction> transactions;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Метод для визначення 10 найбільших витрат
    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }
}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        // Виклик методу, який потрібно протестувати
        double result = analyzer.calculateTotalBalance();

        // Перевірка результату
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        // Виклик методу countTransactionsByMonth
        int countFeb = analyzer.countTransactionsByMonth("02-2023");
        int countMar = analyzer.countTransactionsByMonth("03-2023");

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }
}
public class TransactionAnalyzer {
    private List<Transaction> transactions;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Метод для визначення найбільших витрат за період
    public Transaction findLargestExpense(String monthYear) {
        return transactions.stream()
                .filter(t -> t.getDate().contains(monthYear) && t.getAmount() < 0)
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    // Метод для визначення найменших витрат за період
    public Transaction findSmallestExpense(String monthYear) {
        return transactions.stream()
                .filter(t -> t.getDate().contains(monthYear) && t.getAmount() < 0)
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }
}
