package Messages;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Server extends JFrame implements ActionListener{
	
	JPanel p1;
	JTextField text;
	JButton button;
	JTextArea ta;
	
	Server(){
		//create panel for the header
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7, 94, 04));
		p1.setBounds(0, 0, 450, 70);
		add(p1);
		
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
		
		
		//textarea to place message
		ta = new JTextArea();
		ta.setBounds(5, 75, 440, 475);
		ta.setFont(new Font("SANS_SERIF", Font.PLAIN, 16));
		ta.setEditable(false);
		add(ta);
		
		
		//text field to enter text 
		text = new JTextField();
		text.setBounds(5, 555, 360, 40);
		text.setFont(new Font("SANS-SERIF", Font.PLAIN, 16));
		add(text);
		
		
		//send button
		button = new JButton("Send");
		button.setBounds(370, 555, 70, 40);
		button.setBackground(new Color(7, 94, 04));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
		button.addActionListener(this);
		add(button);
		
		
		setUndecorated(true);
		setLayout(null);
		setSize(450, 600);
		setLocation(300, 100);
		setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent ae){
		String result = text.getText();
		ta.setText(ta.getText()+"\n" + result);
        text.setText("");		
	}
	
	
	public static void main(String[] args) {
		
		new Server().setVisible(true);
	}
}
