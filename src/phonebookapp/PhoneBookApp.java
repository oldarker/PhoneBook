package phonebookapp;

public class PhoneBookApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new PhoneBookGUI().setVisible(true);
        });
    }
    
}
