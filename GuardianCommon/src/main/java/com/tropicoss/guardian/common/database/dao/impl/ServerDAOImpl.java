package com.tropicoss.guardian.common.database.dao.impl;

import com.tropicoss.guardian.common.database.DatabaseConnection;
import com.tropicoss.guardian.common.database.dao.ServerDAO;
import com.tropicoss.guardian.common.database.model.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerDAOImpl implements ServerDAO {
    private final DatabaseConnection databaseConnection;

    public ServerDAOImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void addServer(Server server) {
        String sql = "INSERT INTO servers (serverId, name, token, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, server.getServerId());
            stmt.setString(2, server.getName());
            stmt.setString(3, server.getToken());
            stmt.setTimestamp(4, Timestamp.valueOf(server.getCreatedAt()));
            stmt.setTimestamp(5, Timestamp.valueOf(server.getModifiedAt()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Server getServerById(int serverId) {
        String sql = "SELECT * FROM servers WHERE serverId = ?";
        Server server = null;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, serverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                server = new Server();
                server.setServerId(rs.getInt("serverId"));
                server.setName(rs.getString("name"));
                server.setToken(rs.getString("token"));
                server.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                server.setModifiedAt(rs.getTimestamp("modifiedAt").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return server;
    }

    @Override
    public List<Server> getAllServers() {
        String sql = "SELECT * FROM servers";
        List<Server> servers = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Server server = new Server();
                server.setServerId(rs.getInt("serverId"));
                server.setName(rs.getString("name"));
                server.setToken(rs.getString("token"));
                server.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                server.setModifiedAt(rs.getTimestamp("modifiedAt").toLocalDateTime());

                servers.add(server);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servers;
    }

    @Override
    public void updateServer(Server server) {
        String sql = "UPDATE servers SET name = ?, token = ?, modifiedAt = ? WHERE serverId = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, server.getName());
            stmt.setString(2, server.getToken());
            stmt.setTimestamp(3, Timestamp.valueOf(server.getModifiedAt()));
            stmt.setInt(4, server.getServerId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteServer(int serverId) {
        String sql = "DELETE FROM servers WHERE serverId = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, serverId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
