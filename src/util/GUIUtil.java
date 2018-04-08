package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

public class GUIUtil {
	private static String imageFolder = "C:/Users/a4643/Desktop/JAVA/project/HuTuBill/img";
	
	public static void setImageIcon(JButton b, String fileName, String tip) {
		ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
		b.setIcon(i);
		b.setPreferredSize(new Dimension(61, 81));
		b.setToolTipText(tip);
		b.setVerticalTextPosition(JButton.BOTTOM);
		b.setHorizontalTextPosition(JButton.CENTER);
		b.setText(tip);
	}
	
	public static void setColor(Color color, JComponent ...components) {
		for (JComponent jComponent : components) {
			jComponent.setForeground(color);
		}
	}
	
	/**
	 *	用作快速测试面板 
	 * @param p 测试的面板
	 * @param strechRate	拉伸的百分比
	 */
	public static void showPanel(JPanel p, double strechRate) {
//		GUIUtil.useLNF();
		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(strechRate);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		cp.show(p);
	}
	
	public static void showPanel(JPanel p) {
		showPanel(p, 0.85);
	}
	
	public static boolean cheakZero(JTextField tf, String input) {
		if(!cheakNumber(tf, input))
			return false;
		if (0 == Integer.parseInt(tf.getText().trim())) {
			JOptionPane.showMessageDialog(null, "不能为0");
			tf.grabFocus();
			return false;
		}
		return true;
	}
	
	public static boolean cheakNumber(JTextField tf, String input) {
		if(!cheakEmpty(tf, input))
			return false;
		String text = tf.getText().trim();
		try {
		Integer.parseInt(text);
		return true;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "请输入整数");
			tf.grabFocus();
			return false;
		}
	}
	
	
	public static boolean cheakEmpty(JTextField tf, String input) {
		String text = tf.getText().trim();
		if(0 == text.length()) {
			JOptionPane.showMessageDialog(null, input + "不能為空");
			tf.grabFocus();
			return false;
		}
		return true;
	}
	
	public static int getInt(JTextField tf) {
		return Integer.parseInt(tf.getText().trim());
	}
	
//	public static void useLNf() {
//			try {
//				javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFell");
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InstantiationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (UnsupportedLookAndFeelException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//	}
}
