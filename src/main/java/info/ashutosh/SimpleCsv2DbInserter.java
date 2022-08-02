package info.ashutosh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class SimpleCsv2DbInserter {

	public static void main(String[] args) throws ParseException {
		String jdbcURL = "jdbc:mysql://localhost:3306/interview";
		String username = "root";
		String password = "poop";
		String csvFilePath = "D:\\Git\\Febonachi\\Gitlogin\\SpringRoleBaseLogin\\SpringBootCustomRegisterLogin\\src\\main\\java\\info\\ashutosh\\100000 Sales Records.csv";

		int batchSize = 20;
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(jdbcURL, username, password);
			connection.setAutoCommit(false);

			String sql = "INSERT INTO csv_data (name, age, salary) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
			String lineText = null;

			int count = 0;
			int see = 0;

			lineReader.readLine(); // skip header line

			while ((lineText = lineReader.readLine()) != null) {
				String[] data = lineText.split(",");
				String name = data[0];
				String timestamp = data[1];
				String salary = data[2];

				// name
				statement.setString(1, name);

				// convert String to LocalDate
				DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.parse(timestamp, formatter1);
				int years = Period.between(localDate, LocalDate.now()).getYears();
				statement.setInt(2, years);

				// salary
				Float fRating = Float.parseFloat(salary);
				statement.setDouble(3, fRating);

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}
				System.out.println(see);
				see++;
			}

			lineReader.close();

			// execute the remaining queries
			statement.executeBatch();

			connection.commit();
			connection.close();

		} catch (IOException ex) {
			System.err.println(ex);
		} catch (SQLException ex) {
			ex.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}