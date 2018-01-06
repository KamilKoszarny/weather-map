import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class MapFrame extends JFrame {
    private MapPanel mapPanel;
    private JLabel parLabel;
    private JTextArea parTextArea = new JTextArea();
    private Date date = new Date();
    private Calendar calendar = GregorianCalendar.getInstance();

    MapFrame(City[] cities){

        setSize(870, 570);
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
        parTextArea.setBounds(new Rectangle(570,60,160,460));
        parTextArea.setEditable(false);
        add(parTextArea);

        calendar.setTime(date);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(calendar.getTime());
        int maxHours = App.HOURS - hourOfDay;

        JSlider hourSlider = new JSlider(JSlider.VERTICAL, hourOfDay, hourOfDay + maxHours, hourOfDay);
        hourSlider.setInverted(true);
//        hourSlider.setMajorTickSpacing(6);
        hourSlider.setMinorTickSpacing(1);
        hourSlider.setSnapToTicks(true);
        hourSlider.setPaintTicks(true);
        hourSlider.setPaintTrack(true);
        SimpleDateFormat formatToDay = new SimpleDateFormat("EEE, dd MMM");
        Hashtable table = new Hashtable();
        table.put(hourOfDay, new JLabel(Integer.toString(hourOfDay) + ":00"));
        for (int hour = hourOfDay + 2; hour < hourOfDay + maxHours; hour += 1) {
            if (hour % 24 == 0) {
                calendar.add(Calendar.DATE, 1);
                String dayDate = formatToDay.format(calendar.getTime());
                table.put(hour, new JLabel(dayDate));
            }
            else if (hour % 6 == 0)
                table.put(hour, new JLabel(Integer.toString(hour % 24) + ":00"));
        }
        hourSlider.setLabelTable(table);
        hourSlider.setPaintLabels(true);
        hourSlider.setBounds(740, 60, 110, 460);
        hourSlider.addChangeListener(new HourSliderL());
        add(hourSlider);
    }

    void updateLabel(String parStrValue){
        parLabel.setText(parStrValue);
        parLabel.revalidate();
    }

    void updateTextArea(String temp, String feelTemp, String wind, String precipitation){
        parTextArea.setText
                        ("Coordinates:" +

                        "\nParameters:" +
                        "\nTemperature: " + temp +
                        "\nFeel temperature: " + feelTemp +
                        "\nWind: " + wind +
                        precipitation);
        parTextArea.revalidate();
    }

    private class ParComboBoxL implements ActionListener{
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

    public class HourSliderL implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            if(!source.getValueIsAdjusting()) {
                int hour = source.getValue();
                System.out.println(hour/24 + " " + hour%24);
                mapPanel.setHour(hour);
                mapPanel.repaint();
                mapPanel.requestFrameUpdate();
            }
        }
    }
}
