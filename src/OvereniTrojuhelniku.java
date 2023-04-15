import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class OvereniTrojuhelniku extends JFrame{

    private JPanel panel;
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JButton overeniTrojuhelnikuButton;
    private JButton kopirovani;

    private JFileChooser chooser = new JFileChooser(".");

    public OvereniTrojuhelniku() {

        setTitle("Trojúhelník");

        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        JMenu menuSoubor = new JMenu("Soubor");

        JMenu menuAkce = new JMenu("Akce");


        menuBar.add(menuSoubor);
        menuBar.add(menuAkce);

        JMenuItem nacti = new JMenuItem("Načti...");


        nacti.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e)
            {
                vyberSouboru();
            }
        });


        JMenuItem uloz = new JMenuItem("Ulož...");

        uloz.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                ulozeniSouboru();
            }
        });

        menuSoubor.add(nacti);
        menuSoubor.add(uloz);

        JMenuItem kopiruj = new JMenuItem("Kopíruj A do B i C");

        kopiruj.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                kopiruj();
            }
        });


        JMenuItem overeniItem = new JMenuItem("Lze sestrojit trojúhelník?");

        overeniItem.addActionListener(new ActionListener()

        {
            @Override
            public void actionPerformed(ActionEvent e) {
                overeniTrojuhelniku();
            }
        });
        menuAkce.add(kopiruj);
        menuAkce.add(overeniItem);


        kopirovani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kopiruj();
            }
        });

        overeniTrojuhelnikuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overeniTrojuhelniku();
            }
        });
    }

    private void kopiruj() {
        String cislo = textFieldA.getText();
        textFieldB.setText(cislo);
        textFieldC.setText(cislo);
    }

    private void overeniTrojuhelniku() {

        int a = Integer.parseInt(textFieldA.getText());
        int b = Integer.parseInt(textFieldB.getText());
        int c = Integer.parseInt(textFieldC.getText());


        if(a+b > c)

        {
            JOptionPane.showMessageDialog(null, "Ze stran délek A: "+a+", B: "+b+" a C: "+c+" lze sestrojit trojúhelník");
        }

        else
        {
            JOptionPane.showMessageDialog(null, "Ze stran délek A: "+a+", B: "+b+" a C: "+c+" nelze sestrojit trojúhelník");
        }

    }
    private void vyberSouboru() {

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = chooser.getSelectedFile();

            nacteniSouboru(selectedFile);
        }
    }

    private void nacteniSouboru(File selectedFile){
        try (
                Scanner scanner =
                        new Scanner(new BufferedReader(
                                new FileReader(selectedFile)))
        ) {
            while (scanner.hasNextLine())
            {

                String radek = scanner.nextLine();

                String[] polozky = radek.split(";");

                textFieldA.setText(polozky[0]);
                textFieldB.setText(polozky[1]);
                textFieldC.setText(polozky[2]);


            }
        } catch (FileNotFoundException e)
        {

            throw new RuntimeException(e);

        }
    }
    private void ulozeniSouboru() {


        int result = chooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = chooser.getSelectedFile();

            ulozSoubor(selectedFile);


        }
    }

    private void ulozSoubor(File soubor) {

        try (PrintWriter writer = new PrintWriter(

                new BufferedWriter(new FileWriter(soubor)))

        ) {

            writer.print(""+textFieldA.getText()+";"+textFieldB.getText()+";"+textFieldC.getText());

        }

        catch (IOException e)

        {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }


    public static void main(String[] args) {

        OvereniTrojuhelniku GUI = new OvereniTrojuhelniku();
        GUI.setContentPane(GUI.panel);
        GUI.pack();
        GUI.setVisible(true);
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}