package petcare.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import petcare.model.*;
import petcare.service.PetCareSystem;
import petcare.exception.AlreadyAdoptedException;
import petcare.exception.InvalidPetAgeException;
import petcare.util.PetStatus;

public class MainFrame extends JFrame implements ActionListener {

    private PetCareSystem system;

    private JTextField idField;
    private JTextField nameField;
    private JTextField breedField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField typeField;
    private JTextField priceField;

    private JButton addButton;
    private JButton viewButton;
    private JButton adoptButton;

    private JTextArea output;

    public MainFrame(PetCareSystem system) {

        this.system = system;

        setTitle("Pet Care System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Pet ID:"));

        idField = new JTextField(8);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));

        nameField = new JTextField(8);
        inputPanel.add(nameField);


        inputPanel.add(new JLabel("Breed:"));

        breedField = new JTextField(8);
        inputPanel.add(breedField);


        inputPanel.add(new JLabel("Age:"));

        ageField = new JTextField(3);
        inputPanel.add(ageField);


        inputPanel.add(new JLabel("Gender:"));

        genderField = new JTextField(6);
        inputPanel.add(genderField);


        inputPanel.add(new JLabel("Type: (Dog / Cat / Bird)"));

        typeField = new JTextField(6);
        inputPanel.add(typeField);


        inputPanel.add(new JLabel("Price:"));

        priceField = new JTextField(6);
        inputPanel.add(priceField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addButton = new JButton("Add Pet");
        viewButton = new JButton("View Pets");
        adoptButton = new JButton("Adopt Pet");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(adoptButton);

        output = new JTextArea(18, 60);
        output.setEditable(false);

        setLayout(new BorderLayout());

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        adoptButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            addPet();

        }

        else if (e.getSource() == viewButton) {

            viewPets();

        }

        else if (e.getSource() == adoptButton) {

            adoptPet();

        }
    }

    private void addPet() {

        try {

            String id = idField.getText();
            String name = nameField.getText();
            String breed = breedField.getText();
            String gender = genderField.getText();
            String type = typeField.getText();
            int age = Integer.parseInt(ageField.getText());
            double price = Double.parseDouble(priceField.getText());

            if (age <= 0) {
                throw new InvalidPetAgeException(
                        "Pet age must be greater than 0."
                );
            }

            Pet pet;

            if (type.equalsIgnoreCase("Dog")) {

                pet = new Dog(id,name,breed,age,gender,price,PetStatus.AVAILABLE);

            }

            else if (type.equalsIgnoreCase("Cat")) {

                pet = new Cat(id,name,breed,age,gender,price,PetStatus.AVAILABLE);

            }

            else if (type.equalsIgnoreCase("Bird")) {

                pet = new Bird(id,name,breed,age,gender,price,PetStatus.AVAILABLE);

            }

            else {

                JOptionPane.showMessageDialog(this,"Type must be Dog, Cat, or Bird.");

                return;
            }

            system.addPet(pet);

            system.saveAllPets();

            output.setText(
                    "Pet added successfully!\n\n" +

                    "Pet ID: " + pet.getPetID() + "\n" +
                    "Name: " + pet.getName() + "\n" +
                    "Breed: " + pet.getBreed() + "\n" +
                    "Age: " + pet.getAge() + "\n" +
                    "Gender: " + pet.getGender() + "\n" +
                    "Type: " + pet.getClass().getSimpleName() + "\n" +
                    "Status: " + pet.getStatus() + "\n" +
                    "Adoption Fee: " + pet.getFinalAdoptionFee()
            );

            clearFields();

        }

        catch (InvalidPetAgeException e) {

            JOptionPane.showMessageDialog(this,e.getMessage());

        }

        catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,"Age and price must be numbers.");
        }
    }

    private void viewPets() {

        if (system.getPetList().isEmpty()) {

            output.setText("No pets available.");

            return;
        }

        output.setText("ALL PETS\n");
        output.append("====================================\n");

        for (Pet pet : system.getPetList()) {

            output.append(pet.toString() + "\n------------------------------------\n");
        }
    }

    private void adoptPet() {

        String id = idField.getText();


        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(this,"Enter a Pet ID.");

            return;
        }


        try {

            Pet pet = system.searchPet(id);


            if (pet == null) {

                JOptionPane.showMessageDialog(this,"Pet not found.");

                return;
            }


            system.adoptPet(id);


            output.setText(
                    "Pet adopted successfully!\n\n" +

                    "Pet ID: " + pet.getPetID() + "\n" +
                    "Name: " + pet.getName() + "\n" +
                    "Status: " + pet.getStatus()
            );

        }

        catch (AlreadyAdoptedException e) {

            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        breedField.setText("");
        ageField.setText("");
        genderField.setText("");
        typeField.setText("");
        priceField.setText("");
    }
}