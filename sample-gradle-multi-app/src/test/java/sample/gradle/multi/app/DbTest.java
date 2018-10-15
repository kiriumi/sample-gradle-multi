package sample.gradle.multi.app;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DbTest {

    protected static final String TEST_RESOURCES_PATH = "./src/test/resources/";
    protected static final String DBUNIT_SQL_PATH = TEST_RESOURCES_PATH + "dbunit/";
    protected static final File DB_FILE = new File(
            new File("").getAbsoluteFile().getParent(), "/master/database/sample01.sqlite");

    private static IDatabaseTester databaseTester;
    private static IDatabaseConnection connection;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        setupConnection();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test() throws Exception {

        // テーブルに事前データを投入
        IDataSet before = new FlatXmlDataSetBuilder()
                .build(new File(DBUNIT_SQL_PATH + "before.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), before);

        // 期待値のテーブル情報を取得
        IDataSet expected = new FlatXmlDataSetBuilder()
                .build(new File(DBUNIT_SQL_PATH + "expected.xml"));
        ITable expectedTable = expected.getTable("app_db2");

        // 実際の値のテーブル情報を取得
        IDataSet actual = getConnection().createDataSet();
        ITable actualTable = actual.getTable("app_db2");

        // 比較
        Assertion.assertEquals(expectedTable, actualTable);

        // クリーン
        DatabaseOperation.DELETE_ALL.execute(getConnection(), before);
        DatabaseOperation.DELETE_ALL.execute(getConnection(), expected);
    }

    /**
     * DB接続を行う（DBUnit用）
     *
     * @throws ClassNotFoundException
     * @throws Exception
     */
    private static void setupConnection() throws ClassNotFoundException, Exception {

        databaseTester = new JdbcDatabaseTester(
                "org.sqlite.JDBC",
                "jdbc:sqlite:" + DB_FILE.getAbsolutePath(),
                "",
                "",
                "");

        connection = databaseTester.getConnection();
    }

    /**
     * DBコネクションを取得する
     *
     * @return DBコネクション
     */
    protected IDatabaseConnection getConnection() {
        return connection;

    }
}
