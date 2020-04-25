package kz.almat.dlapi.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import kz.almat.dlapi.DlApiApplicationTests;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Almat on 21.03.2020
 */

@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@Transactional
public abstract class AbstractRepositoryIT<E extends Object> extends DlApiApplicationTests {

    private final Class<E> clazz;

    @SuppressWarnings("unchecked")
    public AbstractRepositoryIT() {
        clazz = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractRepositoryIT.class);
    }

    private final DatabaseOperation SEQUENCE_RESETTER_ORACLE = new DatabaseOperation() {

        @Override

        public void execute(IDatabaseConnection connection, IDataSet dataSet)
                throws DatabaseUnitException, SQLException {

            Table declaredAnnotation = clazz.getDeclaredAnnotation(Table.class);
            String name = declaredAnnotation.name();

            Statement statement = connection.getConnection().createStatement();
            statement.execute("drop sequence " + name + "_id_seq if exists");
            statement.execute("create sequence " + name + "_id_seq start with " + 1);
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
