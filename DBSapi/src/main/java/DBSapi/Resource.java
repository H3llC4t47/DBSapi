package DBSapi;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

@Produces(MediaType.APPLICATION_JSON)
@Path("zoo")
public class Resource {

    @GET
    public Response getAll() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/c3zoo", "root", "");

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT Z.jmeno, D.nazev, Z.vaha, Z.narozen, Z.id FROM Zvirata AS Z JOIN Druhy AS D ON (D.id = Z.druh) ORDER BY Z.vaha DESC");

        ArrayList<Animal> animalArr = new ArrayList<>();

        while(result.next()) {

            Animal animal = new Animal();

            String jmeno = result.getString("jmeno");
            animal.setJmeno(jmeno);
            String druhnazev = result.getString("nazev");
            animal.setDruh(druhnazev);
            String vaha = result.getString("vaha");
            animal.setId(vaha);
            String narozen = result.getString("narozen");
            animal.setId(narozen);
            String id = result.getString("id");
            animal.setId(id);
            animalArr.add(animal);

        }
        connection.close();
        return Response.ok(animalArr).build();
    }
}
