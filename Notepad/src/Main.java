import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.metal.*;
import java.awt.print.PrinterException;
import java.io.*;

class Notepad extends JFrame implements ActionListener {
    JTextArea t;
    JFrame f;

    //constructor of class
    Notepad(){
        f = new JFrame("Notepad");
        t = new JTextArea();

        //main menu bar
        JMenuBar menu = new JMenuBar();

        //main menu items
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu close = new JMenu("Close");


        //add main menu items to main menu bar
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //file drop down
        JMenuItem file1 = new JMenuItem( "New");
        JMenuItem file2 = new JMenuItem("Save");
        JMenuItem file3 = new JMenuItem("Open");
        JMenuItem file4 = new JMenuItem("Print");

        file1.addActionListener(this);
        file2.addActionListener(this);
        file3.addActionListener(this);
        file4.addActionListener(this);

        //adding menu items to the parent - file
        file.add(file1);
        file.add(file2);
        file.add(file3);
        file.add(file4);


        //edit drop down
        JMenuItem edit1 = new JMenuItem( "Cut");
        JMenuItem edit2 = new JMenuItem("Copy");
        JMenuItem edit3 = new JMenuItem("Paste");

        edit1.addActionListener(this);
        edit2.addActionListener(this);
        edit3.addActionListener(this);

        //adding menu items to the parent - edit
        edit.add(edit1);
        edit.add(edit2);
        edit.add(edit3);

        JMenuItem close1 = new JMenuItem("Close");
        close1.addActionListener(this);
        close.add(close1);

        //adding menu bar to jframe
        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(1000,1000);
        f.show();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();
        switch (s){
            case "New":
                t.setText("");
                break;
            case "Save":
                //opening a location of computer
                JFileChooser ji = new JFileChooser("Documents:");
                //open dialog box
                int ri = ji.showSaveDialog(null);
                //if user selects a file
                if(ri == JFileChooser.APPROVE_OPTION){

                    //extracting the selected file path
                    File fl = new File(ji.getSelectedFile().getAbsolutePath());


                    try{
                        //place pointer at start of the file
                        FileWriter fw = new FileWriter(fl);

                        //buffer reader to read the file
                        BufferedWriter bw = new BufferedWriter(fw);

                        bw.write(t.getText());
                        bw.flush();
                        bw.close();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex){
                        throw new RuntimeException(ex);
                    }

                }else{
                    //if user closed the file explorer
                    JOptionPane.showMessageDialog(ji,"operation cancelled");
                }
                break;
            case "Open":
                //opening a location of computer
                JFileChooser j = new JFileChooser("Documents:");
                //open dialog box
                int r = j.showOpenDialog(null);
                //if user selects a file
                if(r == JFileChooser.APPROVE_OPTION){

                    //extracting the selected file path
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    String s1, s2;
                    try{
                        //place pointer at start of the file
                        FileReader fr = new FileReader(fi);

                        //buffer reader to read the file
                        BufferedReader br = new BufferedReader(fr);

                        //storing first line in s1
                        s1 = br.readLine();

                        //appending subsequent lines till the end of file
                        while((s2=br.readLine()) != null){
                            s1 = s1+"\n"+s2;
                        }
                        //copying into notepad
                        t.setText(s1);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex){
                        throw new RuntimeException(ex);
                    }

                }else{
                    //if user closed the file explorer
                    JOptionPane.showMessageDialog(j,"operation cancelled");
                }
                break;
            case "Print":
                try{
                    t.print();
                } catch (PrinterException ex){
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;
        }
    }

    public static void main(String[] args){
        Notepad note = new Notepad();
    }
}

