package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mzwart on 28-11-2016.
 */
public class GameFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JTextArea leftTop;
	private JTextArea choiceArea;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public GameFrame(Game game) {
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		JScrollPane statsPanel = new JScrollPane();
		JScrollPane choicePanel = new JScrollPane();

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					textArea.append("Empty input, try again.\n");
				} else {
					game.action(textField.getText());
					textField.setText("");
					textField.requestFocus();
				}
			}
		});
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setAutoCreateGaps(true);
		gl_contentPane.setAutoCreateContainerGaps(true);

		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup()
					.addComponent(statsPanel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
					.addComponent(choicePanel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
					.addComponent(textField)
					.addComponent(scrollPane)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createSequentialGroup()
				.addComponent(statsPanel, GroupLayout.PREFERRED_SIZE, 120, 120)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(choicePanel, GroupLayout.DEFAULT_SIZE, 100, 100)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, 20)
		);

		leftTop = new JTextArea();
		leftTop.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftTop.setAlignmentY(Component.TOP_ALIGNMENT);
		leftTop.setBackground(Color.GRAY);
		leftTop.setForeground(Color.WHITE);
		leftTop.setEditable(false);
		choiceArea = new JTextArea();
		choiceArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		choiceArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		choiceArea.setBackground(Color.GRAY);
		choiceArea.setForeground(Color.WHITE);
		choiceArea.setEditable(false);
		textArea = new JTextArea();
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		textArea.setEditable(true);
		scrollPane.setViewportView(textArea);
		statsPanel.setViewportView(leftTop);
		choicePanel.setViewportView(choiceArea);
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		contentPane.setLayout(gl_contentPane);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void emptyTextArea() {
		JTextArea newArea = new JTextArea();
		this.textArea = newArea;
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setViewportView(textArea);
	}

	public JTextArea getLeftTop(){
		return leftTop;
	}

}
