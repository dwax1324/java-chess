package db;

import domain.board.position.Position;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MovementDao {

    JdbcConnector jdbcConnector = new JdbcConnector();

    public void createMovement(final Movement movement) {
        final var query = "INSERT INTO movement (source,  target, shape, color) values(?, ?, ?, ?)";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movement.source().toString());
            preparedStatement.setString(2, movement.target().toString());
            preparedStatement.setString(3, movement.shape());
            preparedStatement.setString(4, movement.color());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movement> findAll() {
        final var query = "SELECT * FROM movement";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            final List<Movement> movements = new ArrayList<>();
            while (resultSet.next()) {
                movements.add(new Movement(
                        Position.from(resultSet.getString("source")),
                        Position.from(resultSet.getString("target")),
                        resultSet.getString("shape"),
                        resultSet.getString("color")
                ));
            }
            return movements;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final var query = "DELETE from movement";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
