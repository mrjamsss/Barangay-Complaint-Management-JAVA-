package barangayincidentmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder; 
import javax.swing.table.DefaultTableModel;

public class BarangayIncidentManagementSystem {
    static ArrayList<Complaint> complaints = new ArrayList<>();
    static boolean isLoggedIn = false;
    public static void main(String[] args) {
        // Login GUI
        JFrame loginFrame = new JFrame("Barangay Incident Login");
        loginFrame.setSize(300, 220);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        
        JPanel loginPanel1 = new JPanel();
        loginPanel1.setLayout(new GridLayout(3,2,10,10));
        loginPanel1.setBounds(40,15,200,100);
        
        JPanel loginPanel2 = new JPanel();
        loginPanel2.setLayout(new GridLayout(2,1,10,10));
        loginPanel2.setBounds(40,100,200,70);
        
        loginFrame.add(loginPanel2);
        loginFrame.add(loginPanel1);

        JLabel lblUserID = new JLabel("User ID:");
        JTextField fldUserID = new JTextField(15);
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField fldPassword = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JLabel lblMessage = new JLabel("");

        loginPanel1.add(lblUserID);
        loginPanel1.add(fldUserID);
        loginPanel1.add(lblPassword);
        loginPanel1.add(fldPassword);
        loginPanel2.add(btnLogin);
        loginPanel2.add(lblMessage);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = fldUserID.getText();
                String password = new String(fldPassword.getPassword());

                // Only admin can log in
                if (userID.equals("admin") && password.equals("1234")) {
                    isLoggedIn = true;
                    loginFrame.dispose();
                    displayMenu();
                } else {
                    lblMessage.setText("Invalid credentials. Try again.");
                    lblMessage.setForeground(Color.RED);
                }
            }
        });

        loginFrame.setVisible(true);
    }
    
    public static void displayMenu() {
            JFrame displayFrame = new JFrame("Menu");
            displayFrame.setSize(400, 350);
            displayFrame.setLocationRelativeTo(null);
            displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            displayFrame.setLayout(null);
            
            JPanel displayPanel = new JPanel();
            displayPanel.setLayout(new GridLayout(8,1,10,10));
            displayPanel.setBounds(50,10,300,300);
            
            displayFrame.add(displayPanel);
            
            JLabel lblWelcome = new JLabel("Welcome, Admin! Please choose a task.");
            JButton disbtn1 = new JButton("New Complaint");
            JButton disbtn2 = new JButton("Edit Complaint");
            JButton disbtn3 = new JButton("Check Status of Complaints");
            JButton disbtn4 = new JButton("Display Complaints");
            JButton disbtn5 = new JButton("Solve Complaint");
            JButton disbtn6 = new JButton("Logout");
            JLabel lblLogout = new JLabel("");
            
            disbtn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayFrame.dispose();
                    createNewComplaint();
                }
            });
            
            disbtn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editComplaint();
                    displayFrame.dispose();
                }
            });
            
            disbtn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkComplaintStatus();
                    displayFrame.dispose();
                }
            });
            
            disbtn4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayComplaints();
                    displayFrame.dispose();
                }
            });
            
            disbtn5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayFrame.dispose();
                    solveComplaint();
                }
            });
            
            disbtn6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lblLogout.setText("Logging Out...");
                    lblLogout.setForeground(Color.RED);
                    isLoggedIn = false;
                    
                    Timer timer = new Timer(1500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            displayFrame.dispose();
                            System.exit(0);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    }
            });

            
            displayPanel.add(lblWelcome);
            displayPanel.add(disbtn1);
            displayPanel.add(disbtn2);
            displayPanel.add(disbtn3);
            displayPanel.add(disbtn4);
            displayPanel.add(disbtn5);
            displayPanel.add(disbtn6);
            displayPanel.add(lblLogout);
            displayFrame.setVisible(true);
    }
    
    public static void backToMenu(JFrame currentFrame) {
        JButton btnToMenu = new JButton("Back to Menu");
    currentFrame.add(btnToMenu, BorderLayout.SOUTH);

    btnToMenu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentFrame.dispose();
            displayMenu();
            }
        });
    }

    public static void createNewComplaint() {
        JFrame createFrame = new JFrame("Create Complaint");
        createFrame.setSize(400, 130);
        createFrame.setLocationRelativeTo(null);
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createFrame.setLayout(new FlowLayout());

        JPanel verifyPanel = new JPanel();
        verifyPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JPanel verifyPanel1 = new JPanel();
        verifyPanel1.setLayout(new GridLayout(1, 3, 10, 10));

        JLabel lblQuestion = new JLabel("Is this complainant from Brgy. Sumacabilang Buhay?");
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");
        JButton btnCancel = new JButton("Cancel");

        verifyPanel.add(lblQuestion);
        verifyPanel1.add(btnYes);
        verifyPanel1.add(btnNo);
        verifyPanel1.add(btnCancel);

        verifyPanel.add(verifyPanel1);
        createFrame.add(verifyPanel);

        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFrame.dispose();
                showComplaintTypes();
            }
        });

        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(createFrame, 
                    "Please, go to the barangay where the complainant is from. \nGo back to main menu.");
                createFrame.dispose();
                displayMenu();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFrame.dispose();
                displayMenu();
            }
        });

        createFrame.setVisible(true);
    }
    
    private static void showComplaintTypes() {
    String[] complaintTypes = {
        "1. Neighborhood Disputes (e.g., Boundary Disagreements, Noise Complaints)",
        "2. Family or Domestic Issues (e.g., Domestic Altercations, Child Custody Disputes)",
        "3. Financial Complaints (e.g., Unpaid Debts, Business Disputes)",
        "4. Physical Altercations or Harassment (e.g., Minor Physical Altercations, Harassment Complaints)",
        "5. Pet-Related Complaints (e.g., Pet Noise or Safety Issues)",
        "6. Property or Resource Use Disputes (Shared Resource Issues (e.g., water or electricity), Damage to Property)",
        "7. Minor Theft or Property Loss (Resolution for Minor Theft, Loss or Damage of Borrowed Items)",
        "8. Misunderstandings or Miscommunication (Resolution for False Accusations, Clarifications of Misunderstandings)",
        "9. Agreements for Future Peaceful Coexistence (Barangay Protection Orders (if applicable)",
        "10. Others"
    };

    String selectedType = (String) JOptionPane.showInputDialog(
        null,
        "Please select the type of complaint:",
        "Select Complaint Type",
        JOptionPane.PLAIN_MESSAGE,
        null,
        complaintTypes,
        complaintTypes[0]
    );

    if (selectedType != null) { // Proceed only if a selection was made
        // You can store the selected type for later use in the complaint form
        // For example, you can create a variable to hold the complaint type
        String complaintType = selectedType;

        // Proceed to the complaint form
        showComplaintForm(complaintType);
    } else {
        // If no selection was made, you can return to the menu or handle it as needed
        JOptionPane.showMessageDialog(null, "No complaint type selected. Returning to menu.");
        displayMenu();
    }
}

    private static void showComplaintForm(String complaintType) {
        JFrame complaintFormFrame = new JFrame("Complaint Form");
        complaintFormFrame.setSize(600, 500);
        complaintFormFrame.setLocationRelativeTo(null);
        complaintFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        complaintFormFrame.setLayout(null);
        
        JPanel complaintFormPanel = new JPanel();
        complaintFormPanel.setLayout(new GridLayout(10, 2, 10, 10));
        complaintFormPanel.setBounds(45,90,500,350);
        
        JPanel complaintFormPanel1 = new JPanel();
        complaintFormPanel1.setLayout(new GridLayout(2, 1, 10, 10));
        complaintFormPanel1.setBounds(45,10,700,60);
        
        complaintFormFrame.add(complaintFormPanel);
        complaintFormFrame.add(complaintFormPanel1);

        JLabel lblComplaintType = new JLabel("Complaint Type: "); 
        JLabel lblComplaintType1 = new JLabel(complaintType);
        JLabel lblComplainantName = new JLabel("Complainant Name: ");
        JTextField fldComplainantName = new JTextField(15);
        JLabel lblComplaineeName = new JLabel("Complainee Name: ");
        JTextField fldComplaineeName = new JTextField(15);
        JLabel lblAge = new JLabel("Enter Age: ");
        JTextField fldAge = new JTextField(15);
        JLabel lblAddress = new JLabel("Enter Address: ");
        JTextField fldAddress = new JTextField(15);
        JLabel lblContactNum = new JLabel("Enter Contact Number: ");
        JTextField fldContactNum = new JTextField(15);
        JLabel lblDescription = new JLabel("Enter Description of Complaint/Incident: ");
        JTextField fldDescription = new JTextField(15);
        JLabel lblSolution = new JLabel("Enter Possible Solution: ");
        JTextField fldSolution = new JTextField(15);
        JLabel lblDate = new JLabel("Enter date (YYYY-MM-DD): ");
        JTextField fldDate = new JTextField(15);
        JButton btnCancel = new JButton("Cancel");
        JButton btnSubmit = new JButton("Submit");
        JLabel lblConfirm = new JLabel("");

        complaintFormPanel1.add(lblComplaintType); 
        complaintFormPanel1.add(lblComplaintType1); // Add complaint type label
        complaintFormPanel.add(lblComplainantName);
        complaintFormPanel.add(fldComplainantName);
        complaintFormPanel.add(lblComplaineeName);
        complaintFormPanel.add(fldComplaineeName);
        complaintFormPanel.add(lblAge);
        complaintFormPanel.add(fldAge);
        complaintFormPanel.add(lblAddress);
        complaintFormPanel.add(fldAddress);
        complaintFormPanel.add(lblContactNum);
        complaintFormPanel.add(fldContactNum);
        complaintFormPanel.add(lblDescription);
        complaintFormPanel.add(fldDescription);
        complaintFormPanel.add(lblSolution);
        complaintFormPanel.add(fldSolution);
        complaintFormPanel.add(lblDate);
        complaintFormPanel.add(fldDate);
        complaintFormPanel.add(btnCancel);
        complaintFormPanel.add(btnSubmit);
        complaintFormPanel.add(lblConfirm);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ctname = fldComplainantName.getText().trim();
                String cename = fldComplaineeName.getText().trim();
                String ageText = fldAge.getText().trim();
                String address = fldAddress.getText().trim();
                String contact = fldContactNum.getText().trim();
                String description = fldDescription.getText().trim();
                String solution = fldSolution.getText().trim();
                String date = fldDate.getText().trim();

                // Validate that all fields are filled
                if (ctname.isEmpty() || cename.isEmpty() || ageText.isEmpty() || address.isEmpty() || 
                    contact.isEmpty() || description.isEmpty() || solution.isEmpty() || date.isEmpty()) {
                    lblConfirm.setText("Please complete the form.");
                    lblConfirm.setForeground(Color.RED);
                    return;
                }

                // Validate that age is a number
                try {
                int age = Integer.parseInt(ageText);
                String guardianName = null;

                // If age is less than 18, prompt for guardian's name
                if (age < 18) {
                    guardianName = JOptionPane.showInputDialog(complaintFormFrame, "Please enter the guardian's name:");
                    if (guardianName == null || guardianName.trim().isEmpty()) {
                        lblConfirm.setText("Guardian name is required for complainants under 18.");
                        lblConfirm.setForeground(Color.RED);
                        return;
                    }
                }

                    // Create and add the complaint if validation passes
                    Complaint complaint = new Complaint(ctname, cename, age, guardianName, address, contact, description, solution, date, solution);
                    complaint.type = complaintType; // Store the selected complaint type
                    complaints.add(complaint);

                    // Optionally clear fields after submission
                    fldComplainantName.setText("");
                    fldComplaineeName.setText("");
                    fldAge.setText("");
                    fldAddress.setText("");
                    fldContactNum.setText("");
                    fldDescription.setText("");
                    fldSolution.setText("");
                    fldDate.setText("");
                } catch (NumberFormatException ex) {
                    lblConfirm.setText("Invalid age. Please enter a number.");
                    lblConfirm.setForeground(Color.RED);
                }
                JOptionPane.showMessageDialog(complaintFormFrame, "Complaint logged successfully!");
                complaintFormFrame.dispose();
                displayMenu();

            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(complaintFormFrame, "You cancelled your option. Returning to the main menu.");
                complaintFormFrame.dispose();
                displayMenu();
            }
        });

        complaintFormFrame.setVisible(true);
    }    
    
    public static void solveComplaint() {
        JFrame solveFrame = new JFrame("Solve Complaint");
        solveFrame.setSize(550, 350);
        solveFrame.setLocationRelativeTo(null);
        solveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solveFrame.setLayout(new BorderLayout());
        
        JPanel solvePanel = new JPanel();
        solvePanel.setLayout(new GridLayout(5, 2, 10, 10));
        solvePanel.setBorder(new EmptyBorder(10, 20, 70, 20));
        
        solveFrame.add(solvePanel, BorderLayout.CENTER);

        JLabel lblEnterID = new JLabel("Enter Complaint ID: ");
        JTextField fldEnterID = new JTextField(15);
        JLabel lblCurrentSolution = new JLabel("Current Possible Solution: ");
        JLabel lblSolutionValue = new JLabel("N/A");
        JButton btnDisplayStatus = new JButton("Display Status");
        JButton btnCheckSolution = new JButton("Check Solution");
        JLabel lblStatusPrompt = new JLabel("");
        JLabel lblEmpty = new JLabel("");
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");

        lblStatusPrompt.setVisible(false);
        btnYes.setVisible(false);
        btnNo.setVisible(false);

        solvePanel.add(lblEnterID);
        solvePanel.add(fldEnterID);
        solvePanel.add(lblCurrentSolution);
        solvePanel.add(lblSolutionValue);
        solvePanel.add(btnDisplayStatus);
        solvePanel.add(btnCheckSolution);
        solvePanel.add(lblStatusPrompt);
        solvePanel.add(lblEmpty);
        solvePanel.add(btnYes);
        solvePanel.add(btnNo);
        
        btnDisplayStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveFrame.dispose();
                checkComplaintStatus();
            }
        }); 

        btnCheckSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(fldEnterID.getText().trim());
                    Complaint complaint = findComplaintById(id);

                    if (complaint != null) {
                        lblSolutionValue.setText(complaint.solution);
                        lblStatusPrompt.setText("Was the complaint fixed by this solution?");
                        lblStatusPrompt.setVisible(true);
                        btnYes.setVisible(true);
                        btnNo.setVisible(true);
                    } else {
                        lblSolutionValue.setText("Complaint Not Found.");
                        lblStatusPrompt.setVisible(false);
                        btnYes.setVisible(false);
                        btnNo.setVisible(false);
                    }
                } catch (NumberFormatException ex) {
                    lblSolutionValue.setText("Invalid ID. Please enter a number.");
                }
            }
        });

        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(fldEnterID.getText().trim());
                    Complaint complaint = findComplaintById(id);

                    if (complaint != null) {
                        if (lblStatusPrompt.getText().equals("Was the complaint fixed by this solution?")) {
                            complaint.status = "RESOLVED";
                            JOptionPane.showMessageDialog(solveFrame, "Complaint marked as RESOLVED.");
                            solveFrame.dispose();
                            displayMenu();
                        } else if (lblStatusPrompt.getText().equals("Do you want another solution?")) {
                            String newSolution = JOptionPane.showInputDialog(solveFrame, "Enter New Solution:");
                            if (newSolution != null && !newSolution.isEmpty()) {
                                complaint.solution = newSolution;
                                complaint.status = "PENDING";
                                JOptionPane.showMessageDialog(solveFrame, "Solution updated. Status remains PENDING.");
                            }
                        } else if (lblStatusPrompt.getText().equals("Do you want to escalate the matter to the appropriate courts?")) {
                            complaint.status = "RHA";
                            JOptionPane.showMessageDialog(solveFrame, "Complaint escalated. Status set to RHA.");

                            // Show CFA (Certificate to File Action)
                            showCFA(solveFrame);  // Call the method to display CFA
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(solveFrame, "Invalid Complaint ID.");
                }
            }
        });

        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(fldEnterID.getText().trim());
                    Complaint complaint = findComplaintById(id);

                    if (complaint != null) {
                        if (lblStatusPrompt.getText().equals("Was the complaint fixed by this solution?")) {
                            lblStatusPrompt.setText("Do you want another solution?");
                            lblSolutionValue.setText("Solution remains: " + complaint.solution);
                        } else if (lblStatusPrompt.getText().equals("Do you want another solution?")) {
                            lblStatusPrompt.setText("Do you want to escalate the matter to the appropriate courts?");
                            lblSolutionValue.setText("Solution remains: " + complaint.solution);
                        } else if (lblStatusPrompt.getText().equals("Do you want to escalate the matter to the appropriate courts?")) {
                            complaint.status = "UNRESOLVED";
                            JOptionPane.showMessageDialog(solveFrame, "Complaint marked as UNRESOLVED.");
                            solveFrame.dispose();
                            displayMenu();
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(solveFrame, "Invalid Complaint ID.");
                }
            }
        });

        // Check Solution Button Action: Display the initial solution and ask if fixed
        btnCheckSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(fldEnterID.getText().trim());
                    Complaint complaint = findComplaintById(id);

                    if (complaint != null) {
                        lblSolutionValue.setText(complaint.solution);
                        lblStatusPrompt.setText("Was the complaint fixed by this solution?");
                        lblStatusPrompt.setVisible(true);
                        btnYes.setVisible(true);
                        btnNo.setVisible(true);
                        btnYes.setEnabled(true);
                        btnNo.setEnabled(true);
                    } else {
                        lblSolutionValue.setText("Complaint Not Found.");
                        lblStatusPrompt.setVisible(false);
                        btnYes.setVisible(false);
                        btnNo.setVisible(false);
                    }
                } catch (NumberFormatException ex) {
                    lblSolutionValue.setText("Invalid ID. Please enter a number.");
                }
            }
        });

        backToMenu(solveFrame);
        solveFrame.setVisible(true);
    }
    
    // Method to show the CFA (Certificate to File Action)
    private static void showCFA(JFrame parentFrame) {
        JFrame cfaFrame = new JFrame("Certificate to File Action (CFA)");
        cfaFrame.setSize(500, 450);
        cfaFrame.setLocationRelativeTo(parentFrame);
        cfaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel lblTitle = new JLabel("<html><center>" 
                + "Republic of the Philippines<br>"
                + "Province of Nueva Ecija<br>"
                + "Municipality of Cabanatuan City<br>"
                + "Barangay Sumacabilang Buhay"
                + "</center></html>");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblTitle);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblCfaHeader = new JLabel("Certificate to File Action");
        lblCfaHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCfaHeader.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblCfaHeader);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // Input fields for complainant and respondent
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel lblComplainantName = new JLabel("Complainant Name:");
        JTextField fldComplainantName = new JTextField();
        JLabel lblComplainantAddress = new JLabel("Complainant Address:");
        JTextField fldComplainantAddress = new JTextField();
        JLabel lblComplaineeName = new JLabel("Complainee Name:");
        JTextField fldComplaineeName = new JTextField();
        JLabel lblComplaineeAddress = new JLabel("Complainee Address:");
        JTextField fldComplaineeAddress = new JTextField();
        JLabel lblDate = new JLabel("Date of Filing (YYYY-MM-DD):");
        JTextField fldDate = new JTextField();

        inputPanel.add(lblComplainantName);
        inputPanel.add(fldComplainantName);
        inputPanel.add(lblComplainantAddress);
        inputPanel.add(fldComplainantAddress);
        inputPanel.add(lblComplaineeName);
        inputPanel.add(fldComplaineeName);
        inputPanel.add(lblComplaineeAddress);
        inputPanel.add(fldComplaineeAddress);
        inputPanel.add(lblDate);
        inputPanel.add(fldDate);

        panel.add(inputPanel);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // Button to generate CFA
        JButton btnGenerateCFA = new JButton("Generate CFA");
        btnGenerateCFA.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnGenerateCFA);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // Close button
        JButton btnCloseCFA = new JButton("Close");
        btnCloseCFA.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCloseCFA.addActionListener(e -> {
            cfaFrame.dispose();
            displayMenu();
        });
        panel.add(btnCloseCFA);

        // Action listener for Generate CFA
        btnGenerateCFA.addActionListener(e -> {
            String complainantName = fldComplainantName.getText().trim();
            String complainantAddress = fldComplainantAddress.getText().trim();
            String respondentName = fldComplaineeName.getText().trim();
            String respondentAddress = fldComplaineeAddress.getText().trim();
            String dateOfFiling = fldDate.getText().trim();

            if (complainantName.isEmpty() || complainantAddress.isEmpty() || respondentName.isEmpty() || respondentAddress.isEmpty() || dateOfFiling.isEmpty()) {
                JOptionPane.showMessageDialog(cfaFrame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Generate CFA content
            String cfaContent = String.format(
                "<html><center>" +
                "Republic of the Philippines<br>" +
                "Province of Nueva Ecija<br>" +
                "Municipality of Cabanatuan City<br>" +
                "Barangay Sumacabilang Buhay<br><br>" +
                "<b>Certificate to File Action</b><br><br>" +
                "This is to certify that the complaint lodged by:<br><br>" +
                "<b>Complainant Name:</b> %s<br>" +
                "<b>Address:</b> %s<br><br>" +
                "--Against--<br><br>" +
                "<b>Respondent Name:</b> %s<br>" +
                "<b>Address:</b> %s<br><br>" +
                "Concerning the matter of:<br>" +
                "[Description of the Complaint/Incident]<br><br>" +
                "Despite the barangay’s efforts to mediate and resolve the issue, no amicable settlement has been reached.<br>" +
                "As such, the complainant is hereby issued this Certificate to File Action (CFA), authorizing them to escalate<br>" +
                "this matter to the appropriate court or relevant authority for further resolution.<br><br>" +
                "Issued this %s at Barangay Sumacabilang Buhay, Cabanatuan City, Nueva Ecija.<br><br>" +
                "Barangay Captain/Authorized Official:<br>" +
                "Capt. Pol Clarence Pascual<br>" +
                "Barangay Captain<br><br>" +
                "Barangay Secretary:<br>" +
                "Sec. Angel Nishimoto<br>" +
                "Barangay Secretary" +
                "</center></html>",
                complainantName, complainantAddress, respondentName, respondentAddress, dateOfFiling
            );

            // Display CFA in a new window
            JFrame cfaDisplayFrame = new JFrame("Generated CFA");
            cfaDisplayFrame.setSize(600, 500);
            cfaDisplayFrame.setLocationRelativeTo(cfaFrame);
            cfaDisplayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel cfaDisplayLabel = new JLabel(cfaContent);
            cfaDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JScrollPane scrollPane = new JScrollPane(cfaDisplayLabel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            cfaDisplayFrame.add(scrollPane);
            cfaDisplayFrame.setVisible(true);
        });

        cfaFrame.add(panel);
        cfaFrame.setVisible(true);
    }

    // Helper method to find a complaint by ID
    private static Complaint findComplaintById(int id) {
        for (Complaint complaint : complaints) {
            if (complaint.getId() == id) {
                return complaint;
            }
        }
        return null;
    }

    
    public static void editComplaint() {
        JFrame editFrame = new JFrame("Edit Complaint");
        editFrame.setSize(450, 350);
        editFrame.setLocationRelativeTo(null);
        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.setLayout(null); // Using absolute positioning for simplicity

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(4, 2, 10, 10));
        editPanel.setBounds(45, 20, 350, 150);

        JPanel editPanel1 = new JPanel();
        editPanel1.setLayout(new GridLayout(1, 2, 10, 10));
        editPanel1.setBounds(45, 250, 350, 30);

        editFrame.add(editPanel);
        editFrame.add(editPanel1);

        JLabel enterID = new JLabel("Enter Complaint ID to edit: ");
        JTextField fieldID = new JTextField(15);
        JLabel updatedDescription = new JLabel("Updated Description: ");
        JTextField fldUpdateDes = new JTextField(15);
        JLabel updatedSolution = new JLabel("Updated Solution: ");
        JTextField fldUpdateSol = new JTextField(15);
        JButton submitID = new JButton("Edit");
        JLabel confirmation = new JLabel("");
        JButton btnDisplay = new JButton("Display Complaints");
        JButton btnCancel = new JButton("Cancel");

        editPanel.add(enterID);
        editPanel.add(fieldID);
        editPanel.add(updatedDescription);
        editPanel.add(fldUpdateDes);
        editPanel.add(updatedSolution);
        editPanel.add(fldUpdateSol);
        editPanel.add(submitID);
        editPanel.add(confirmation);

        editPanel1.add(btnDisplay);
        editPanel1.add(btnCancel);

        submitID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation.setText("");
                
                try {
                    int id = Integer.parseInt(fieldID.getText().trim());
                    String upDes = fldUpdateDes.getText().trim();
                    String upSol = fldUpdateSol.getText().trim();
                    Complaint complaint = findComplaintById(id);
                    
                    if (upDes.isEmpty() || upSol.isEmpty()) {
                        confirmation.setText("Please complete the form.");
                        return;
                    }
                    
                    if (complaint != null) {
                        complaint.description = fldUpdateDes.getText();
                        complaint.solution = fldUpdateSol.getText();
                        confirmation.setText("Updated Successfully!");
                    } else {
                        confirmation.setText("Complaint Not Found!");
                    }
                } catch (NumberFormatException ex) {
                    confirmation.setText("Invalid Complaint ID!");
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(editFrame, "You cancelled your option. Returning to the main menu.");
                editFrame.dispose();
                displayMenu();
            }
        });

        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrame.dispose();
                displayComplaints();
            }
        });

        editFrame.setVisible(true);
    }


    public static void checkComplaintStatus() {
        JFrame checkStatus = new JFrame("Complaint Status");
        checkStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkStatus.setSize(600, 400);
        checkStatus.setLocationRelativeTo(null);
        checkStatus.setLayout(new BorderLayout());
        
        String[] info = {"ID", "Complainant Name", "Complainee Name", "Status"};
        DefaultTableModel model = new DefaultTableModel(info, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        if (complaints.isEmpty()) {
            model.addRow(new Object[]{"No ID to display.", "No complainant to display.", "No complainee to display.", "No status to display.",});
        } else {
            for (Complaint complaint : complaints) {
                String status = complaint.solution.isEmpty() ? "Pending" : "Resolved";
                Object[] row = {complaint.id, complaint.complainantName, complaint.complaineeName, complaint.status};
                model.addRow(row);
            }
        }
        checkStatus.add(scrollPane, BorderLayout.CENTER);
        backToMenu(checkStatus);
        checkStatus.setVisible(true);
    }
    
    public static void displayComplaints() {
    JFrame allDisplay = new JFrame("All Complaints");
    allDisplay.setLayout(new BorderLayout());
    allDisplay.setSize(500, 500);
    allDisplay.setLocationRelativeTo(null);
    allDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel displayPanel = new JPanel();
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

    if (complaints == null || complaints.isEmpty()) {
        displayPanel.add(new JLabel("No complaints to display."));
    } else {
        for (Complaint complaint : complaints) {
            JPanel complaintPanel = new JPanel();
            complaintPanel.setLayout(new GridLayout(0, 2, 0, 0));
            complaintPanel.setBorder(BorderFactory.createTitledBorder("Complaint ID: " + complaint.id));

            complaintPanel.add(new JLabel("Complainant Name:"));
            complaintPanel.add(new JLabel(complaint.complainantName));

            complaintPanel.add(new JLabel("Complainee Name:"));
            complaintPanel.add(new JLabel(complaint.complaineeName));

            complaintPanel.add(new JLabel("Age:"));
            complaintPanel.add(new JLabel(String.valueOf(complaint.age)));

            complaintPanel.add(new JLabel("Address:"));
            complaintPanel.add(new JLabel(complaint.address));

            complaintPanel.add(new JLabel("Contact Number:"));
            complaintPanel.add(new JLabel(complaint.contact));

            complaintPanel.add(new JLabel("Description:"));
            complaintPanel.add(new JLabel(complaint.description));

            complaintPanel.add(new JLabel("Solution:"));
            complaintPanel.add(new JLabel(complaint.solution));

            complaintPanel.add(new JLabel("Guardian Info:"));
            if (complaint.guardianInfo == null || complaint.guardianInfo.isEmpty()) {
                complaintPanel.add(new JLabel("N/A"));
            } else {
                complaintPanel.add(new JLabel(complaint.guardianInfo));
            }
            
            complaintPanel.add(new JLabel("Date:"));
            complaintPanel.add(new JLabel(complaint.date));

            displayPanel.add(complaintPanel);
        }
    }

    JScrollPane scrollPane = new JScrollPane(displayPanel);
    allDisplay.add(scrollPane, BorderLayout.CENTER);
    allDisplay.setVisible(true);
    backToMenu(allDisplay);
}

    public static void displayCFA(Complaint complaint) {
        JFrame cfaFrame = new JFrame("Certificate to File Action");
        cfaFrame.setSize(400, 200);
        cfaFrame.setLocationRelativeTo(null);
        cfaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cfaFrame.setLayout(new FlowLayout());

        JLabel lblCFA = new JLabel("<html><body>" +
                "Certificate to File Action<br>" +
                "Name: " + complaint.complainantName + "<br>" +
                "Address: " + complaint.address + "<br>" +
                "Description: " + complaint.description + "<br>" +
                "</body></html>");
        cfaFrame.add(lblCFA);

        cfaFrame.setVisible(true);
    }
}

class Complaint {
    static int count = 1;
    int id;
    String complainantName;
    String complaineeName;
    int age;
    String guardianInfo;
    String address;
    String contact;
    String description;
    String solution;
    String date;
    String type; // New field for complaint type
    
    // New fields for the Solve Complaint functionality
    String possibleSolution;  // To store the solution entered during complaint creation
    String status;  // To track the status of the complaint (e.g., RESOLVED, PENDING, UNRESOLVED)

    public Complaint(String complainantName, String complaineeName, int age, String guardianInfo, String address, String contact, String description, String solution, String date, String solution1) {
        this.id = count++; // Increment count and assign it to id
        this.complainantName = complainantName;
        this.complaineeName = complaineeName;
        this.age = age;
        this.guardianInfo = guardianInfo != null ? guardianInfo : "N/A"; 
        this.address = address;
        this.contact = contact;
        this.description = description;
        this.solution = solution;
        this.date = date;
        this.possibleSolution = solution1;
        this.status = "PENDING";
    }
    
    // Getter for id
    public int getId() {
        return id;
    }
    
    public String getPossibleSolution() {
        return possibleSolution;
    }

    public void setPossibleSolution(String possibleSolution) {
        this.possibleSolution = possibleSolution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    // Getter and setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}