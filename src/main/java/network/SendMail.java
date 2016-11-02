package network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2016/4/5.
 */
public class SendMail extends JFrame {

    boolean frameSizeAdjusted = false;
    JLabel fromLabel = new JLabel();
    JLabel toLabel = new JLabel();
    JLabel subjectLabel = new JLabel();
    JLabel serverLabel = new JLabel();
    JTextField _from = new JTextField();
    JTextField _to = new JTextField();
    JTextField _subject = new JTextField();
    JTextField _smtp = new JTextField();
    JScrollPane _scrollPane2 = new JScrollPane();
    JTextArea _body = new JTextArea();
    JButton _send = new JButton();
    JButton _cancel = new JButton();
    JScrollPane _scrollPane = new JScrollPane();
    JList _output = new JList();
    DefaultListModel _model = new DefaultListModel();
    BufferedReader _in;
    PrintWriter _out;


    public SendMail() throws HeadlessException {
        setTitle("SendMail Example");
        getContentPane().setLayout(null);
        setSize(736, 312);
        setVisible(false);
        
        fromLabel.setText("From:");
        getContentPane().add(fromLabel);
        fromLabel.setBounds(12, 12, 36, 12);
        
        toLabel.setText("To:");
        getContentPane().add(toLabel);
        toLabel.setBounds(12, 48, 36, 12);

        subjectLabel.setText("Subject:");
        getContentPane().add(subjectLabel);
        subjectLabel.setBounds(12, 84, 48, 12);

        serverLabel.setText("SMTP Server:");
        getContentPane().add(serverLabel);
        serverLabel.setBounds(12, 120, 84, 12);

        getContentPane().add(_from);
        _from.setBounds(96, 12, 300, 24);

        getContentPane().add(_to);
        _to.setBounds(96, 48, 300, 24);

        getContentPane().add(_subject);
        _subject.setBounds(96, 84, 300, 24);

        getContentPane().add(_smtp);
        _smtp.setBounds(96, 120, 300, 24);

        getContentPane().add(_scrollPane2);
        _scrollPane2.setBounds(12, 156, 384, 108);

        _body.setText("Enter your message here");
        _scrollPane2.getViewport().add(_body);
        _body.setBounds(0, 0, 381, 105);

        _send.setText("Send");
        _send.setActionCommand("Send");
        getContentPane().add(_send);
        _send.setBounds(60, 276, 132, 24);

        _cancel.setText("Cancel");
        _cancel.setActionCommand("Cancel");
        getContentPane().add(_cancel);
        _cancel.setBounds(216, 276, 120, 24);

        getContentPane().add(_scrollPane);
        _scrollPane.setBounds(408, 12, 312, 288);

        getContentPane().add(_output);
        _output.setBounds(408, 12, 309, 285);

        SymAction symAction = new SymAction();
        _send.addActionListener(symAction);
        _cancel.addActionListener(symAction);
        _output.setModel(_model);
        _model.addElement("Server output displayed here:");
        _scrollPane.getViewport().setView(_output);
        _scrollPane2.getViewport().setView(_body);

    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            setLocation(50, 50);
        }
        super.setVisible(b);

    }

    class SymAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object object = e.getSource();
            if (object == _send) {
                sendActionPerformed(e);
            } else if (object == _cancel) {
                cancelActionPerformed(e);
            }
        }
    }

    private void cancelActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void sendActionPerformed(ActionEvent e) {
        try {
            Socket s = new Socket(_smtp.getText(), 25);
            _out = new PrintWriter(s.getOutputStream());
            _in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            send(null);
            send("HELO " + InetAddress.getLocalHost().getHostName());
            send("MAIL FROM: " + _from.getText());
            send("RCPT TO: " + _to.getText());
            send("DATA");
            _out.println("Subject:" + _subject.getText());
            _out.println(_body.getText());
            send(".");
            s.close();
        } catch (IOException e1) {
            _model.addElement("Error: " + e);
        }
    }

    protected void send(String s) throws IOException {
        if (s != null) {
            _model.addElement("C:" + s);
            _out.println(s);
            _out.flush();
        }
        String line = _in.readLine();
        if (line != null) {
            _model.addElement("S:" + line);
        }
    }

    public static void main(String[] args) {
        (new SendMail()).show();
    }

    @Override
    public void addNotify() {
        Dimension size = getSize();
        super.addNotify();

        if(frameSizeAdjusted)
            return;
        frameSizeAdjusted = true;

        Insets insets = getInsets();
        JMenuBar menuBar = getRootPane().getJMenuBar();
        int menuBarHeight = 0;
        if (menuBar != null) {
            menuBarHeight = menuBar.getPreferredSize().height;
        }
        setSize(insets.left+insets.right+size.width,insets.top+insets.bottom+size.height+menuBarHeight);
    }
}
