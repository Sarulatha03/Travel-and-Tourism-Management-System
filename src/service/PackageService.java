package service;

import dao.PackageDAO;
import model.Package;

import java.sql.SQLException;
import java.util.List;

public class PackageService {

    private PackageDAO packageDAO = new PackageDAO();

    // Method to add a new tour package
    public void addPackage(Package pkg) throws SQLException {
        packageDAO.addPackage(pkg);
    }

    // Method to get all tour packages
    public List<Package> getAllPackages() throws SQLException {
        return packageDAO.getAllPackages();
    }
}
