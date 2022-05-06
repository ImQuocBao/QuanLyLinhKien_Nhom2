package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.NhanVienDao;
import entity.NhanVien;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrameHeThongTrangChu extends JInternalFrame {

	private JLabel lblTenNhanVien,lblChucVu;
	private NhanVienDao nvDao = new NhanVienDao();
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public FrameHeThongTrangChu(String maNV) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1950, 1080);
		
		JPanel pTrangCHu = new JPanel();
		pTrangCHu.setBackground(Color.WHITE);
		pTrangCHu.setBounds(0, 0, 1186, 654);
		getContentPane().add(pTrangCHu);
		pTrangCHu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/bgShopp.png")));
		lblNewLabel.setBounds(474, 0, 1450, 1021);
		pTrangCHu.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/lblLogo.png")));
		lblNewLabel_1.setBounds(0, 522, 251, 188);
		pTrangCHu.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/lblVS.png")));
		lblNewLabel_2.setBounds(0, 640, 472, 381);
		pTrangCHu.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/girl.png")));
		lblNewLabel_4.setBounds(0, 0, 472, 332);
		pTrangCHu.add(lblNewLabel_4);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 332, 472, 136);
		pTrangCHu.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Tên nhân viên :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(0, 10, 191, 39);
		panel.add(lblNewLabel_5);
		
		lblTenNhanVien = new JLabel("");
		lblTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTenNhanVien.setBounds(216, 20, 252, 29);
		panel.add(lblTenNhanVien);
		
		JLabel lblNewLabel_6 = new JLabel("Chức vụ :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setBounds(0, 50, 191, 39);
		panel.add(lblNewLabel_6);
		
		lblChucVu = new JLabel("");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChucVu.setBounds(216, 55, 252, 29);
		panel.add(lblChucVu);
		
		loadNhanVien(maNV);
	}

	void loadNhanVien(String maMaNV) throws ClassNotFoundException, SQLException {
		NhanVien nv = nvDao.getNVTheoMa(maMaNV);
		lblChucVu.setText(nv.getChuVu());
		lblTenNhanVien.setText(nv.getTenNV());
	}
}
