import service.BookingService;
import service.FeedbackService;
import service.PackageService;
import service.UserService;
import service.PaymentService;
import model.Booking;
import model.Feedback;
import model.Package;
import model.User;
import model.Payment;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static UserService userService = new UserService();
    private static PackageService packageService = new PackageService();
    private static BookingService bookingService = new BookingService();
    private static FeedbackService feedbackService = new FeedbackService();
    private static PaymentService paymentService = new PaymentService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Travel and Tourism Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (customer/admin): ");
        String role = scanner.nextLine();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        try {
            userService.registerUser(user);
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while registering the user.");
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.loginUser(username, password);
            if (user != null) {
                System.out.println("Login successful.");
                // Additional functionality based on role
                if ("admin".equals(user.getRole())) {
                    adminMenu(scanner);
                } else {
                    customerMenu(scanner, user.getId());
                }
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred during login.");
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add Package");
            System.out.println("2. View All Bookings");
            System.out.println("3. Manage Users");
            System.out.println("4. Generate Reports");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addPackage(scanner);
                    break;
                case 2:
                    viewAllBookings();
                    break;
                case 3:
                    manageUsers();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void customerMenu(Scanner scanner, int userId) {
        while (true) {
            System.out.println("Customer Menu");
            System.out.println("1. View Packages");
            System.out.println("2. Book Package");
            System.out.println("3. View Booking History");
            System.out.println("4. Provide Feedback");
            System.out.println("5. Make Payment");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewPackages();
                    break;
                case 2:
                    bookPackage(scanner, userId);
                    break;
                case 3:
                    viewBookingHistory(userId);
                    break;
                case 4:
                    provideFeedback(scanner, userId);
                    break;
                case 5:
                    makePayment(scanner, userId);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addPackage(Scanner scanner) {
        System.out.print("Enter package name: ");
        String name = scanner.nextLine();
        System.out.print("Enter package description: ");
        String description = scanner.nextLine();
        System.out.print("Enter package price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Package pkg = new Package();
        pkg.setName(name);
        pkg.setDescription(description);
        pkg.setPrice(price);

        try {
            packageService.addPackage(pkg);
            System.out.println("Package added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding the package.");
        }
    }

    private static void viewAllBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            for (Booking booking : bookings) {
                System.out.println("ID: " + booking.getId());
                System.out.println("User ID: " + booking.getUserId());
                System.out.println("Package ID: " + booking.getPackageId());
                System.out.println("Booking Date: " + booking.getBookingDate());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching all bookings.");
        }
    }

    private static void manageUsers() {
        try {
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching users.");
        }
    }


    private static void generateReports() {
        // Implement report generation based on your requirements
        System.out.println("Report generation is not implemented yet.");
    }

    private static void viewPackages() {
        try {
            List<Package> packages = packageService.getAllPackages();
            for (Package pkg : packages) {
                System.out.println("ID: " + pkg.getId());
                System.out.println("Name: " + pkg.getName());
                System.out.println("Description: " + pkg.getDescription());
                System.out.println("Price: " + pkg.getPrice());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching packages.");
        }
    }

    private static void bookPackage(Scanner scanner, int userId) {
        System.out.print("Enter package ID to book: ");
        int packageId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setPackageId(packageId);
        booking.setBookingDate(new java.util.Date());

        try {
            bookingService.addBooking(booking);
            System.out.println("Booking successful.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while booking the package.");
        }
    }

    private static void viewBookingHistory(int userId) {
        try {
            List<Booking> bookings = bookingService.getBookingsByUser(userId);
            for (Booking booking : bookings) {
                System.out.println("ID: " + booking.getId());
                System.out.println("Package ID: " + booking.getPackageId());
                System.out.println("Booking Date: " + booking.getBookingDate());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching booking history.");
        }
    }

    private static void provideFeedback(Scanner scanner, int userId) {
        System.out.print("Enter package ID to provide feedback: ");
        int packageId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter feedback: ");
        String feedbackText = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setPackageId(packageId);
        feedback.setFeedback(feedbackText);
        feedback.setRating(rating);

        try {
            feedbackService.addFeedback(feedback);
            System.out.println("Feedback submitted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while submitting feedback.");
        }
    }

    private static void makePayment(Scanner scanner, int userId) {
        System.out.print("Enter booking ID to make payment: ");
        int bookingId = scanner.nextInt();
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setPaymentDate(new java.util.Date());
        payment.setPaymentStatus("Pending");

        try {
            paymentService.addPayment(payment);
            System.out.println("Payment made successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while making the payment.");
        }
    }
}
