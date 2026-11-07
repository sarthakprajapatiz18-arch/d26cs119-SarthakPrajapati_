import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

    @Test
    void addingAccountIncreasesCount() {
        BankService service = new BankService();
        service.addAccount(new BankAccount("A101", 5000));

        assertEquals(1, service.getAccountCount());
    }

    @Test
    void invalidDepositThrowsException() {
        BankService service = new BankService();
        service.addAccount(new BankAccount("A101", 5000));

        assertThrows(IllegalArgumentException.class,
                () -> service.deposit("A101", -100));
    }

    @Test
    void missingAccountThrowsException() {
        BankService service = new BankService();

        assertThrows(IllegalArgumentException.class,
                () -> service.deposit("A999", 100));
    }
}