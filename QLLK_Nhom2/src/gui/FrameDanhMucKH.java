package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDao;
import entity.KhachHang;


import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class FrameDanhMucKH extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dftableKH;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtSoDT;
	private JComboBox<String> cmbGioiTinh;
	private JTable tableKH;
	private ArrayList<KhachHang> listKH;
	private JButton btnThem, btnLuu, btnCapNhat, btnXoaTrang;

	KhachHangDao kh_dao;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameQLKH frame = new FrameQLKH();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @throws ClassNotFoundException
	 */
	public FrameDanhMucKH() throws ClassNotFoundException {

		kh_dao = new KhachHangDao();
		cmbGioiTinh = new JComboBox<String>();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		JPanel panelKH = new JPanel();
		setBounds(-5, -26, 1950, 1080);
		getContentPane().add(panelKH);
		panelKH.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Kh\u00E1ch h\u00E0ng", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("Khách hàng");
		panel.setBounds(10, 10, 1914, 587);
		panelKH.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng", TitledBorder.LEFT, TitledBorder.TOP, null,
				SystemColor.desktop));
		panel_2.setBounds(10, 23, 1894, 431);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã khách hàng");
		lblNewLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(54, 70, 167, 26);
		panel_2.add(lblNewLabel);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaKH.setBounds(238, 61, 443, 42);
		panel_2.add(txtMaKH);
		txtMaKH.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(775, 70, 160, 26);
		panel_2.add(lblNewLabel_1);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(944, 61, 477, 42);
		panel_2.add(txtTenKH);

		JLabel lblNewLabel_2 = new JLabel("Địa chỉ");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(54, 187, 167, 26);
		panel_2.add(lblNewLabel_2);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(238, 178, 443, 42);
		panel_2.add(txtDiaChi);

		JLabel lblNewLabel_3 = new JLabel("Số điện thoại");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(775, 187, 160, 26);
		panel_2.add(lblNewLabel_3);

		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(944, 177, 477, 45);
		panel_2.add(txtSoDT);

		JLabel lblNewLabel_4_1 = new JLabel("Giới tính");
		lblNewLabel_4_1.setForeground(Color.BLACK);
		lblNewLabel_4_1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(54, 310, 167, 26);
		panel_2.add(lblNewLabel_4_1);

		cmbGioiTinh = new JComboBox<String>();
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbGioiTinh.setModel(new DefaultComboBoxModel<String>(new String[] { "Nam", "Nữ" }));
		cmbGioiTinh.setBounds(238, 301, 443, 42);
		panel_2.add(cmbGioiTinh);

		btnThem = new JButton("Thêm");
		btnThem.setBackground(SystemColor.activeCaption);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ma = txtMaKH.getText().toUpperCase();
					String ten = txtTenKH.getText();

					String sdt = txtSoDT.getText();
					String diachi = txtDiaChi.getText();
					String gt = (String) cmbGioiTinh.getSelectedItem();
					KhachHang kh = new KhachHang(ma, ten, sdt, diachi, gt);

					if (valiDataKH()) {

						try {
							if (kh_dao.getKHTheoMa(ma) == null) {
								System.out.println(kh);
								String[] row = { kh.getMaKH(), kh.getTenKH(), kh.getSoDienThoai(), kh.getDiaChi(),
										kh.getGioiTinh() };
								dftableKH.addRow(row);
								kh_dao.insertKhachHang(kh);
								System.out.println(kh);
								JOptionPane.showMessageDialog(lblNewLabel, "Thêm thành công");
								emityiuputKH();
								selectedRow();

							} else {
								JOptionPane.showMessageDialog(lblNewLabel, "Trùng mã");
							}
						} catch (SQLException eThem) {
							// TODO Auto-generated catch block
						}
					}

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnThem.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(1287, 478, 200, 65);
		panel.add(btnThem);

		btnLuu = new JButton(" Lưu");
		btnLuu.setBackground(SystemColor.activeCaption);
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKH = txtMaKH.getText();
				KhachHang kh;
				try {
					kh = kh_dao.getKHTheoMa(maKH);
					KhachHang kh1 = new KhachHang(kh.getMaKH().trim(), txtTenKH.getText().trim(),
							txtSoDT.getText().trim(), txtDiaChi.getText().trim(), cmbGioiTinh.getSelectedItem() + "");
					kh_dao.updateKhachHang(kh1);
					JOptionPane.showMessageDialog(lblNewLabel, "Lưu thành công");
					System.out.println(kh1);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnLuu.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnSave.png")));
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLuu.setBounds(473, 478, 200, 65);
		panel.add(btnLuu);

		btnCapNhat = new JButton("Sửa");
		btnCapNhat.setBackground(SystemColor.activeCaption);
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vt = tableKH.getSelectedRow();

				if (valiDataKH()) {
					dftableKH.setValueAt(txtMaKH.getText(), vt, 0);
					dftableKH.setValueAt(txtTenKH.getText(), vt, 1);
					dftableKH.setValueAt(txtSoDT.getText(), vt, 2);
					dftableKH.setValueAt(txtDiaChi.getText(), vt, 3);
					dftableKH.setValueAt(cmbGioiTinh.getSelectedItem(), vt, 4);
					JOptionPane.showMessageDialog(lblNewLabel, "Nhấn nút lưu để lưu");
					btnLuu.setEnabled(true);
				}
			}
		});
		btnCapNhat.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnCapNhat.png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapNhat.setBounds(897, 478, 200, 65);
		panel.add(btnCapNhat);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBackground(SystemColor.activeCaption);
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputKH();
				btnCapNhat.setEnabled(false);
				btnLuu.setEnabled(false);
			}
		});

		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrang.setBounds(1617, 478, 200, 65);
		panel.add(btnXoaTrang);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.setBackground(SystemColor.activeCaption);
		btnXoa.setBounds(72, 478, 200, 65);
		panel.add(btnXoa);
		btnXoa.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnXoa.png")));
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int vt = tableKH.getSelectedRow();
					String maKH = txtMaKH.getText();
					JOptionPane.showConfirmDialog(lblNewLabel, "Bạn chắc chắn muốn xóa");
					dftableKH.removeRow(vt);
					kh_dao.deleteKhachHang(maKH);
					JOptionPane.showMessageDialog(lblNewLabel, "Xóa thành công");

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 608, 1914, 412);
		panelKH.add(scrollPane);

		tableKH = new JTable();
		tableKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		String[] table = { "Mã khách hàng", "Tên Khách hàng", "Số điện thoại", "Địa chỉ", "Giới tính" };
		dftableKH = new DefaultTableModel(table, 0);
		tableKH = new JTable(dftableKH);
		tableKH.setRowHeight(30);
		tableKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int vt = tableKH.getSelectedRow();
				txtMaKH.setText(tableKH.getValueAt(vt, 0).toString());
				txtTenKH.setText(tableKH.getValueAt(vt, 1).toString());
				txtSoDT.setText(tableKH.getValueAt(vt, 2).toString());
				txtDiaChi.setText(tableKH.getValueAt(vt, 3).toString());
				cmbGioiTinh.setSelectedItem(tableKH.getValueAt(vt, 4).toString());
				btnCapNhat.setEnabled(true);
			}
		});
		scrollPane.setViewportView(tableKH);
		showTableKH();
		selectedRow();
		cmbGioiTinh.setSelectedIndex(1);
		cmbGioiTinh.setEditable(true);
	}

	public void emityiuputKH() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtSoDT.setText("");

		cmbGioiTinh.setSelectedItem(null);
		txtMaKH.requestFocus();
	}

	private void selectedRow() {
		tableKH.setRowSelectionInterval(0, 0);
		txtMaKH.setText(tableKH.getValueAt(0, 0).toString());
		
		txtTenKH.setText(tableKH.getValueAt(0,1).toString());
		txtSoDT.setText(tableKH.getValueAt(0, 2).toString());
		txtDiaChi.setText(tableKH.getValueAt(0, 3).toString());
		cmbGioiTinh.setSelectedItem(tableKH.getValueAt(0, 4).toString());
	}

	public void showTableKH() throws ClassNotFoundException {

//    this.setLocationRelativeTo(null);
		listKH = new KhachHangDao().getAllKhachHang();

		dftableKH = (DefaultTableModel) tableKH.getModel();
		if (tableKH.getRowCount() > 0) {
			for (int i = tableKH.getRowCount() - 1; i > -1; i--) {
				dftableKH.removeRow(i);
			}
		}
		dftableKH.setColumnIdentifiers(
				new Object[] { "Mã khách hàng", "Tên Khách hàng", "Số điện thoại", "Địa chỉ", "Giới tính" });
		for (KhachHang kh : listKH) {
			dftableKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSoDienThoai(), kh.getDiaChi(),
					kh.getGioiTinh() });
		}
	}

	public boolean valiDataKH() {

		String maKh = txtMaKH.getText().trim();
		String ten = txtTenKH.getText().trim();
		String sdt = txtSoDT.getText().trim();
		if (!(maKh.length() > 0 && maKh.matches("^(KH|kh)[0-9]{2,}$"))) {
			showMessage("Error: Ma KH phai bat dau KH[SỐ] ví dụ KH03, ít nhất là 2 số", txtMaKH);
			txtMaKH.requestFocus();
			return false;
		}

		if (!(ten.length() > 0 && ten.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Ten Phai Theo Mau : Nguyen Van Anh", txtTenKH);
			txtTenKH.requestFocus();
			return false;
		}
		
		if (!(sdt.length() > 0 && sdt.matches("^[0-9]{10}$"))) {
			showMessage("Error: SDT  Khach Hang Phai Theo Mau:^[0-9]{10}$\n VD:0329324401", txtSoDT);
			txtSoDT.requestFocus();
			return false;
		}
		return true;
	}

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}