package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnDN;
	private JButton btnThoat;
	private TaiKhoanDao tkDao = new TaiKhoanDao();
	private NhanVienDao nvDao = new NhanVienDao();
	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 400);
		setFocusCycleRoot(true);
		setFocusableWindowState(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(45, 48, 256, 256);
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/Login.png")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
		lblNewLabel_1.setBounds(440, 62, 224, 40);
		contentPane.add(lblNewLabel_1);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUserName.setBounds(480, 159, 273, 30);
		txtUserName.setText("admin");
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tên đăng nhập : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblNewLabel_2.setBounds(350, 159, 120, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mật khẩu :");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(350, 200, 120, 30);
		contentPane.add(lblNewLabel_2_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setBounds(480, 200, 273, 30);
		txtPassword.setText("admin");
		contentPane.add(txtPassword);
		
		btnDN = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDN.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		btnDN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmTong fTong;
				String taiKhoan = txtUserName.getText().trim();
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText().trim();
				String maNV = tkDao.checkNV(taiKhoan, password);
				
				if(maNV!=null) {
					NhanVien nv = null;
					try {
						nv = nvDao.getNVTheoMa(maNV);
					} catch (ClassNotFoundException | SQLException e2) {
						e2.printStackTrace();
					}
					try {
						if (nv == null) {
							System.out.println("loi roi dung thu nua");
							return;
						}
						
						fTong = new FrmTong(nv.getMaNV(),nv.getChuVu());
						fTong.pack();
						fTong.setBounds(0, 0,  1299, 674);
						fTong.setLocationRelativeTo(null);
						fTong.setVisible(true);
						fTong.chechChucVu(nv.getChuVu());
						fTong.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
						fTong.setExtendedState(JFrame.MAXIMIZED_BOTH); 
						fTong.setFocusCycleRoot(true);
						fTong.setFocusableWindowState(true);
						dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDN.setBackground(new Color(224, 255, 255));
		btnDN.setBounds(473, 278, 150, 48);
		contentPane.add(btnDN);
		
		btnThoat = new JButton("Tho\u00E1t");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		btnThoat.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/btnExitLogin.png")));
		btnThoat.setBounds(633, 278, 120, 48);
		contentPane.add(btnThoat);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(350, 125, 403, 5);
		contentPane.add(panel);
	}
}
