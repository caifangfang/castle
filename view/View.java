package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JPanel{
	private static final long serialVersionUID = 371809822400072039L;
	protected JTextArea textArea;
	private final static String newline = "\n";
	private String displayContext = "";
	
	public View()
	{
		super(new BorderLayout());
		textArea = new JTextArea(20,30);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);

		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void displayInfo(String newContext){
		displayContext = newContext + newline;		

		textArea.append(displayContext);
		textArea.setCaretPosition(textArea.getDocument().getLength());	
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		textArea.append(displayContext);
//		textArea.setCaretPosition(textArea.getDocument().getLength());		
//	}
	
}
