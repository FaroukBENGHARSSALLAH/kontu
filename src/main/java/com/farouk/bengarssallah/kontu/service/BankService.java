import com.farouk.bengarssallah.kontu.domain.Transaction;
import java.util.List;
import java.util.Collection;
import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Client;

public interface BankService
{
    Client findClientByReference(final Long p0);
    
    Account findAccountByReference(final Long p0);
    
    Collection<Client> findAllClient();
    
    void addClient(final Client p0);
    
    void editClient(final Client p0);
    
    void deleteClient(final Client p0);
    
    void addAccount(final Account p0);
    
    void editAccount(final Account p0);
    
    void deleteAccount(final Account p0);
    
    void pay(final Long p0, final double p1);
    
    void withdraw(final Long p0, final double p1);
    
    void trasnfert(final Long p0, final Long p1, final double p2);
    
    List<Account> accountsList(final Long p0);
    
    List<Transaction> transactionsList(final Long p0);
}
