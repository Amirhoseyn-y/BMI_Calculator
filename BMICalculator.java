import javax.swing.*;

public class BMICalculator {

    public static void main(String[] args) {
        JFrame firstFrame = new JFrame("BMI Calculator");
        firstFrame.setSize(400, 200);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setLocationRelativeTo(null);

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));

        JLabel firstLabel = new JLabel("Welcome to BMI Calculator");
        firstLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Calculating");
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        firstPanel.add(Box.createVerticalGlue());
        firstPanel.add(firstLabel);
        firstPanel.add(Box.createVerticalStrut(20));
        firstPanel.add(startButton);
        firstPanel.add(Box.createVerticalGlue());

        firstFrame.add(firstPanel);
        firstFrame.setVisible(true);

        startButton.addActionListener(e -> {
            firstFrame.dispose();

            JFrame bmiFrame = new JFrame("BMI Calculator");
            bmiFrame.setSize(400, 300);
            bmiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bmiFrame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JPanel genderPanel = new JPanel();
            genderPanel.add(new JLabel("Gender:"));
            String[] genders = {"Enter your gender", "Male", "Female"};
            JComboBox<String> genderBox = new JComboBox<>(genders);
            genderPanel.add(genderBox);

            JPanel weightPanel = new JPanel();
            weightPanel.add(new JLabel("Weight (kg):"));
            JTextField weightField = new JTextField(10);
            weightPanel.add(weightField);

            JPanel heightPanel = new JPanel();
            heightPanel.add(new JLabel("Height (cm):"));
            JTextField heightField = new JTextField(10);
            heightPanel.add(heightField);

            JButton calculateButton = new JButton("Calculate");
            calculateButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

            JLabel resultLabel = new JLabel("");
            resultLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            mainPanel.add(genderPanel);
            mainPanel.add(Box.createVerticalStrut(10));
            mainPanel.add(weightPanel);
            mainPanel.add(Box.createVerticalStrut(10));
            mainPanel.add(heightPanel);
            mainPanel.add(Box.createVerticalStrut(20));
            mainPanel.add(calculateButton);
            mainPanel.add(Box.createVerticalStrut(20));
            mainPanel.add(resultLabel);

            bmiFrame.add(mainPanel);
            bmiFrame.setVisible(true);

            calculateButton.addActionListener(ae -> {
                String gender = (String) genderBox.getSelectedItem();
                if (gender.equals("Enter your gender")) {
                    JOptionPane.showMessageDialog(bmiFrame, "Please select your gender!");
                    return;
                }

                String weightText = weightField.getText();
                String heightText = heightField.getText();

                if (weightText.isEmpty() || heightText.isEmpty()) {
                    JOptionPane.showMessageDialog(bmiFrame, "Please fill all fields!");
                    return;
                }

                double weight=0;
                double heightCm=0;
                boolean validWeight=false;
                boolean validHeight=false;

                if (weightText.matches("[0-9]+\\.?[0-9]*")) {
                    weight = Double.parseDouble(weightText);
                    if (weight > 0 && weight < 500) {
                        validWeight=true;
                    }
                }

                if (heightText.matches("[0-9]+\\.?[0-9]*")) {
                    heightCm = Double.parseDouble(heightText);
                    if (heightCm > 0 && heightCm < 300) {
                        validHeight=true;
                    }
                }

                if (!validWeight || !validHeight) {
                    JOptionPane.showMessageDialog(bmiFrame, "Please enter valid numbers!");
                    return;
                }

                double heightM = heightCm / 100.0;
                double bmi = weight / (heightM * heightM);
                String status = "";

                switch (gender) {
                    case "Male":
                        if (bmi < 20) {
                            status = "Underweight";
                        }
                        else if (bmi >= 20 && bmi < 25) {
                            status = "Normal";
                        }
                        else if (bmi >= 25 && bmi < 30) {
                            status = "Overweight";
                        }
                        else {
                            status = "Obese";
                        }
                        break;

                    case "Female":
                        if (bmi < 19) {
                            status = "Underweight";
                        }
                        else if (bmi >= 19 && bmi < 24) {
                            status = "Normal";
                        }
                        else if (bmi >= 24 && bmi < 29) {
                            status = "Overweight";
                        }
                        else {
                            status = "Obese";
                        }
                        break;
                }

                resultLabel.setText(String.format("BMI: %.2f - %s", bmi, status));
            });
        });
    }
}