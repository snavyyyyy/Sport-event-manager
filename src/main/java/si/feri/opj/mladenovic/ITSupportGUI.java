package si.feri.opj.mladenovic;

import si.feri.opj.mladenovic.event.ConcreteVenue;
import si.feri.opj.mladenovic.event.Event;
import si.feri.opj.mladenovic.event.Venue;
import si.feri.opj.mladenovic.match.Athlete;
import si.feri.opj.mladenovic.match.Match;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ITSupportGUI extends JFrame {

    private ArrayList<Venue> venueList = new ArrayList<>();
    private ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<Athlete> athleteList = new ArrayList<>();
    private JButton addVenueButton;
    private JButton editVenueButton;
    private JButton deleteVenueButton;
    private JButton addEventButton;
    private JButton editEventButton;
    private JButton deleteEventButton;

    private JButton addAthleteButton;
    private JButton editAthleteButton;
    private JButton deleteAthleteButton;

    private JButton manageAthletesButton;
    private JButton manageMatchesButton;
    
    public ITSupportGUI() {
        setTitle("Sports Event Management IT Support");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        initializeComponents();

        setLayout(new GridLayout(4, 3));

        add(addVenueButton);

        add(addEventButton);

        add(addAthleteButton);

        add(manageAthletesButton);
        add(manageMatchesButton);

        setVisible(true);
    }
    private void initializeComponents() {
        addVenueButton = new JButton("Venues");

        addEventButton = new JButton("Events");

        addAthleteButton = new JButton("Athlethes");
        editAthleteButton = new JButton("Edit Athlete");
        deleteAthleteButton = new JButton("Delete Athlete");

        manageAthletesButton = new JButton("Manage Athletes in Events");
        manageMatchesButton = new JButton("Manage Matches in Venues");

        addVenueButton.addActionListener(e -> this.openAddVenueWindow());
        
        addEventButton.addActionListener(e -> this.openAddEventWindow());
        
        addAthleteButton.addActionListener(e-> this.openAddAthleteWindow());

        manageMatchesButton.addActionListener(e  -> this.openManageMatchWindow());

        manageAthletesButton.addActionListener(e ->this.openManageAthleteWindow());
    }

    private JTextField athleteNameTextField;
    private JComboBox<String> eventComboBox;
    private void openManageAthleteWindow() {
        setTitle("Check Athlete Participation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);

        initializeComponents2();

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Enter Athlete Name:"));
        panel.add(athleteNameTextField);
        panel.add(new JLabel("Select Event:"));
        panel.add(eventComboBox);

        JButton checkParticipationButton = new JButton("Check Participation");
        checkParticipationButton.addActionListener(e -> checkParticipation());
        panel.add(checkParticipationButton);

        add(panel);

        setVisible(true);
    }
    private void initializeComponents2() {
        athleteNameTextField = new JTextField();
        eventComboBox = new JComboBox<>();
        for (Event event : eventList) {
            eventComboBox.addItem(event.getName());
        }
    }
    private void checkParticipation() {
        String athleteName = athleteNameTextField.getText().trim();
        String selectedEventName = (String) eventComboBox.getSelectedItem();
        Event selectedEvent = null;
        for (Event event : eventList) {
            if (event.getName().equals(selectedEventName)) {
                selectedEvent = event;
                break;
            }
        }

        if (selectedEvent != null) {
            boolean athleteParticipating = false;
            for (Athlete athlete : selectedEvent.getParticipants()) {
                if (athlete.getName().equalsIgnoreCase(athleteName)) {
                    athleteParticipating = true;
                    break;
                }
            }
            if (athleteParticipating) {
                JOptionPane.showMessageDialog(this, "The athlete is participating in the selected event.");
            } else {
                JOptionPane.showMessageDialog(this, "The athlete is not participating in the selected event.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selected event not found.");
        }
    }

    private void openManageMatchWindow() {
          JFrame addMatchFrame = new JFrame("Add Match");
          addMatchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          addMatchFrame.setSize(400, 150);
          addMatchFrame.setLocationRelativeTo(null);

          JPanel panel = new JPanel(new GridLayout(2, 1));

          JTextField matchNameTextField = new JTextField();
          panel.add(new JLabel("Enter Match Name:"));
          panel.add(matchNameTextField);


          JButton checkValidityButton = new JButton("Check Validity");
          checkValidityButton.addActionListener(e -> {
              String matchName = matchNameTextField.getText().trim();
              if (!matchName.isEmpty()) {
                  boolean matchFound = false;
                  for (Venue venue : venueList) {
                      for (Match match : venue.getListOfMatches()) {
                          if (match.getName().equalsIgnoreCase(matchName)) {
                              matchFound = true;
                              if (match.checkValidity(venue)) {
                                  JOptionPane.showMessageDialog(addMatchFrame, "The match " + match.getName() + " can take place in " + venue.getName());
                              } else {
                                  JOptionPane.showMessageDialog(addMatchFrame, "The match " + match.getName() + " has not been added to " + venue.getName());
                              }
                          }
                      }
                  }
                  if (!matchFound) {
                      JOptionPane.showMessageDialog(addMatchFrame, "Match not found in any venue.");
                  }
              } else {
                  JOptionPane.showMessageDialog(addMatchFrame, "Please enter a match name.");
              }
          });

          panel.add(checkValidityButton);

          addMatchFrame.add(panel);
          addMatchFrame.setVisible(true);
      }

    private void openDeleteAthleteWindow() {
            int athleteToDelete = athleteJList.getSelectedIndex();
            if(athleteJList.getSelectedIndex()!=-1) {
                athleteList.remove(athleteToDelete);
                athleteListModel.clear();
                athleteListModel.addAll(athleteList);
            }
    }

    private void openEditAthleteWindow() {
        if(athleteJList.getSelectedIndex()!=-1) {
            Athlete selectedAthlete = athleteList.get(athleteJList.getSelectedIndex());
            if (selectedAthlete != null) {
                selectedAthlete.setName(nameAthleteTextField.getText());
                selectedAthlete.setSurname(surnameAthleteTextField.getText());
                selectedAthlete.setAthleteNumber(Integer.parseInt(numberTextField.getText()));
                selectedAthlete.setDateOfBirth(LocalDate.parse(dobTextField.getText()));

                athleteListModel.clear();
                athleteListModel.addAll(athleteList);
            }
        }
        };

    private Athlete findAthleteByNumber(int athleteNumber) {
        for (Athlete athlete : athleteList) {
            if (athlete.getAthleteNumber() == athleteNumber) {
                return athlete;
            }
        }
        return null;
    }
    JLabel nameLabel = new JLabel("Name:");
    JTextField nameAthleteTextField = new JTextField();

    JLabel surnameLabel = new JLabel("Surname:");
    JTextField surnameAthleteTextField = new JTextField();

    JLabel numberLabel = new JLabel("Athlete Number:");
    JTextField numberTextField = new JTextField();

    JLabel dobLabel = new JLabel("Date of Birth (yyyy-MM-dd):");
    JTextField dobTextField = new JTextField();

    JList athleteJList = new JList();

    DefaultListModel athleteListModel = new DefaultListModel<Athlete>();
    private void openAddAthleteWindow() {
        JFrame addAthleteFrame = new JFrame("Add Athlete");
        addAthleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addAthleteFrame.setSize(400, 300);
        addAthleteFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        athleteJList.addListSelectionListener(e -> {
            Athlete athlete = (Athlete) athleteJList.getSelectedValue();
            if(athlete != null){
                nameAthleteTextField.setText(athlete.getName());
                surnameAthleteTextField.setText(athlete.getSurname());
                numberTextField.setText(String.valueOf(athlete.getAthleteNumber()));
                dobTextField.setText(athlete.getDateOfBirth().toString());
            }
        });

        athleteListModel.addAll(athleteList);
        athleteJList.setModel(athleteListModel);

        panel.add(nameLabel);
        panel.add(nameAthleteTextField);
        panel.add(surnameLabel);
        panel.add(surnameAthleteTextField);
        panel.add(numberLabel);
        panel.add(numberTextField);
        panel.add(dobLabel);
        panel.add(dobTextField);
        panel.add(athleteJList);
        panel.add(editAthleteButton);
        panel.add(deleteAthleteButton);

        editAthleteButton.addActionListener(e -> this.openEditAthleteWindow());
        deleteAthleteButton.addActionListener(e -> this.openDeleteAthleteWindow());

        JButton addButton = new JButton("Add Athlete");
        addButton.addActionListener(e -> {
            String name = nameAthleteTextField.getText();
            String surname = surnameAthleteTextField.getText();
            int athleteNumber = Integer.parseInt(numberTextField.getText());
            LocalDate dob = LocalDate.parse(dobTextField.getText());

            Athlete athlete = new Athlete(name, surname, athleteNumber,dob);
            addAthlete(athlete);

            athleteListModel.clear();
            athleteListModel.addAll(athleteList);

        });

        addAthleteFrame.add(panel, BorderLayout.CENTER);
        addAthleteFrame.add(addButton, BorderLayout.SOUTH);
        addAthleteFrame.setVisible(true);
    }

    private void addAthlete(Athlete athlete) {
        athleteList.add(athlete);
        JOptionPane.showMessageDialog(null, "Athlete added successfully.");
    }
    private void openDeleteEventWindow() {
            deleteEventByName();
        };
    private void deleteEventByName() {
        String eventName = nameEventTextField.getText();
        Event eventToDelete = findEventByName();
        eventList.remove(eventToDelete);
        eventListModel.clear();
        listModel.addAll(eventList);
        nameEventTextField.setText("");
        scheduleTextField.setText("");
        canceledCheckBox.setSelected(false);
    }
    DefaultListModel eventListModel = new DefaultListModel<Event>();
    JTextField scheduleTextField = new JTextField();
    JCheckBox canceledCheckBox = new JCheckBox();
    private void openAddEventWindow() {
        JFrame addEventFrame = new JFrame("Events");
        addEventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEventFrame.setSize(600, 400);
        addEventFrame.setLocationRelativeTo(this);
        CardLayout crd;


        editEventButton = new JButton("Edit Event");
        deleteEventButton = new JButton("Delete Event");

        editEventButton.addActionListener(e -> this.openEditEvent());
        deleteEventButton.addActionListener(e -> this.openDeleteEventWindow());

        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField();

        JLabel scheduleLabel = new JLabel("Schedule (yyyy-MM-dd HH:mm):");

        JLabel canceledLabel = new JLabel("Canceled:");

        panel.add(nameLabel);
        panel.add(nameEventTextField);
        panel.add(scheduleLabel);
        panel.add(scheduleTextField);
        panel.add(canceledLabel);
        panel.add(canceledCheckBox);
        panel.add(editEventButton);
        panel.add(deleteEventButton);

        JList eventJList = new JList();
        eventListModel.addAll(eventList);
        eventJList.setModel(eventListModel);
        JScrollPane scrollPane = new JScrollPane(eventJList);

        eventJList.addListSelectionListener(e -> {
            Event event = (Event) eventJList.getSelectedValue();
            if(event != null){
                nameEventTextField.setText(event.getName());
                scheduleTextField.setText(event.getSchedule().toString());
                canceledCheckBox.setSelected(event.isCanceled());
            }
        });
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> {
            String name = nameEventTextField.getText();
            String scheduleText = scheduleTextField.getText();
            boolean canceled = canceledCheckBox.isSelected();

            try {
                LocalDateTime schedule = LocalDateTime.parse(scheduleText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Event event = new Event(name, schedule, canceled);
                addEvent(event);

                JOptionPane.showMessageDialog(addEventFrame, "Event added successfully.");
                nameEventTextField.setText("");
                scheduleTextField.setText("");
                canceledCheckBox.setSelected(false);
                eventListModel.clear();
                eventListModel.addAll(eventList);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(addEventFrame, "Invalid schedule format. Please enter in yyyy-MM-dd HH:mm format.");
            }
        });

        addEventFrame.add(panel, BorderLayout.NORTH);
        addEventFrame.add(scrollPane, BorderLayout.CENTER);
        addEventFrame.add(addEventButton, BorderLayout.SOUTH);


        addEventFrame.setVisible(true);
    }
    private void addEvent(Event event) {
        eventList.add(event);
        eventComboBox.addItem(event.getName());
    }
    JTextField nameEventTextField = new JTextField();
    private void openEditEvent() {
            String eventName = nameEventTextField.getText();
            Event event = findEventByName();
            if (event != null) {
                openEditEventDetailsWindow(event);
            }
        };
    private Event findEventByName() {
        String eventName = nameEventTextField.getText();
        for (Event event : eventList) {
            if (event.getName().equalsIgnoreCase(eventName)) {
                return event;
            }
        }
        return null;
    }
    private void openEditEventDetailsWindow(Event event) {
        JFrame editEventDetailsFrame = new JFrame("Edit Event Details");
        editEventDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editEventDetailsFrame.setSize(400, 200);
        editEventDetailsFrame.setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField(event.getName());

        JLabel scheduleLabel = new JLabel("Schedule (yyyy-MM-dd HH:mm):");
        JTextField scheduleTextField = new JTextField(event.getSchedule().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        JLabel canceledLabel = new JLabel("Canceled:");
        JCheckBox canceledCheckBox = new JCheckBox();
        canceledCheckBox.setSelected(event.isCanceled());

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(scheduleLabel);
        panel.add(scheduleTextField);
        panel.add(canceledLabel);
        panel.add(canceledCheckBox);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String newName = nameTextField.getText();
            LocalDateTime newSchedule = LocalDateTime.parse(scheduleTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            boolean newCanceled = canceledCheckBox.isSelected();

            editEvent(event, newName, newSchedule, newCanceled);
            JOptionPane.showMessageDialog(editEventDetailsFrame, "Event details updated successfully.");
            editEventDetailsFrame.dispose();
        });

        editEventDetailsFrame.add(panel, BorderLayout.NORTH);
        editEventDetailsFrame.add(saveButton, BorderLayout.SOUTH);

        editEventDetailsFrame.setVisible(true);
    }
    private void editEvent(Event event, String newName, LocalDateTime newSchedule, boolean newCanceled) {

        event.setName(newName);
        event.setSchedule(newSchedule);
        event.setCanceled(newCanceled);
    }
    public void openDeleteVenueWindow() {
        String venueName = nameTextField.getText();
            boolean found = false;
            for (Venue venue : venueList) {
                if (venue.getName().equalsIgnoreCase(venueName)) {
                    found = true;
                    deleteVenue(venue);
                    break;
            }
        }
    }
    private void deleteVenue(Venue venue) {
        venueList.remove(venue);
        listModel.clear();
        listModel.addAll(venueList);
        nameTextField.setText("");
        phoneNumberTextField.setText("");
        matchesTextField.setText("");
    }
    JTextField nameTextField = new JTextField();

    JLabel phoneNumberLabel = new JLabel("Phone Number:");
    JTextField phoneNumberTextField = new JTextField();

    JLabel matchesLabel = new JLabel("Number of Matches:");
    JTextField matchesTextField = new JTextField();
    JList venueInfoJList=new JList<>();
    DefaultListModel listModel=new DefaultListModel<Venue>();
    public void openAddVenueWindow() {
        editVenueButton=new JButton("Edit Venue");
        deleteVenueButton=new JButton("Delete Venue");

        editVenueButton.addActionListener(e -> this.openEditVenueWindow());
        deleteVenueButton.addActionListener(e -> this.openDeleteVenueWindow());

        JFrame addVenueFrame = new JFrame("Add Venue");
        addVenueFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addVenueFrame.setSize(400, 300);
        addVenueFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberTextField);
        panel.add(matchesLabel);
        panel.add(matchesTextField);
        panel.add(editVenueButton);
        panel.add(deleteVenueButton);

        JButton addButton = new JButton("Add Venue");
        addButton.addActionListener(e -> {
            String name = nameTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();

            int matches = Integer.parseInt(matchesTextField.getText());
            ConcreteVenue venue = new ConcreteVenue(name, phoneNumber, matches);
            venueList.add(venue);

            nameTextField.setText("");
            phoneNumberTextField.setText("");
            matchesTextField.setText("");
        });

       listModel.addAll(venueList);
       venueInfoJList.setModel(listModel);

       venueInfoJList.addListSelectionListener(e -> {
           Venue venue = (Venue) venueInfoJList.getSelectedValue();
           if(venue != null) {
               nameTextField.setText(venue.getName());
               phoneNumberTextField.setText(venue.getPhoneNumber());
               matchesTextField.setText(String.valueOf(venue.getListOfMatches().length));
           }
       });
        JScrollPane scrollPane = new JScrollPane(venueInfoJList);

        JButton printVenuesButton = new JButton("Venues");
        printVenuesButton.addActionListener(e -> {
            listModel.clear();
            listModel.addAll(venueList);
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(printVenuesButton);

        addVenueFrame.add(panel, BorderLayout.NORTH);
        addVenueFrame.add(scrollPane, BorderLayout.CENTER);
        addVenueFrame.add(buttonPanel, BorderLayout.SOUTH);

        addVenueFrame.setVisible(true);
    }
    public void openEditVenueWindow() {
        String venueName=nameTextField.getText();
            boolean found = false;
            for (Venue venue : venueList) {
                if (venue.getName().equalsIgnoreCase(venueName)) {
                    found = true;
                    editVenueData(venue);
                    break;
                }
            }
        }
    private void editVenueData(Venue venue) {
            String newName = nameTextField.getText();
            String newPhoneNumber = phoneNumberTextField.getText();
            int newMatches = Integer.parseInt(matchesTextField.getText());
            venue.setName(newName);
            venue.setPhoneNumber(newPhoneNumber);
            venue.setListOfMatches(new Match[newMatches]);
            listModel.clear();
            listModel.addAll(venueList);
    }
}
