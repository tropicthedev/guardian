package com.tropicoss.guardian.common.database.dao.impl;

import com.tropicoss.guardian.common.database.DatabaseConnection;
import com.tropicoss.guardian.common.database.dao.SessionDAO;
import com.tropicoss.guardian.common.database.model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionDAOImpl implements SessionDAO {
    private final DatabaseConnection databaseConnection;
    public static final Logger LOGGER = LoggerFactory.getLogger("Guardian");


    public SessionDAOImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void addSession(Session session) {
        String sql = "INSERT INTO sessions (sessionId, memberId, serverId, sessionStart, sessionEnd) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, session.getSessionId());
            stmt.setInt(2, session.getMemberId());
            stmt.setInt(3, session.getServerId());
            stmt.setTimestamp(4, Timestamp.valueOf(session.getSessionStart()));
            stmt.setTimestamp(5, session.getSessionEnd() != null ? Timestamp.valueOf(session.getSessionEnd()) : null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Session getSessionById(int sessionId) {
        String sql = "SELECT * FROM sessions WHERE sessionId = ?";
        Session session = null;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sessionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                session = new Session();
                session.setSessionId(rs.getInt("sessionId"));
                session.setMemberId(rs.getInt("memberId"));
                session.setServerId(rs.getInt("serverId"));
                session.setSessionEnd(rs.getTimestamp("sessionEnd") != null ? rs.getTimestamp("sessionEnd").toLocalDateTime() : null);
                session.setSessionStart(rs.getTimestamp("sessionStart").toLocalDateTime());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return session;
    }

    @Override
    public List<Session> getAllSessions() {
        String sql = "SELECT * FROM sessions";
        List<Session> sessions = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Session session = new Session();
                session.setSessionId(rs.getInt("sessionId"));
                session.setMemberId(rs.getInt("memberId"));
                session.setServerId(rs.getInt("serverId"));
                session.setSessionEnd(rs.getTimestamp("sessionEnd") != null ? rs.getTimestamp("sessionEnd").toLocalDateTime() : null);
                session.setSessionStart(rs.getTimestamp("sessionStart").toLocalDateTime());

                sessions.add(session);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return sessions;
    }

    @Override
    public void updateSession(Session session) {
        String sql = "UPDATE sessions SET memberId = ?, serverId = ?, sessionEnd = ? WHERE sessionId = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, session.getMemberId());
            stmt.setInt(2, session.getServerId());
            stmt.setTimestamp(3, session.getSessionEnd() != null ? Timestamp.valueOf(session.getSessionEnd()) : null);
            stmt.setInt(4, session.getSessionId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteSession(int sessionId) {
        String sql = "DELETE FROM sessions WHERE sessionId = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sessionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
