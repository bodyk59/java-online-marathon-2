import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
	private Connection connection;
	private Statement statement;
	private String schemaName;

    public Connection createConnection() throws SQLException {
    	DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:test", "", "");
		return connection;
    }
    
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public Statement createStatement() throws SQLException {
        statement = connection.createStatement();
        return statement;
    }

    public void closeStatement() throws SQLException {
        statement.close();
    }

    public void createSchema(String schemaName) throws SQLException {
        this.schemaName = schemaName;
        statement.execute("CREATE SCHEMA IF NOT EXISTS " + this.schemaName + ";");
    }

    public void dropSchema() throws SQLException {
        statement.execute("DROP SCHEMA IF EXISTS " + this.schemaName + ";");
    }

    public void useSchema() throws SQLException {
        statement.execute("SET SCHEMA " + this.schemaName + ";");
    }

    public void createTableRoles() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Roles (" +
                "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "roleName VARCHAR(50) NOT NULL);" );
    }

    public void createTableDirections() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Directions (" +
                "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "directionName VARCHAR(50) NOT NULL);" );
    }

    public void createTableProjects() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Projects (" +
                "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "projectName VARCHAR(50) NOT NULL," +
                "directionId INT, " +
                "CONSTRAINT fk_directionId FOREIGN KEY (directionId) REFERENCES Directions(id));");
    }

    public void createTableEmployee() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Employee (" +
                "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "firstName VARCHAR(50) NOT NULL," +
                "roleId INT," +
                "projectId INT," +
                "CONSTRAINT fk_roleId FOREIGN KEY (roleId) REFERENCES Roles(id)," +
                "CONSTRAINT fk_projectsId FOREIGN KEY (projectId) REFERENCES Projects(id));");
    }

    public void dropTable(String tableName) throws SQLException {
        statement.execute("DROP TABLE IF EXISTS " + tableName + " CASCADE;");
    }
    
    public void insertTableRoles(String roleName) throws SQLException {
        statement.execute("INSERT INTO Roles(roleName) VALUES ('" + roleName + "');");
    }

    public void insertTableDirections(String directionName) throws SQLException {
        statement.execute("INSERT INTO Directions(directionName) VALUES ('" + directionName + "');");
    }

    public void insertTableProjects(String projectName, String directionName) throws SQLException {
        statement.execute("INSERT INTO Projects(projectName, directionId) VALUES ('"
                + projectName + "', " +
                "(SELECT id FROM Directions WHERE directionName = '" + directionName + "'));");
    }

    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
        statement.execute("INSERT INTO Employee(firstName, roleId, projectId) VALUES ('"
                + firstName +"', " +
                "(SELECT id FROM Roles WHERE roleName = '" + roleName + "')," +
                "(SELECT id FROM Projects WHERE projectName = '" + projectName + "'));");
    }
    
    public int getRoleId(String roleName) throws SQLException {
        ResultSet resultSet =  statement.executeQuery("SELECT id FROM Roles WHERE rolename = '" + roleName + "';");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    public int getDirectionId(String directionName) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT id FROM Directions WHERE directionName = '" + directionName + "';");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    public int getProjectId(String projectName) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT id FROM Projects WHERE projectName = '" + projectName + "';");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    public int getEmployeeId(String firstName) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT id FROM Employee WHERE firstName = '" + firstName + "';");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }
    
    public List<String> getAllRoles() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Roles;");
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(2));
        }
        return result;
    }

    public List<String> getAllDirestion() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Directions;");
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(2));
        }
        return result;
    }

    public List<String> getAllProjects() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Projects;");
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(2));
        }
        return result;
    }

    public List<String> getAllEmployee() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee;");
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(2));
        }
        return result;
    }
    
    public List<String> getAllDevelopers() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee WHERE roleId = 1;");
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(2));
        }
        return result;
    }

    public List<String> getAllJavaProjects() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Projects WHERE directionId = 1;");
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(2));
        }
        return result;
    }

    public List<String> getAllJavaDevelopers() throws SQLException {
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM Employee WHERE roleId = 1 AND (projectId = 1 OR projectId = 2);");
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(2));
        }
        return result;
    }
}
