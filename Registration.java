import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class Registration extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField txtAge;
    private JTextField txtMob;
    private JTextField txtEmail;
    private JTextField txtUsername;
    private JPasswordField txtPass;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registration frame = new Registration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Registration() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ayush\\Downloads\\user.jpg"));
        setTitle("Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 644);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 65, 85, 22);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Address");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 112, 85, 22);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Gender");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(10, 195, 85, 13);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Mobile No.");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_3.setBounds(10, 295, 85, 13);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Email");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_4.setBounds(10, 338, 85, 13);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Age");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_5.setBounds(10, 241, 85, 22);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Username");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_6.setBounds(10, 387, 85, 22);
        contentPane.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Password");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_7.setBounds(10, 451, 85, 22);
        contentPane.add(lblNewLabel_7);

        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtName.setBounds(105, 69, 231, 19);
        contentPane.add(txtName);
        txtName.setColumns(10);

        JTextArea txtAddr = new JTextArea();
        txtAddr.setBounds(105, 113, 231, 62);
        contentPane.add(txtAddr);

        JRadioButton rbMale = new JRadioButton("Male");
        rbMale.setBackground(new Color(128, 255, 0));
        rbMale.setFont(new Font("Tahoma", Font.BOLD, 14));
        buttonGroup.add(rbMale);
        rbMale.setBounds(117, 193, 103, 21);
        contentPane.add(rbMale);

        JRadioButton rbFemale = new JRadioButton("Female");
        rbFemale.setBackground(new Color(128, 255, 0));
        rbFemale.setFont(new Font("Tahoma", Font.BOLD, 14));
        buttonGroup.add(rbFemale);
        rbFemale.setBounds(233, 193, 103, 21);
        contentPane.add(rbFemale);

        txtAge = new JTextField();
        txtAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtAge.setBounds(105, 245, 231, 19);
        contentPane.add(txtAge);
        txtAge.setColumns(10);

        txtMob = new JTextField();
        txtMob.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMob.setBounds(105, 294, 231, 19);
        contentPane.add(txtMob);
        txtMob.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtEmail.setBounds(105, 337, 231, 19);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsername.setBounds(103, 391, 233, 19);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        txtPass = new JPasswordField();
        txtPass.setBounds(105, 455, 231, 19);
        contentPane.add(txtPass);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "Ayush@$1828")) {
                        String query = "INSERT INTO registration (name, address, gender, age, mobile, email, password, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement ps = con.prepareStatement(query)) {
                            ps.setString(1, txtName.getText());
                            ps.setString(2, txtAddr.getText());
                            ps.setString(3, rbMale.isSelected() ? rbMale.getText() : rbFemale.getText());
                            ps.setString(4, txtAge.getText().isEmpty() ? null : txtAge.getText());
                            ps.setString(5, txtMob.getText().isEmpty() ? null : txtMob.getText());
                            ps.setString(6, txtEmail.getText());
                            ps.setString(7, new String(txtPass.getPassword()));
                            ps.setString(8, txtUsername.getText());
                            int i = ps.executeUpdate();
                            JOptionPane.showMessageDialog(btnRegister, i + " Record Added Successfully");
                        }
                    }
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(btnRegister, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRegister.setBounds(68, 521, 103, 38);
        contentPane.add(btnRegister);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtAddr.setText("");
                txtAge.setText("");
                txtMob.setText("");
                txtEmail.setText("");
                txtUsername.setText("");
                txtPass.setText("");
                buttonGroup.clearSelection();
            }
        });
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnReset.setBounds(233, 521, 103, 38);
        contentPane.add(btnReset);
    }
}
