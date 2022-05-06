package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.LinhKienDao;
import dao.LoaiLinhKienDao;
import dao.NhaCungCapDao;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class FrameDanhMucLK extends JInternalFrame {

	private JPanel contentPane;
	private JPanel pMain;
	private JPanel panelContent;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JTextField txtMaLK;
	private JTextField txtTenLK;
	private JTextField txtDonGia;
	private JTextField txtMaLoaiLK;
	private JTextField txtSoLuong;
	private JTextField txtMaNCC;
	private DefaultTableModel tbtDSSPModel;
	private JTable tblLK;
	private JLabel lblNewLabel_4; 
	private JButton btnLuu, btnCapNhap, btnXoa, btnThm, btnXoaTrang;
	private JComboBox<String> cboLoaiLK, cboNCC;
	private NhaCungCapDao nccDao = new NhaCungCapDao();
	private LoaiLinhKienDao lLkDao = new LoaiLinhKienDao();
	private LinhKienDao lkDao = new LinhKienDao();

	/**
	 * Create the frame.
	 */
	public FrameDanhMucLK() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1950, 1080);

		pMain = new JPanel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1186, 654);
		getContentPane().add(pMain);
		pMain.setLayout(null);

		JLabel lblNewLabel = new JLabel("Danh Mục Linh Kiện");
		lblNewLabel.setBounds(0, -11, 1924, 135);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		pMain.add(lblNewLabel);

		panelContent = new JPanel();
		panelContent.setBackground(Color.WHITE);
		panelContent.setBounds(0, 123, 1924, 898);
		pMain.add(panelContent);
		panelContent.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã Linh Kiện : ", SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(-45, 35, 318, 43);
		panelContent.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Tên Linh Kiện : ", SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(-45, 123, 318, 43);
		panelContent.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Đơn Giá : ", SwingConstants.RIGHT);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(-45, 216, 318, 43);
		panelContent.add(lblNewLabel_3);

		lblNewLabel_6 = new JLabel("Mã Loại Linh Kiện : ", SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(872, 35, 213, 43);
		panelContent.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("Số Lượng : ", SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(-45, 298, 318, 43);
		panelContent.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("Mã Nhà Cung Cấp : ", SwingConstants.RIGHT);
		lblNewLabel_8.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(872, 216, 213, 43);
		panelContent.add(lblNewLabel_8);

		txtMaLK = new JTextField();
		txtMaLK.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtMaLK.setEditable(false);
		txtMaLK.setBounds(298, 36, 459, 42);
		panelContent.add(txtMaLK);
		txtMaLK.setColumns(10);

		txtTenLK = new JTextField();
		txtTenLK.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtTenLK.setColumns(10);
		txtTenLK.setBounds(298, 123, 459, 42);
		panelContent.add(txtTenLK);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(298, 216, 459, 42);
		panelContent.add(txtDonGia);

		txtMaLoaiLK = new JTextField();
		txtMaLoaiLK.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtMaLoaiLK.setEditable(false);
		txtMaLoaiLK.setColumns(10);
		txtMaLoaiLK.setBounds(1129, 36, 459, 42);
		panelContent.add(txtMaLoaiLK);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(298, 301, 459, 42);
		panelContent.add(txtSoLuong);

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtMaNCC.setEditable(false);
		txtMaNCC.setColumns(10);
		txtMaNCC.setBounds(1129, 217, 459, 42);
		panelContent.add(txtMaNCC);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 399, 1924, 476);
		panelContent.add(scrollPane);

		String[] col1 = { "Mã Linh Kiện", "Tên Linh Kiện", "Đơn giá", "Số lượng", "Loại Linh Kiện", "Nhà Cung Cấp"};
		tbtDSSPModel = new DefaultTableModel(col1, 0);
		tblLK = new JTable(tbtDSSPModel);
		tblLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowSelect = tblLK.getSelectedRow();
				String maS =  (String) tblLK.getValueAt(rowSelect, 0);
				String tenS = (String) tblLK.getValueAt(rowSelect, 1);
				int donGia = (int) tblLK.getValueAt(rowSelect, 2);
				int soLuong = (Integer) tblLK.getValueAt(rowSelect, 3);
				String tenLlk = (String) tblLK.getValueAt(rowSelect, 4);
				String tenNcc = (String) tblLK.getValueAt(rowSelect, 5);
				
				txtMaLK.setText(maS);
				txtTenLK.setText(tenS);
				txtDonGia.setText(String.valueOf(donGia));
				txtSoLuong.setText(String.valueOf(soLuong));
				
				for(int i=0;i<cboLoaiLK.getItemCount();i++) {
					if(tenLlk.equalsIgnoreCase((String) cboLoaiLK.getItemAt(i))) {
						cboLoaiLK.setSelectedIndex(i);
					}
				}
				for(int i=0;i<cboNCC.getItemCount();i++) {
					if(tenNcc.equalsIgnoreCase((String) cboNCC.getItemAt(i))) {
						cboNCC.setSelectedIndex(i);
					}
				}
				btnCapNhap.setEnabled(true);
				btnLuu.setEnabled(false);
				btnXoa.setEnabled(true);
				btnThm.setEnabled(true);
			}
		});
		tblLK.setRowHeight(25);
		tblLK.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		scrollPane.setViewportView(tblLK);
		
		lblNewLabel_4 = new JLabel("Loại Linh Kiện", SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(872, 123, 213, 43);
		panelContent.add(lblNewLabel_4);
		
		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String maLk = txtMaLK.getText();
					String tenLk = txtTenLK.getText().trim();
					int soLuong = Integer.valueOf(txtSoLuong.getText()).intValue();
					int donGia = Integer.valueOf(txtDonGia.getText()).intValue();
					LoaiLinhKien loaiLk = null;
					NhaCungCap nhaCungCap = null;
					
					try {
						loaiLk = lLkDao.getLLKTheoID(txtMaLoaiLK.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						nhaCungCap = nccDao.getNCCTheoID(txtMaNCC.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					LinhKien lk = new LinhKien(maLk, tenLk, donGia, soLuong, 1 ,loaiLk, nhaCungCap);
					try {
						if(lkDao.themLk(lk)) {
							showMessage("Thêm Thành Công", txtTenLK);
							
							tbtDSSPModel.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), lk.getDonGia(), lk.getSoLuong(),
									lk.getLoaiLK().getTenLoaiLK(), lk.getNhaCungCap().getTenNCC()});
							xoaTrang();
						} else {
							showMessage("Thêm Thất Bại", txtTenLK);
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnSave.png")));
		btnLuu.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnLuu.setBounds(1680, 35, 138, 43);
		panelContent.add(btnLuu);
		
		btnCapNhap = new JButton("Cập Nhập");
		btnCapNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(validateLSP()) {
					String maLk = txtMaLK.getText();
					String tenLk = txtTenLK.getText().trim();
					int soLuong = Integer.valueOf(txtSoLuong.getText()).intValue();
					int donGia = Integer.valueOf(txtDonGia.getText()).intValue();
					LoaiLinhKien loaiLk = null;
					NhaCungCap nhaCungCap = null;
					
					try {
						loaiLk = lLkDao.getLLKTheoID(txtMaLoaiLK.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						nhaCungCap = nccDao.getNCCTheoID(txtMaNCC.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					LinhKien lk = new LinhKien(maLk, tenLk, donGia, soLuong, 1 ,loaiLk, nhaCungCap);
					try {
						
						if(lkDao.capnhat(lk)) {
							loadSP();
							xoaTrang();
							JOptionPane.showMessageDialog(null, "Cập Nhập Thành Công");
						}
						else {
							JOptionPane.showMessageDialog(null, "Cập Nhập Thất Bại");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnCapNhap.setEnabled(false);
		btnCapNhap.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnCapNhat.png")));
		btnCapNhap.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnCapNhap.setBounds(1680, 100, 138, 43);
		panelContent.add(btnCapNhap);
		
		btnXoa = new JButton("Xoá");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDungCu= txtMaLK.getText();
				int n= JOptionPane.showConfirmDialog(
						panelContent, 
                        "Bạn có chắc muốn XÓA SÁCH này?", 
                        "Thông báo xác nhận XÓA SÁCH này", 
                        JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {		
					try {
						lkDao.delete(maDungCu);
						loadSP();
						xoaTrang();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnXoa.setEnabled(false);
		btnXoa.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnXoa.png")));
		btnXoa.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnXoa.setBounds(1680, 168, 138, 43);
		panelContent.add(btnXoa);
		
		btnThm = new JButton("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
				try {
					txtMaLK.setText("LK"+(lkDao.getallSP().size()+1));
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				btnLuu.setEnabled(true);
			}
		});
		btnThm.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnThem.png")));
		btnThm.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnThm.setBounds(1680, 230, 138, 43);
		panelContent.add(btnThm);
		
		btnXoaTrang = new JButton("Xoá Trắng");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnXoaTrang.setBounds(1680, 298, 138, 43);
		panelContent.add(btnXoaTrang);
		
		cboLoaiLK = new JComboBox<String>();
		cboLoaiLK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenLoaiLK = (String) cboLoaiLK.getSelectedItem();
				try {
					txtMaLoaiLK.setText(lLkDao.getMaTheoTen(tenLoaiLK));
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		cboLoaiLK.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		cboLoaiLK.setBounds(1129, 123, 459, 43);
		panelContent.add(cboLoaiLK);
		
		JLabel lblNewLabel_8_1 = new JLabel("Nhà Cung Cấp", SwingConstants.RIGHT);
		lblNewLabel_8_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_8_1.setBounds(872, 298, 213, 43);
		panelContent.add(lblNewLabel_8_1);
		
		cboNCC = new JComboBox<String>();
		cboNCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenNcc = (String) cboNCC.getSelectedItem();
				txtMaNCC.setText(nccDao.getMaNCCTheoTen(tenNcc));
			}
		});
		cboNCC.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		cboNCC.setBounds(1129, 298, 459, 43);
		panelContent.add(cboNCC);
	}
	
	public void loadSP() throws Exception {
		int tblRow = tblLK.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tbtDSSPModel.removeRow(i);
		}
		for (LinhKien lk : lkDao.getallSP()) {
			tbtDSSPModel.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), lk.getDonGia(), lk.getSoLuong(),
					lk.getLoaiLK().getTenLoaiLK(), lk.getNhaCungCap().getTenNCC() });

		}
	}
	
	public void loadLlkToCombo() throws ClassNotFoundException, SQLException {
		cboLoaiLK.removeAll();
		for (LoaiLinhKien llk : lLkDao.getAll()) {
			cboLoaiLK.addItem(llk.getTenLoaiLK());
		}
	}
	
	public void loadNccToCombo() throws ClassNotFoundException, SQLException {
		cboNCC.removeAll();
		for (NhaCungCap ncc : nccDao.getallNCC()) {
			cboNCC.addItem(ncc.getTenNCC());
		}
	}
	
	public void xoaTrang() {
		txtMaLK.setText("");
		txtTenLK.setText("");
		txtSoLuong.setText("");
		txtDonGia.setText("");
		cboLoaiLK.setSelectedIndex(0);
		cboNCC.setSelectedIndex(0);
	}
	
	public boolean validateLSP() {
		String tenLSP = txtTenLK.getText().trim();
		if (!(tenLSP.length() > 0 && tenLSP.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			JOptionPane.showMessageDialog(this,"Error: Tên loại Linh Kiện không được trống hoặc có kí tự đặt biệt VD: RAM 16GB ");
			return false;
		}
		int donGia = Integer.valueOf(txtDonGia.getText());
		if(donGia <=0) {
			return false;
		}
		int soLuong = Integer.valueOf(txtDonGia.getText());
		if(soLuong <=0) {
			return false;
		}
		return true;
	}
	
	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
	
}
