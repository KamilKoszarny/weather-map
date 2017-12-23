import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MapFrame extends JFrame {
    private MapPanel mapPanel;
    private JLabel parLabel;
    private JTextArea parTextArea = new JTextArea();


    MapFrame(City[] cities){

        setSize(850, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        mapPanel = new MapPanel(cities, "Temperature", this);

        addComponents();

        setVisible(true);
    }

    private void addComponents(){
        mapPanel.setBounds(new Rectangle(20,20, MapPanel.MAP_WIDTH, MapPanel.MAP_HEIGHT));
        add(mapPanel);
        String[] parameters = {"Temperature", "Temperature (feel)", "Wind", "Precipitation"};

        JComboBox parComboBox = new JComboBox(parameters);
        parComboBox.setBounds(new Rectangle(570,20,150,20));
        parComboBox.addActionListener(new ParComboBoxL());
        add(parComboBox);

        parLabel = new JLabel("XX \u00b0C");
        parLabel.setBounds(new Rectangle(730,20,120,20));
        add(parLabel);

        parTextArea = new JTextArea("Parameters:");
        parTextArea.setBounds(new Rectangle(570,60,170,460));
        parTextArea.setEditable(false);
        add(parTextArea);

        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        JSlider hourSlider = new JSlider(JSlider.VERTICAL, hourOfDay, hourOfDay + App.HOURS, hourOfDay);
        hourSlider.setMajorTickSpacing(6);
        hourSlider.setMinorTickSpacing(1);
        hourSlider.setPaintTicks(true);
        hourSlider.setPaintTrack(true);
        hourSlider.setBounds(750, 60, 50, 460);
        add(hourSlider);
    }

    void updateLabel(String parStrValue){
        parLabel.setText(parStrValue);
        parLabel.revalidate();
    }

    void updateTextArea(String temp, String feelTemp, String wind, String precipitation){
        parTextArea.setText
                        ("Parameters:" +
                        "\nTemperature: " + temp +
                        "\nFeel temperature: " + feelTemp +
                        "\nWind: " + wind +
                        precipitation);
        parTextArea.revalidate();
    }

    public class ParComboBoxL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String parameter = (String)cb.getSelectedItem();
            System.out.println(parameter);
            mapPanel.setParameter(parameter);
            mapPanel.repaint();
            mapPanel.requestFrameUpdate();
        }
    }


}
