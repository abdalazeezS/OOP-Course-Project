package project;

import exceptions.AccountNotFoundException;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidPinException;
import exceptions.NotEnoughCashException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class Frame extends javax.swing.JFrame {

    private double amount;
    private static Bank bank;
    private BankAccount account;
    private Card card;
    private static CashDispenser cashDispenser;
    private final LoggerInterface logger;

    /**
     * Creates new form Frame
     *
     * @param logger
     */
    public Frame(LoggerInterface logger) {
        initComponents();
        this.logger = logger;
        logger.log(Messages.Log.ATM_STARTED);

        cashDispenser = new CashDispenser();
        bank = new Bank();

        displayConsole();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitFrame();
            }

        });
        moneyLabel.setVisible(false);

    }

    public void exitFrame() {
        logger.log(Messages.Log.ATM_STOPPED);
        System.exit(0);
    }

    public final void displayConsole() {
        consoleTextArea.setText(Messages.WELCOME
                + Messages.ATM_READY);
    }

    public void isExit(Card card) throws AccountNotFoundException {
        boolean exit = false;
        for (BankAccount b : bank.customers) {
            if (b.getCard().equals(card)) {
                exit = true;
                break;
            }
        }
        if (!exit) {
            logger.log(Messages.Log.INVALID_CARD_INSERTED);
            throw new AccountNotFoundException();
        }
    }

    public static boolean isVerifiedCard(BankAccount account, int pin) {
        for (BankAccount b : bank.customers) {
            if (b.equals(account) && b.getPin().equals(pin)) {
                return true;
            }
        }
        return false;
    }

    public void insertPin() throws InvalidPinException {
        String pin;
        int attempts = 2;

        pin = JOptionPane.showInputDialog(Messages.ENTER_PIN);

        boolean valid = CardReader.isVerifiedCard(account, pin);

        while (attempts > 0 && !valid) {

            pin = JOptionPane.showInputDialog("Wrong PIN !, Try Again you have " + attempts
                    + " attempts: ");

            if (CardReader.isVerifiedCard(account, pin)) {
                valid = true;
            }
            attempts--;

        }

        if (!valid) {
            logger.log(Messages.Log.WRONG_PIN);
            resetCardFields();
            bank.customers.removeIf(bankAccount -> bankAccount.getCard().equals(card));
            throw new InvalidPinException();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cardNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cardNoTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        insertButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        consoleTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        fiftyButton = new javax.swing.JButton();
        hundredButton = new javax.swing.JButton();
        hundredFiftyButton = new javax.swing.JButton();
        twoHundredButton = new javax.swing.JButton();
        enterSpecificAmountButton = new javax.swing.JButton();
        cancelWitdrawButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        withdrawButton = new javax.swing.JButton();
        enquiryButton = new javax.swing.JButton();
        cancelTransactionButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        backToTransactionButton = new javax.swing.JButton();
        exitFromWitdrawButton = new javax.swing.JButton();
        collectButton = new javax.swing.JButton();
        moneyLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ATM");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Card Name:");

        cardNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Card NO:");

        cardNoTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        insertButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        insertButton.setText("Insert Card");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Console", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        consoleTextArea.setEditable(false);
        consoleTextArea.setColumns(20);
        consoleTextArea.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        consoleTextArea.setRows(5);
        jScrollPane1.setViewportView(consoleTextArea);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Withdraw Operations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        fiftyButton.setText("50");
        fiftyButton.setEnabled(false);
        fiftyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiftyButtonActionPerformed(evt);
            }
        });

        hundredButton.setText("100");
        hundredButton.setEnabled(false);
        hundredButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hundredButtonActionPerformed(evt);
            }
        });

        hundredFiftyButton.setText("150");
        hundredFiftyButton.setEnabled(false);
        hundredFiftyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hundredFiftyButtonActionPerformed(evt);
            }
        });

        twoHundredButton.setText("200");
        twoHundredButton.setEnabled(false);
        twoHundredButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoHundredButtonActionPerformed(evt);
            }
        });

        enterSpecificAmountButton.setText("Enter a specific Amount");
        enterSpecificAmountButton.setEnabled(false);
        enterSpecificAmountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterSpecificAmountButtonActionPerformed(evt);
            }
        });

        cancelWitdrawButton.setText("Cancel Witdraw");
        cancelWitdrawButton.setEnabled(false);
        cancelWitdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelWitdrawButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fiftyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hundredButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hundredFiftyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(twoHundredButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(enterSpecificAmountButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelWitdrawButton)))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fiftyButton)
                    .addComponent(hundredButton)
                    .addComponent(hundredFiftyButton)
                    .addComponent(twoHundredButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterSpecificAmountButton)
                    .addComponent(cancelWitdrawButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaction Operations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        withdrawButton.setText("Withdraw");
        withdrawButton.setEnabled(false);
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });

        enquiryButton.setText("Balance Enquiry");
        enquiryButton.setEnabled(false);
        enquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enquiryButtonActionPerformed(evt);
            }
        });

        cancelTransactionButton.setText("Cancel");
        cancelTransactionButton.setEnabled(false);
        cancelTransactionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelTransactionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(withdrawButton)
                .addGap(18, 18, 18)
                .addComponent(enquiryButton)
                .addGap(28, 28, 28)
                .addComponent(cancelTransactionButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(withdrawButton)
                    .addComponent(enquiryButton)
                    .addComponent(cancelTransactionButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Balance Enquiry Operations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        backToTransactionButton.setText("Back to Transaction Menu");
        backToTransactionButton.setEnabled(false);
        backToTransactionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToTransactionButtonActionPerformed(evt);
            }
        });

        exitFromWitdrawButton.setText("Exit");
        exitFromWitdrawButton.setEnabled(false);
        exitFromWitdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitFromWitdrawButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(backToTransactionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitFromWitdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backToTransactionButton)
                    .addComponent(exitFromWitdrawButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        collectButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        collectButton.setText("Collect Money");
        collectButton.setEnabled(false);
        collectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectButtonActionPerformed(evt);
            }
        });

        moneyLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/money.jpg"))); // NOI18N
        moneyLabel.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cardNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cardNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(insertButton))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(moneyLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(collectButton)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cardNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cardNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(insertButton)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(10, 10, 10)
                .addComponent(collectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(moneyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed

        try {
            String cardName = cardNameTextField.getText();
            String no = cardNoTextField.getText();

            card = new Card(no, cardName);

            isExit(card);

            account = Card.getBankAccount(card);

            insertPin();

            setFieldsEditable(false);
            insertButton.setEnabled(false);

            consoleTextArea.setText(Messages.CARD_INSERTED);

            logger.log(Messages.Log.CARD_INSERTED + account.getInfo());
            consoleTextArea.setText(Messages.TRANSACTION_MENU);

            setTransactionsButtonsEnabled(true);

        } catch (AccountNotFoundException ex) {
            logger.log(Messages.Log.INVALID_CARD_INSERTED);
            JOptionPane.showMessageDialog(this, ex.getMessage());
            setFieldsEditable(true);
            resetCardFields();
        } catch (InvalidPinException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_insertButtonActionPerformed

    private void enterSpecificAmountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterSpecificAmountButtonActionPerformed
        amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the specific amount"));
        try {
            account.withdraw(amount);
            arrangAfterWithdraw();
        } catch (NotEnoughCashException | InsufficientBalanceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            setWitdrawButtonsEnabled(false);
            arrangeAfterCancel();
        }
    }//GEN-LAST:event_enterSpecificAmountButtonActionPerformed

    private void cancelWitdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelWitdrawButtonActionPerformed
        setWitdrawButtonsEnabled(false);
        arrangeAfterCancel();

    }//GEN-LAST:event_cancelWitdrawButtonActionPerformed

    private void cancelTransactionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelTransactionButtonActionPerformed
        setTransactionsButtonsEnabled(false);
        arrangeAfterCancel();
    }//GEN-LAST:event_cancelTransactionButtonActionPerformed

    private void exitFromWitdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitFromWitdrawButtonActionPerformed
        setBalanceEnquiryButtonsEnabled(false);
        setTransactionsButtonsEnabled(false);
        arrangeAfterCancel();

    }//GEN-LAST:event_exitFromWitdrawButtonActionPerformed

    private void enquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enquiryButtonActionPerformed
        JOptionPane.showMessageDialog(this, Messages.BALANCE + account.getBalance());
        consoleTextArea.setText("Choose Either go back to Transaction Menu \nOR Exit");
        setTransactionsButtonsEnabled(false);
        setBalanceEnquiryButtonsEnabled(true);
    }//GEN-LAST:event_enquiryButtonActionPerformed

    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawButtonActionPerformed
        setWitdrawButtonsEnabled(true);
        setTransactionsButtonsEnabled(false);
        consoleTextArea.setText(Messages.WITHDRAW_MENU);
    }//GEN-LAST:event_withdrawButtonActionPerformed

    private void fiftyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiftyButtonActionPerformed
        try {
            amount = Integer.parseInt(fiftyButton.getText());
            account.withdraw(amount);
            arrangAfterWithdraw();
        } catch (NotEnoughCashException | InsufficientBalanceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_fiftyButtonActionPerformed

    private void hundredButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hundredButtonActionPerformed
        try {
            amount = Integer.parseInt(hundredButton.getText());
            account.withdraw(amount);
            arrangAfterWithdraw();
        } catch (NotEnoughCashException | InsufficientBalanceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_hundredButtonActionPerformed

    private void hundredFiftyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hundredFiftyButtonActionPerformed
        try {
            amount = Integer.parseInt(hundredFiftyButton.getText());
            account.withdraw(amount);
            arrangAfterWithdraw();
        } catch (NotEnoughCashException | InsufficientBalanceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_hundredFiftyButtonActionPerformed

    private void twoHundredButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoHundredButtonActionPerformed
        try {
            amount = Integer.parseInt(twoHundredButton.getText());;
            account.withdraw(amount);
            arrangAfterWithdraw();
        } catch (NotEnoughCashException | InsufficientBalanceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_twoHundredButtonActionPerformed

    public void arrangeAfterCancel() {
        resetCardFields();
        setFieldsEditable(true);
        insertButton.setEnabled(true);
        displayConsole();
    }

    public void arrangAfterWithdraw() {
        JOptionPane.showMessageDialog(this, Messages.BALANCE + account.getBalance() + "$\n"
                + Messages.DISPENSER_CASH + CashDispenser.getCash(), "Operation Done Successfully", JOptionPane.INFORMATION_MESSAGE
        );
        consoleTextArea.setText("Please Collect Your Money");
        moneyLabel.setVisible(true);
        logger.log(Messages.Log.WITHDRAW + amount + " $ ");
        setWitdrawButtonsEnabled(false);
        resetCardFields();
        collectButton.setEnabled(true);

    }

    private void backToTransactionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToTransactionButtonActionPerformed
        setTransactionsButtonsEnabled(true);
        setBalanceEnquiryButtonsEnabled(false);
        consoleTextArea.setText(Messages.TRANSACTION_MENU);
    }//GEN-LAST:event_backToTransactionButtonActionPerformed

    private void collectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectButtonActionPerformed
        moneyLabel.setVisible(false);
        displayConsole();
        setFieldsEditable(true);
        insertButton.setEnabled(true);
        collectButton.setEnabled(false);
    }//GEN-LAST:event_collectButtonActionPerformed

    public void resetCardFields() {
        cardNameTextField.setText("");
        cardNoTextField.setText("");
    }

    public void setFieldsEditable(boolean status) {
        cardNameTextField.setEditable(status);
        cardNoTextField.setEditable(status);
    }

    public void setTransactionsButtonsEnabled(boolean status) {
        withdrawButton.setEnabled(status);
        enquiryButton.setEnabled(status);
        cancelTransactionButton.setEnabled(status);
    }

    public void setWitdrawButtonsEnabled(boolean status) {
        fiftyButton.setEnabled(status);
        hundredButton.setEnabled(status);
        hundredFiftyButton.setEnabled(status);
        twoHundredButton.setEnabled(status);
        enterSpecificAmountButton.setEnabled(status);
        cancelWitdrawButton.setEnabled(status);
    }

    public void setBalanceEnquiryButtonsEnabled(boolean status) {
        backToTransactionButton.setEnabled(status);
        exitFromWitdrawButton.setEnabled(status);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                cashDispenser.setInitialCash(100_000);
                ATMLogger aTMLogger = new ATMLogger();
                new Frame(aTMLogger).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToTransactionButton;
    private javax.swing.JButton cancelTransactionButton;
    private javax.swing.JButton cancelWitdrawButton;
    private javax.swing.JTextField cardNameTextField;
    private javax.swing.JTextField cardNoTextField;
    private javax.swing.JButton collectButton;
    private javax.swing.JTextArea consoleTextArea;
    private javax.swing.JButton enquiryButton;
    private javax.swing.JButton enterSpecificAmountButton;
    private javax.swing.JButton exitFromWitdrawButton;
    private javax.swing.JButton fiftyButton;
    private javax.swing.JButton hundredButton;
    private javax.swing.JButton hundredFiftyButton;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel moneyLabel;
    private javax.swing.JButton twoHundredButton;
    private javax.swing.JButton withdrawButton;
    // End of variables declaration//GEN-END:variables
}
