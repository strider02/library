package views;


import javax.swing.*;

/**
 * @author admin
 */
public class Welcome extends JFrame {


    // Variables declaration - do not modify
    private JPanel background;
    private JLabel phrase;
    private JLabel logo;
    private JLabel window;
    // End of variables declaration

    public Welcome() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initComponents() {

        background = new JPanel();
        phrase = new JLabel();
        logo = new JLabel();
        window = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(1920, 1080));
        setTitle("Java Core Swing GUI project - Welcome");

        background.setLayout(null);
        background.setSize(new java.awt.Dimension(1920, 1080));

        logo.setIcon(new ImageIcon("App/src/storage/logo_1m.png"));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setVerticalAlignment(SwingConstants.CENTER);
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startProgram(evt);
            }
        });
        logo.setBounds(0, -85, 1920, 1080);

        background.add(logo);

        phrase.setFont(new java.awt.Font("Consolas", 1, 16));
        phrase.setForeground(new java.awt.Color(102, 0, 0));
        phrase.setHorizontalAlignment(SwingConstants.CENTER);
        phrase.setVerticalAlignment(SwingConstants.CENTER);
        phrase.setText("INTERACT WITH A BOOK");
        phrase.setToolTipText("");
        phrase.setBounds(0, 0, 1920, 1080);

        background.add(phrase);

        window.setIcon(new ImageIcon("App/src/storage/background.jpg"));
        window.setHorizontalAlignment(SwingConstants.CENTER);
        window.setVerticalAlignment(SwingConstants.CENTER);
        window.setOpaque(true);
        window.setPreferredSize(new java.awt.Dimension(1920, 1080));
        window.setBounds(0, 0, 1920, 1080);

        background.add(window);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.PREFERRED_SIZE, 1080, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void startProgram(java.awt.event.MouseEvent evt) {

        this.dispose();
        Login.main(null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }

}
