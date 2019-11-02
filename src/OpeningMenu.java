import Entities.Bank;
import Entities.Manager;

import javax.swing.*;
import java.awt.*;


/**
 * Opening menu where user will choose which type of client will be used
 */
public class OpeningMenu extends JFrame{
    private String radioChoice;
    private Bank bank;

    public OpeningMenu(Bank bank) {
        this.bank=bank;
        this.radioChoice="";
        JPanel panel= new JPanel();
        JLabel label = new JLabel("Please choose you account type");
        add(label);

        setLayout(new GridLayout(4,2));
        JRadioButton manager = new JRadioButton("Manager");
        manager.addActionListener(e -> {
            this.radioChoice="Manager";
        });
        JRadioButton client = new JRadioButton("Client");
        client.addActionListener(e -> {
            this.radioChoice="Client";
        });
        ButtonGroup accountType = new ButtonGroup();
        accountType.add(manager);
        accountType.add(client);
        panel.add(manager);
        panel.add(client);

        JButton submit=new JButton("Submit");
        submit.addActionListener(e -> {
            if(this.radioChoice.equals("Manager")){
                ManagerMenu managerMenu = new ManagerMenu(this.bank);
                dispose();
            }else if(this.radioChoice.equals("Client")){
                ClientConfirmationPage clientConfirmationPage=new ClientConfirmationPage(this.bank);
                dispose();
            }else{
                JOptionPane.showMessageDialog(new Frame(),"Please choose account type");
            }
        });
        panel.add(submit);

        add(panel);
        setTitle("Welcome");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
