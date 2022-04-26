package es.cursojee.jurassicpark.services.impl;

import java.io.InputStream;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractServiceTestCase {

	@Autowired
	protected DataSource dataSource;

	@BeforeEach
	public void setUp() throws Exception {
		DataSourceDatabaseTester ds = new DataSourceDatabaseTester(dataSource);
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dbunit/test/data-test.xml");
		ReplacementDataSet dataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(in));
		dataSet.addReplacementObject("[NULL]", null);
		IDatabaseConnection connection = ds.getConnection();
		connection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		connection.close();
		log.info("Datos de prueba para el test cargado");
	}

}
