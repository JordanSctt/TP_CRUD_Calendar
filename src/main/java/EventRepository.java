import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import java.util.Date;

public class EventRepository {

    private final static String SELECT_REQUEST = "SELECT id, _titre, _date, _description FROM _event";
    private final String INSERT_REQUEST = "INSERT INTO _event (_titre, _date, _description) VALUES (?, ?, ?)";
    private final String DELETE_REQUEST = "DELETE FROM _event WHERE id = ?";
    private final String UPDATE_REQUEST = "UPDATE _event SET _titre = ?, _date = ?, _description = ? WHERE id = ?";
    
    private static final String WHERE_ID = " WHERE id = ?";
    private static final String SELECT_REQUEST_WHERE_ID = SELECT_REQUEST + WHERE_ID;

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public List<Event> findAllEvents() throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST);
            resultSet = stmt.executeQuery();

            List<Event> listEvent = new ArrayList<>();
            while (resultSet.next()) {
                listEvent.add(toEvent(resultSet));
            }
            return listEvent;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + SELECT_REQUEST, e);
        } finally {
            JDBCUtils.close(resultSet, stmt, conn);
        }
    }
    
    public Event findById(int eventId) throws RepositoryException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = connectionFactory.create();
            stmt = conn.prepareStatement(SELECT_REQUEST_WHERE_ID);
            stmt.setInt(1, eventId);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return toEvent(resultSet);
            }
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + SELECT_REQUEST);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + SELECT_REQUEST, e);
        } finally {
            JDBCUtils.close(resultSet, stmt, conn);
        }
    }

    public Event create(Event event) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        java.sql.Date sqlDate = new java.sql.Date(event.getDate().getTime());
        try {
        	if (event.titreIsValid()) {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(INSERT_REQUEST);
            preparedStatement.setString(1, event.getTitre());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.executeUpdate();            
        	}
        	return event;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + INSERT_REQUEST, e);
        } finally {
            JDBCUtils.close(rs, preparedStatement, conn);
        }
    }
    
    public Event updateEvent(Event event, int id) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        java.sql.Date sqlDate = new java.sql.Date(event.getDate().getTime());
        try {        	
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(UPDATE_REQUEST);
            preparedStatement.setString(1, event.getTitre());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();                    	
        	return event;
        	
        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requete:" + UPDATE_REQUEST, e);
        } finally {
            JDBCUtils.close(rs, preparedStatement, conn);
        }
    }

    public void deleteEvent(int id) throws RepositoryException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = connectionFactory.create();
            preparedStatement = conn.prepareStatement(DELETE_REQUEST);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RepositoryException("Erreur lors de l'execution de la requête:" + INSERT_REQUEST, e);
        } finally {
            JDBCUtils.close(preparedStatement, conn);
        }

    }

    private Event toEvent(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getInt("id"));
        event.setTitre(resultSet.getString("_titre"));
        event.setDate(resultSet.getDate("_date"));
        event.setDescription(resultSet.getString("_description"));
        return event;
    }


}
