package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import castle.Castle;
import castle.Room;
import view.View;

public class Game {
    private HashMap<String, Handler> handlers = new HashMap<String, Handler>();
    private Castle theCastle;
    private View theView;
    private String cmdContext;
    private boolean isStart = false;
        
    public Game() 
    {
    	handlers.put("go", new Handler(){
    		@Override
    		public void doCmd(String word) {
    			goRoom(word);
    		}
    	});
    	  	
    	theView = new View();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("CastleGame");
		frame.add(theView,BorderLayout.SOUTH);

		JPanel panel1 = new JPanel();
		JButton btnStart = new JButton("开始游戏");
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				start();				
			}
		});
		JButton btnStop = new JButton("结束游戏");
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bye();				
			}
		});
		JButton btnHelp = new JButton("帮助");	
		btnHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));        
		panel1.add(btnStart);
		panel1.add(btnStop);
		panel1.add(btnHelp);
		frame.add(panel1,BorderLayout.NORTH);	
		
		JPanel panel2 = new JPanel();
		JTextField cmdInput = new JTextField(20);
		JButton btnStep = new JButton("执行");
		btnStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdContext = cmdInput.getText();
				step();
			}
		});
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		panel2.add(cmdInput);
		panel2.add(btnStep);		
		frame.add(panel2,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); 
    }
    
    private void start(){
    	if(isStart){
    		theView.displayInfo("");
    		theView.displayInfo("游戏已经开始，无需再次点击'开始游戏'按钮。");
    	}
    	else
    	{
        	theCastle = new Castle();  
    		isStart = true;
    		printWelcome();
    	}
    }

    private void printWelcome() {
    	theView.displayInfo("");
        theView.displayInfo("欢迎来到城堡！");
        theView.displayInfo("这是一个超级无聊的游戏。");
        theView.displayInfo("如果需要帮助，请点击'帮助'按钮 。");
        showPrompt();
    }
    
    public void showPrompt(){
    	theView.displayInfo("");
    	theView.displayInfo("现在你在" + theCastle.getCurrentRoom());
        theView.displayInfo("出口有：");
        theView.displayInfo(theCastle.getCurrentRoom().getExitDesc());
    }
    
    public void showHelp(){
    	if(!isStart)
    	{
    		theView.displayInfo("");
    		theView.displayInfo("你可以先点击'开始游戏'按钮。");
            theView.displayInfo("然后在输入框中输入go命令并点击执行，");
            theView.displayInfo("如：\tgo east");
            theView.displayInfo("如果你不想玩了，可以点击'结束游戏'按钮。");
    	}
    	else
    	{
    		theView.displayInfo("");
    		theView.displayInfo("迷路了吗？你可以在输入框中输入go命令并点击执行，");
    		theView.displayInfo("如：\tgo east");
            theView.displayInfo("如果你不想玩了，可以点击'结束游戏'按钮。");
    	}	
    }
    
    public void bye(){
    	if(isStart)
    	{
    		isStart = false;
    		theView.displayInfo("");
    		theView.displayInfo("感谢您的光临，再见！");
    	}
    	else
    	{
    		theView.displayInfo("");
    		theView.displayInfo("你还没有开始游戏，你可以点击'开始游戏'按钮进入游戏。");
    	}
    }
    
    public void step(){
    	if(isStart)
	    {
	    	String line = cmdContext;
	    	String[] words = line.split(" ");
	    	Handler handler = handlers.get(words[0]);
			String value = "";
			if(words.length>1)
				value = words[1];
			if(handler != null){
				handler.doCmd(value);
			}
    	}
    	else
    	{
    		theView.displayInfo("");
    		theView.displayInfo("你还没有开始游戏，你可以点击'开始游戏'按钮进入游戏。");
    	}
    }
    
//    public void play(){
//		Scanner in = new Scanner(System.in);
//    	while ( true ) {
//    		String line = in.nextLine();
//    		String[] words = line.split(" ");
//    		Handler handler = handlers.get(words[0]);
//    		String value = "";
//    		if(words.length>1)
//    			value = words[1];
//    		if(handler != null){
//    			handler.doCmd(value);
//    			if(handler.isBye())
//    				break;
//    		}
//    	}
//    	in.close();
//    }
    
    // 以下为用户命令

    public void goRoom(String direction) 
    {
        Room nextRoom = theCastle.goRoom(direction);
        if (nextRoom == null) {
        	theView.displayInfo("");
            theView.displayInfo("那里没有门！");
        }
        else {
            showPrompt();
        }
    }
	
	public static void main(String[] args) {
		Game game = new Game();       
	}

}
