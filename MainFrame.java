package attalSw;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MainFrame extends JFrame implements KeyListener {

	private JLabel lblTimer;
	private JButton btnStart;
	private JButton btnStop;
	private BorderLayout brdl;
	private JTextArea txtRes;
	Toolkit defaultToolkit;

	public MainFrame() {
		super("Attal Test");
		lblTimer = new JLabel("Time: 0",SwingConstants.CENTER);

		lblTimer.setFont(new Font("Verdana", Font.PLAIN, 50));
		btnStart = new JButton("Start");
		btnStop = new JButton("Stop");
		btnStart.setFocusable(false);
		btnStop.setFocusable(false);
		lblTimer.setFocusable(false);



		brdl = new BorderLayout();
		txtRes = new JTextArea();
		txtRes.setLineWrap(true);
		txtRes.setFocusable(false);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startEx();

			}
		});

		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stopEx();
			}
		});
		btnStart.setSize(100,100);

		setLayout(brdl);
		txtRes.setEditable(false);
		add(lblTimer,BorderLayout.PAGE_START);
		add(btnStart,BorderLayout.LINE_START);
		add(btnStop,BorderLayout.LINE_END);
		add(txtRes,BorderLayout.CENTER);


		btnStop.setEnabled(false);
		setSize(800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);


		defaultToolkit = Toolkit.getDefaultToolkit();

	}

	public static void main(String[] args) {
		MainFrame f = new MainFrame();

	}

	public void stopEx()
	{
		timer.cancel();
		String res = txtRes.getText();
		double a0 = 0.0;
		double a1 = 0.0;
		double a2 = 0.0;
		double a3 = 0.0;
		double a4 = 0.0;
		double a5 = 0.0;

		if(res.length() > 0)
		{
			for (int i = 0; i < res.length(); i++) {
				if (res.charAt(i) == '0') {
					a0++;
				}

				if (res.charAt(i) == '1') {
					a1++;
				}

				if (res.charAt(i) == '2') {
					a2++;
				}

				if (res.charAt(i) == '3') {
					a3++;
				}

				if (res.charAt(i) == '4') {
					a4++;
				}

				if (res.charAt(i) == '5') {
					a5++;
				}
			}
			double size = res.length();

			double score = ((a0*0.0)+(a1*1.0)+(a2*2.0)+(a3*3.0)+(a4*4.0)+(a5*5.0))/size;


			defaultToolkit.getSystemClipboard().setContents(new StringSelection(res+" Score "+ score), null);
		}
		btnStop.setEnabled(false);
		btnStart.setEnabled(true);
		Toolkit.getDefaultToolkit().beep();
		Toolkit.getDefaultToolkit().beep();
		Toolkit.getDefaultToolkit().beep();
		System.out.print("\007");
		System.out.flush();



	}
	long millis;
	int time;
	Timer timer;
	public void startEx()
	{

		btnStart.setEnabled(false);
		btnStop.setEnabled(true);


		time = 0;
		millis = System.currentTimeMillis();


		txtRes.setText("");




		TimerTask task = new TimerTask() {
			public void run() {


				String res = txtRes.getText();
				if(Character.isDigit(c) == true)
				{
					int a = Character.getNumericValue(c);
					//System.out.println(a);
					if(a >= 0 && a<=5)
						txtRes.setText(res+c);
				}
				c = 'a';
				lblTimer.setText("Time: " + String.valueOf(time) +"s Measures:" +  txtRes.getText().length());
				time++;

				if(time > 120) {
					timer.cancel();
					stopEx();
				}



			}
		};
		timer = new Timer();


		timer.schedule(task, 0,1000);
	}

	char c;
	@Override
	public void keyTyped(KeyEvent e) {



	}

	@Override
	public void keyPressed(KeyEvent e) {
		c = e.getKeyChar();
		//System.out.println(c);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
