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

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					textArea.append("Empty input, try again.");
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
			gl_contentPane.createSequentialGroup()
				.addComponent(statsPanel, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(textField)
					.addComponent(scrollPane))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup()
				.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(statsPanel)
					.addComponent(textField))
				.addComponent(scrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
		);

		leftTop = new JTextArea();
		leftTop.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftTop.setAlignmentY(Component.TOP_ALIGNMENT);
		leftTop.setBackground(Color.GRAY);
		leftTop.setForeground(Color.WHITE);
		leftTop.setEditable(false);
		textArea = new JTextArea();
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		textArea.setEditable(true);
		scrollPane.setViewportView(textArea);
		statsPanel.setViewportView(leftTop);
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
