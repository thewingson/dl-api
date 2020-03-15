package kz.almat.dlapi.config;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.operation.DatabaseOperationLookup;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.AbstractOperation;
import org.dbunit.operation.CompositeOperation;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Almat on 13.03.2020
 */

public final class OracleDatabaseOperationLookup implements DatabaseOperationLookup {

    private static Map<DatabaseOperation, org.dbunit.operation.DatabaseOperation> OPERATION_LOOKUP;

    static {
        // TODO: migrate to ImmutableMap.
        OPERATION_LOOKUP = new ConcurrentHashMap<>();
        OPERATION_LOOKUP.put(DatabaseOperation.CLEAN_INSERT,
                new CompositeOperation(
                        new TruncateWithRestartSequences(),
                        org.dbunit.operation.DatabaseOperation.INSERT));
    }

    @Override
    public org.dbunit.operation.DatabaseOperation get(com.github.springtestdbunit.annotation.DatabaseOperation databaseOperation) {
        return OPERATION_LOOKUP.get(databaseOperation);
    }

    private static class TruncateWithRestartSequences extends AbstractOperation {
        @Override
        public void execute(final IDatabaseConnection connection, final IDataSet dataSet) throws DatabaseUnitException, SQLException {
            try (final Statement statement = connection.getConnection().createStatement()) {
                final String[] tables = dataSet.getTableNames();
                for (String table : tables) {
                    int startWith = dataSet.getTable(table).getRowCount() + 1;
                    statement.execute("drop sequence " + table + "_id_seq if exists");
                    statement.execute("create sequence " + table + "_id_seq start with " + startWith);
                }
            }
        }
    }
}
