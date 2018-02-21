import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane; //swing components that we'll use to organize chat bot

import java.awt.Color; //background color

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent; //monitor for when we press enter so they know when we enter stuff

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener{

	JPanel p=new JPanel(); //add the board so I can add stuff to the swing application
	JTextArea dialog=new JTextArea(20,50); //contain the conversation
	JTextArea input=new JTextArea(1,50); //input your text to conversation
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	); //select text area you want to scroll and then the vertical scrollbar is as needed and we never have a horizontal scroll


	
	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","ola","howdy","ni hao"}, //things I say
		{"hi","hello","hey"}, //what chatbot says
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well","bad","you already know"},
		//basketball
		{"kobe","lebron","jordan"},
		{"no","NO","NO!!!!!!!","goat","lit"},
		//default
		{"sorry","I love basketball","computer science is amazing","java is lit",
		"(anthony is currently not available)"}
	};
	
	public static void main(String[] args){
		new ChatBot();
	}
	
	public ChatBot(){
		super("Chat Bot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this); //trigger keylistener in this class
	
		p.add(scroll); //scroll contains dialog
		p.add(input);
		p.setBackground(new Color(66,116,244));
		add(p);
		
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){ //check if key pressed is entered
			input.setEditable(false); //don't want enter to cause a newline
			//-----grab quote-----------
			String quote=input.getText(); 
			input.setText("");
			addText("-->You:\t"+quote);
			quote = quote.trim(); //take out white space
			while( 
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			} // if there is a punctuation then it takes the punctuation away
			quote = quote.trim(); //trim any extra white space
			byte response=0;  
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2java )){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);//finds the index of the array of responses
					addText("\n-->Anthony\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Anthony\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true); //won't register a new line
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str); //add to dialog
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}