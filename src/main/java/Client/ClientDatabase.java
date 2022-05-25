package Client;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDatabase {

    Connection connection;

    ClientGeneration clientGeneration = new ClientGeneration();

    public ClientDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Client> read(){
        List<Client> clients = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Clients");
            while(result.next()) {
                Client current = clientGeneration.createClient(result);
                clients.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return clients;
    }

    public void update(Client newClient){
        try{
            String query = "UPDATE Clients SET firstName = ?, lastName = ?, CNP = ?, birthDate = ?, email = ?, phone = ?, street = ?, city = ?, county = ?, postalCode = ? WHERE clientId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newClient.getFirstName());
            preparedStmt.setString(2, newClient.getLastName());
            preparedStmt.setString(3, newClient.getCNP());
            preparedStmt.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(newClient.getBirthDate()));
            preparedStmt.setString(5, newClient.getEmail());
            preparedStmt.setString(6, newClient.getPhone());
            preparedStmt.setString(7, newClient.getAddress().getStrada());
            preparedStmt.setString(8, newClient.getAddress().getOras());
            preparedStmt.setString(9, newClient.getAddress().getJudet());
            preparedStmt.setInt(10, newClient.getAddress().getCodPostal());
            preparedStmt.setInt(11, newClient.getClientId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(Client client){
        try{
            String query = "INSERT INTO Clients (clientId, firstName, lastName, CNP, birthDate, email, phone, street, city, county, postalCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getClientId());
            preparedStmt.setString(2, client.getFirstName());
            preparedStmt.setString(3, client.getLastName());
            preparedStmt.setString(4, client.getCNP());
            preparedStmt.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(client.getBirthDate()));
            preparedStmt.setString(6, client.getEmail());
            preparedStmt.setString(7, client.getPhone());
            preparedStmt.setString(8, client.getAddress().getStrada());
            preparedStmt.setString(9, client.getAddress().getOras());
            preparedStmt.setString(10, client.getAddress().getJudet());
            preparedStmt.setInt(11, client.getAddress().getCodPostal());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Client client){
        try{
            String query = "DELETE FROM Clients WHERE clientId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getClientId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
