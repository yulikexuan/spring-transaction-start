//: jdbc.repository.AccountRepository.java


package jdbc.repository;


import jdbc.domain.model.Account;
import jdbc.service.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AccountRepository {

    String SQL_FIND_BY_ID_TEMPLATE = "SELECT * FROM account WHERE id = ?";
    String SQL_FIND_ALL_TEMPLATE = "SELECT * FROM account";
    String SQL_CHANGE_ACCOUNT_TEMPLATE = "UPDATE account SET amount = ? WHERE id = ?";

    List<Account> findAll();

    Account findById(int id);

    void change(long id, long amount);

}

@Repository
@RequiredArgsConstructor
class AccountRepositoryImpl implements AccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Account> accountRowMapper;

    @Override
    public List<Account> findAll() {
        return this.jdbcTemplate.query(SQL_FIND_ALL_TEMPLATE, this.accountRowMapper);
    }

    @Override
    public Account findById(int id) {

        Account account = null;

        try {
            account = jdbcTemplate.queryForObject(
                    SQL_FIND_BY_ID_TEMPLATE, this.accountRowMapper, id);
        } catch (DataAccessException e) {
            throw new AccountNotFoundException(id);
        }

        return account;
    }

    @Override
    public void change(long id, long amount) {
        this.jdbcTemplate.update(SQL_CHANGE_ACCOUNT_TEMPLATE, amount, id);
    }

}///:~