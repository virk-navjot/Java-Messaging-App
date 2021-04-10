package Messages;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.border.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

public class Server implements ActionListener{
	
	JPanel p1;
	JTextField text;
	JButton button;
	static JPanel ta;
	static Box vertical = Box.createVerticalBox();
	static JFrame f1 = new JFrame();
	
	static ServerSocket st;
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;
	
	Boolean type;
	
	
	Server(){
		//create panel for the header
		f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7, 94, 04));
		p1.setBounds(0, 0, 450, 70);
		f1.add(p1);
		
		//add back arrow icon
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("messages/icons/arrow1.png"));
		Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel arrowLabel= new JLabel(i3);
		arrowLabel.setBounds(5, 22, 25, 25);
		p1.add(arrowLabel);
		
		//add mouse event listener
		arrowLabel.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});

		
		//add profile picture
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("messages/icons/profile1.png"));
		Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel profile_label= new JLabel(i6);
		profile_label.setBounds(40, 5, 60, 60);
		p1.add(profile_label);

		
		//add label for user's name
		JLabel name_label = new JLabel("Navjot");
		name_label.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
		name_label.setForeground(Color.WHITE);
		name_label.setBounds(110, 15, 100, 25);
		p1.add(name_label);
		
		//add a label for user's status
		JLabel status_label = new JLabel("Active Now");
		status_label.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
		status_label.setForeground(Color.WHITE);
		status_label.setBounds(110, 35, 100, 18);
		p1.add(status_label);
		
		
		Timer t = new Timer(1, new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				if(!type) {
					
				status_label.setText("Active Now");
				
				}
			}
			
		});
		
		t.setInitialDelay(2000);
		
	    //add an image icon for video 
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("messages/icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel video_label= new JLabel(i9);
		video_label.setBounds(300, 17, 25, 25);
		p1.add(video_label);
	
		
		//add an image icon for phone
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("messages/icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel phone_label= new JLabel(i12);
		phone_label.setBounds(350, 17, 25, 25);
		p1.add(phone_label);
		
		//add menu icon
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("messages/icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(15, 30, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel menu_label= new JLabel(i15);
		menu_label.setBounds(400, 17, 15, 25);
		p1.add(menu_label);
		
		
		//text area to place message
		ta = new JPanel();
		//ta.setBounds(5, 75, 440, 475);
		ta.setFont(new Font("SANS_SERIF", Font.PLAIN, 16));
		//f1.add(ta);
		
		JScrollPane sp = new JScrollPane(ta);
		sp.setBounds(5, 75, 440, 475);
		sp.setBorder(BorderFactory.createEmptyBorder());
		f1.add(sp);
		
		ScrollBarUI ui = new BasicScrollBarUI() {
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setBackground(new Color(7, 94, 84));
				button.setForeground(Color.WHITE);
				this.thumbColor = new Color(7, 94, 84);
				return button;
			}
			
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setBackground(new Color(7, 94, 84));
				button.setForeground(Color.WHITE);
				this.thumbColor = new Color(7, 94, 84);
				return button;
			}
		};
		
		sp.getVerticalScrollBar().setUI(ui);
		f1.add(sp);
		
		//text field to enter text 
		text = new JTextField();
		text.setBounds(5, 555, 360, 40);
		text.setFont(new Font("SANS-SERIF", Font.PLAIN, 16));
		f1.add(text);
		
		text.addKeyListener(new KeyAdapter() {
		   
			public void keyPressed(KeyEvent ke) {
				status_label.setText("typing...");
				t.stop();
				type = true;
			}
			
			public void keyReleased(KeyEvent ke) {
				type = false;
				
				if (!t.isRunning()) {
					t.start();
				}
			}
		});
		
		
		//send button
		button = new JButton("Send");
		button.setBounds(370, 555, 70, 40);
		button.setBackground(new Color(7, 94, 04));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
		button.addActionListener(this);
		f1.add(button);
		
		
		f1.setUndecorated(true);
		f1.setLayout(null);
		f1.setSize(450, 600);
		f1.setLocation(200, 50);
		f1.setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent ae){
		
		try {
			
				String result = text.getText();
				
				sendTextToFile(result);
				JPanel p2 = formatLabel(result);
				
				ta.setLayout(new BorderLayout());
				
				JPanel right = new JPanel(new BorderLayout());
				right.add(p2, BorderLayout.LINE_END);
				vertical.add(right);
						
				vertical.add(Box.createVerticalStrut(15));
	            
	            ta.add(vertical, BorderLayout.PAGE_START);
				
				/* ta.add(p2); */
				
		        dos.writeUTF(result);
		        text.setText("");
		}catch(Exception e) {
			System.out.println();
		}
	}
	
	public void sendTextToFile(String message) throws FileNotFoundException {
		 
		try(FileWriter fw = new FileWriter("Message.txt");
			 PrintWriter pw = new PrintWriter(new BufferedWriter(fw));){
			 pw.println("Navjot: " + message);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	}
	
	public static JPanel formatLabel(String result) {
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
		
		JLabel l1 = new JLabel("<HTML><p style= \"width:150px\">"+ result +"</p></HTML>");
		l1.setFont(new Font("tahoma", Font.PLAIN, 16));
		l1.setBackground(new Color(37, 211, 102));
		l1.setOpaque(true);
		l1.setBorder(new EmptyBorder(15, 15, 15, 50));
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("hh:mm"); 
		
		JLabel l2 = new JLabel();
		l2.setText(date.format(cal.getTime()));
		
		p3.add(l1);
		p3.add(l2);
		return p3;
	}
	
	
	public static void main(String[] args) {
		
		new Server().f1.setVisible(true);
		
		String messageInput = "";
		
		try {
				
			st = new ServerSocket(6001);
			
			while(true) {
				s = st.accept();
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				
				
			while(true) {
				
				messageInput = dis.readUTF();
				JPanel p2 = formatLabel(messageInput);
				
				JPanel left = new JPanel(new BorderLayout());
				left.add(p2, BorderLayout.LINE_START);
				vertical.add(left);
				
			
				f1.validate();
				
			  }
		  }
			
		}catch(Exception e) {
			
		}
	}

}