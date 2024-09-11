package dao;

import model.Package;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageDAO {

    // Method to add a new package
    public void addPackage(Package pkg) throws SQLException {
        String query = "INSERT INTO packages (name, description, price) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, pkg.getName());
            ps.setString(2, pkg.getDescription());
            ps.setDouble(3, pkg.getPrice());
            ps.executeUpdate();
        }
    }

    // Method to get all packages
    public List<Package> getAllPackages() throws SQLException {
        List<Package> packages = new ArrayList<>();
        String query = "SELECT * FROM packages";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Package pkg = new Package();
                pkg.setId(rs.getInt("id"));
                pkg.setName(rs.getString("name"));
                pkg.setDescription(rs.getString("description"));
                pkg.setPrice(rs.getDouble("price"));
                packages.add(pkg);
            }
        }
        return packages;
    }
}
