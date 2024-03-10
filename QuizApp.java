package jcommon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot;

public class QuizApp extends JFrame implements ActionListener{
	JLabel label;
	JRadioButton radioButtons[] = new JRadioButton[6];
	JButton btnNext, btnResult;
	ButtonGroup bg;
	int count = 0, current=0, x = 1, y = 1, now=0, wrong = 0;
	int m[] = new int[10];

	public QuizApp(String s) {
		super(s);
		label = new JLabel();
		add(label);
		bg = new ButtonGroup();
		for(int i=0; i<6; i++) {
			radioButtons[i] = new JRadioButton();
			add(radioButtons[i]);
			bg.add(radioButtons[i]);
		}
		btnNext = new JButton("Next");
		btnResult = new JButton("Result");
		btnResult.addActionListener(this);
		btnNext.addActionListener(this);
		btnResult.setVisible(false);
		add(btnNext);
		add(btnResult);
		setData();
		label.setBounds(170, 80, 450, 20);
		radioButtons[0].setBounds(170, 110, 450, 20);
		radioButtons[1].setBounds(170, 140, 200, 20);
		radioButtons[2].setBounds(170, 170, 200, 20);
		radioButtons[3].setBounds(170, 200, 200, 20);
		//radioButtons[4].setBounds(170, 200, 200, 20);
		btnNext.setBounds(170, 240, 100, 30);
		btnResult.setBounds(300, 240, 100, 30);
		
		setLayout(null);
		setVisible(true);
		setSize(600, 350);
	}
	void setData() {
		radioButtons[5].setSelected(true);
		if(current == 0) {
			label.setText("Q1. what is the only thing that computer understand? ");
			radioButtons[0].setText("Machine Code");
			radioButtons[1].setText("Low Level languages");
			radioButtons[2].setText("High Level languages");
			radioButtons[3].setText("Alpha code");
		}
		if(current == 1) {
			label.setText("Q2. Before a computer understand a program it must be");
			radioButtons[0].setText("Translated into its machine code");
			radioButtons[1].setText("Translated into a high level languages");
			radioButtons[2].setText("Translated into a low level languages");
			radioButtons[3].setText("Translated into binary code");
		}
		if(current == 2) {
			label.setText("Q3. A list of instructions that enable a computer to perform a specific task is a");

			radioButtons[0].setText("Computer Program");
			radioButtons[1].setText("Algorithm");
			radioButtons[2].setText("Machine code");
			radioButtons[3].setText("Binary Code");
		}
		if(current == 3) {
			label.setText("Q4. which programming paradigm emphasizes on writing code that is is to read");

			radioButtons[0].setText("Procedural programming");
			radioButtons[1].setText("Object-oriented programming");
			radioButtons[2].setText("Functional programming");
			radioButtons[3].setText("Structured programming");
		}
		if(current == 4) {
			label.setText("Q5. what is the term used for a block of code that is executed repeatedly until a certian condition is met");

			radioButtons[0].setText("Function");
			radioButtons[1].setText("Loop");
			radioButtons[2].setText("Condition");
			radioButtons[3].setText("Variable");
		}
		if(current == 5) {
			label.setText("Q6.Which data structure is used for storing a collection of elements in a non-linear ");

			radioButtons[0].setText("Array");
			radioButtons[1].setText("Stack");
			radioButtons[2].setText("Queue");
			radioButtons[3].setText("Tree");
		}
		if(current == 6) {
			label.setText("Q7. which of the following is a type of sorting algorithm");

			radioButtons[0].setText("Binary Search");
			radioButtons[1].setText("Quick Sort");
			radioButtons[2].setText("Depth-First-Search");
			radioButtons[3].setText("Breadth-First-Search");
		}
		if(current == 7) {
			label.setText("Q8. Which of the following is not a web browser");

			radioButtons[0].setText("Chrome");
			radioButtons[1].setText("Safari");
			radioButtons[2].setText("Firefox");
			radioButtons[3].setText("Photoshop");
		}
		if(current == 8) {
			label.setText("Q9. Which data structure is used for implementing a last-in-first-out LIFO behavior");

			radioButtons[0].setText("Queue");
			radioButtons[1].setText("Stack");
			radioButtons[2].setText("Tree");
			radioButtons[3].setText("Linked List");
		}
		if(current == 9) {
			label.setText("Q10.Number of primitive data type in Java are ");

			radioButtons[0].setText("6");
			radioButtons[1].setText("7");
			radioButtons[2].setText("8");
			radioButtons[3].setText("9");
		}
		label.setBounds(130, 80, 450, 20);
		label.setFont(new Font("serif", Font.PLAIN, 10));
		for(int i=0, j=0; i<=90; i+=30, j++) {
			if(j<4) {
				radioButtons[j].setBounds(130, 110+i, 200, 20);
			}
		}
	}
	
	boolean checkAnswer() {
		if(current == 0) {
			return (radioButtons[0].isSelected());
		}
		if(current == 1) {
			return (radioButtons[0].isSelected());
		}
		if(current == 2) {
			return (radioButtons[0].isSelected());
		}
		if(current == 3) {
			return (radioButtons[1].isSelected());
		}
		if(current == 4) {
			return (radioButtons[1].isSelected());
		}
		if(current == 5) {
			return (radioButtons[3].isSelected());
		}
		if(current == 6) {
			return (radioButtons[1].isSelected());
		}
		if(current == 7) {
			return (radioButtons[3].isSelected());
		}
		if(current == 8) {
			return (radioButtons[1].isSelected());
		}
		if(current == 9) {
			return (radioButtons[2].isSelected());
		}
		
		return false;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNext) {
			if(checkAnswer()) {
				count = count+1;
			}
			if(!checkAnswer()) {
				wrong = wrong+1;
			}
			current++;
			setData();
			if(current == 9) {
				btnNext.setEnabled(false);
				btnResult.setEnabled(true);
				btnResult.setVisible(true);
				btnResult.setText("Result");
			}
		}
		if(e.getActionCommand().equals("Result")) {
			if(checkAnswer()) {
				count = count+1;
			}
			current++;
			showPieChart(count, wrong);
		}
	}
	public static void showPieChart(int count, int wrong) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("correct", count);
		dataset.setValue("wrong", wrong);
		
		JFreeChart chart = ChartFactory.createPieChart(
				"Result",
				dataset,
				true,
				true,
				false
				);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSectionPaint("correct", java.awt.Color.green);
		plot.setSectionPaint("wrong", java.awt.Color.RED);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(400, 80, 350, 250);
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.add(chartPanel);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		QuizApp qa = new QuizApp("Simple Quiz App");

	}


}
