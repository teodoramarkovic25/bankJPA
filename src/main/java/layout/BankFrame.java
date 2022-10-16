package layout;
import entity.BankAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BankFrame extends JFrame {


    private JLabel fromAccountLabel = new JLabel("From ");
    private JLabel toAccountLabel = new JLabel("To ");
    private JLabel amountLabel = new JLabel("Amount ");
    private JComboBox<BankAccount> fromAccountComboBox = new JComboBox<>();
    private JComboBox<BankAccount> toAccountComboBox = new JComboBox<>();
    private JTextField amountTextField = new JTextField(10);
    private JButton transferAmountButton = new JButton("Transfer Money");


    public BankFrame() {
        setTitle("Bank account transfer screen");
        setSize(400, 250);
        setLayout(new GridLayout(4, 1));
        JPanel fromPanel = new JPanel();
        fromPanel.add(fromAccountLabel);
        fromPanel.add(fromAccountComboBox);
        add(fromPanel);
        JPanel toPanel = new JPanel();
        toPanel.add(toAccountLabel);
        toPanel.add(toAccountComboBox);
        add(toPanel);
        JPanel amountPanel = new JPanel();
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);
        add(amountPanel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(transferAmountButton);
        add(buttonPanel);
        fillBankAccountComboBoxes();
        transferAmountButton.addActionListener(this::transferMoney);
    }


    private void fillBankAccountComboBoxes() {
        List<BankAccount> bankAccounts = BankAccount.loadAll();
        for (BankAccount bankAccount : bankAccounts) {

            fromAccountComboBox.addItem(bankAccount);
            toAccountComboBox.addItem(bankAccount);
        }
    }

    private void transferMoney(ActionEvent e) {
        BankAccount fromBankAccount = (BankAccount) fromAccountComboBox.getSelectedItem();
        BankAccount toBankAccount = (BankAccount) toAccountComboBox.getSelectedItem();
        Double amount = Double.parseDouble(amountTextField.getText());
        BankAccount.transferMoney(fromBankAccount, toBankAccount, amount);

        // fillBankAccountComboBoxes();
        //fromAccountComboBox.setSelectedItem(fromBankAccount);
        //toAccountComboBox.setSelectedItem(toBankAccount);


        fromAccountComboBox.removeAllItems();
        toAccountComboBox.removeAllItems();
        fillBankAccountComboBoxes();

        fromAccountComboBox.setSelectedItem(fromBankAccount);
        toAccountComboBox.setSelectedItem(toBankAccount);
        amountTextField.setText("");




    }

    public void showFrame() {
        pack();
        setVisible(true);

    }
}

