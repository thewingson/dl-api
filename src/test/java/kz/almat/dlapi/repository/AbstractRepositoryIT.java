package kz.almat.dlapi.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Almat on 21.03.2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@TestPropertySource("/application-test.properties")
@Transactional
public abstract class AbstractRepositoryIT {

    public static final DatabaseOperation SEQUENCE_RESETTER_ORACLE = new DatabaseOperation() {
        @Override
        public void execute(IDatabaseConnection connection, IDataSet dataSet)
                throws DatabaseUnitException, SQLException {
            String[] tables = dataSet.getTableNames();
            Statement statement = connection.getConnection().createStatement();
            for (String table : tables) {
                int startWith = dataSet.getTable(table).getRowCount() + 1;
                statement.execute("drop sequence " + table + "_id_seq if exists");
                statement.execute("create sequence " + table + "_id_seq start with " + startWith);
            }
        }
    };

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        IDatabaseConnection dbUnitConnection = new DatabaseConnection(
                connection);
        IDataSet dataSet = dbUnitConnection.createDataSet();

        DataSourceUtils.releaseConnection(connection, dataSource);
        try {
            DatabaseOperation.REFRESH.execute(dbUnitConnection, dataSet);

        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        SEQUENCE_RESETTER_ORACLE.execute(dbUnitConnection, dataSet);
    }

}
