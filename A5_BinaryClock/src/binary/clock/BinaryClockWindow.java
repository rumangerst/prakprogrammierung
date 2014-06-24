package binary.clock;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class BinaryClockWindow extends JFrame implements ActionListener
{
	private BinaryViewerVertical timeSecondsRight;
	private BinaryViewerVertical timeSecondsLeft;
	private BinaryViewerVertical timeMinutesRight;
	private BinaryViewerVertical timeMinutesLeft;
	private BinaryViewerVertical timeHoursRight;
	private BinaryViewerVertical timeHoursLeft;
	
	private Timer clockTimer;
	

	public BinaryClockWindow()
	{
		initializeComponents();
		startClock();
		refreshClock();
	}

	private void initializeComponents()
	{
		setTitle("Binary Clock");
		setSize(640, 480);
		setLayout(new GridBagLayout());
		setBackground(Color.BLACK);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/**
		 * Initialize digits
		 */
		timeSecondsRight = new BinaryViewerVertical();		
		timeSecondsRight.setLength(4);
		timeSecondsLeft = new BinaryViewerVertical();
		timeSecondsLeft.setLength(3);

		timeMinutesRight = new BinaryViewerVertical();
		timeMinutesRight.setLength(4);
		timeMinutesLeft = new BinaryViewerVertical();
		timeMinutesLeft.setLength(3);

		timeHoursRight = new BinaryViewerVertical();
		timeHoursRight.setLength(4);
		timeHoursLeft = new BinaryViewerVertical();
		timeHoursLeft.setLength(2);

		/**
		 * Insert digits into panel
		 */
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 10;
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;

		// Hours
		c.gridx = 0;
		add(timeHoursLeft, c);

		c.gridx = 1;
		add(timeHoursRight, c);

		/**
		 * Separator
		 */
		c.gridx = 2;
		add(new JPanel(), c);

		// Minutes
		c.gridx = 3;
		add(timeMinutesLeft, c);

		c.gridx = 4;
		add(timeMinutesRight, c);
		
		/**
		 * Separator
		 */
		c.gridx = 5;
		add(new JPanel(), c);

		// Seconds
		c.gridx = 6;
		add(timeSecondsLeft, c);

		c.gridx = 7;
		add(timeSecondsRight, c);

	}
	
	private void startClock()
	{
		clockTimer = new Timer(1000, this);
		clockTimer.setActionCommand("UPDATE_CLOCK");
		clockTimer.start();
	}
	
	private void refreshClock()
	{
		Calendar current = Calendar.getInstance();
		
		int hour = current.get(Calendar.HOUR_OF_DAY);
		int minute = current.get(Calendar.MINUTE);
		int second = current.get(Calendar.SECOND);
		
		/**
		 * Set digits
		 */
		int hour_right = hour % 10;
		int hour_left = (hour - hour_right) / 10;
		
		int minute_right = minute % 10;
		int minute_left = (minute - minute_right) /10;
		
		int second_right = second % 10;
		int second_left = (second - second_right ) / 10;
		
		/**
		 * Write digits
		 */
		timeHoursRight.setNumber(hour_right);
		timeHoursLeft.setNumber(hour_left);
		timeMinutesRight.setNumber(minute_right);
		timeMinutesLeft.setNumber(minute_left);
		timeSecondsRight.setNumber(second_right);
		timeSecondsLeft.setNumber(second_left);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getActionCommand().equals("UPDATE_CLOCK"))
		{
			refreshClock();
		}
	}
}
