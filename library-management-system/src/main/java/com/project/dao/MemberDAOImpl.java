package com.project.dao;

import java.sql.*;
import java.util.*;

import com.project.classes.Member;
import com.project.utils.DatabaseConnection;

 public class MemberDAOImpl implements MemberDAO {
    @Override
    public void addMember(Member member) {
        String query = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getMemberName());
            stmt.setString(2, member.getMemberEmail());
            stmt.setString(3, member.getMemberPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMember(Member member) {
        String query = "UPDATE members SET name = ?, email = ?, phone = ? WHERE member_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getMemberName());
            stmt.setString(2, member.getMemberEmail());
            stmt.setString(3, member.getMemberPhone());
            stmt.setInt(4, member.getMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(int memberId) {
        String query = "DELETE FROM members WHERE member_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Member member = new Member(
                    rs.getInt("member_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


}
