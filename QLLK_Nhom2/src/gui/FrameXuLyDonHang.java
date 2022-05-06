package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CTHoaDonDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LinhKienDao;
import dao.LoaiLinhKienDao;
import dao.NhanVienDao;
import entity.CTHoaDon;
import entity.DonHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhanVien;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class FrameXuLyDonHang extends JInternalFrame {
	private JPanel contentPane, pMain;
	private JTextField txtTenKH, txtMaKH, txtSDT, txtDiaChi, txtSoluong;
	private JLabel lblNewLabel_1, lblMKhchHng_1, lblNewLabel_1_3, lblNewLabel_1_4, lblNewLabel_1_5, lblNewLabel_1_6;
	private JTable table, tableTimKH_1;
	private DefaultTableModel tableModel, tableModel1;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private JLabel lblTongThanTien, lblThongBaoSoLuong, lblDh;
	private JComboBox<String> cbbLoaiLK, comboBox_1;
	private JScrollPane scpTimKH;
	private JButton btnThanhToan, btnLuh, btnThemLK, btnTaoHD, btnXoaTrang, btnCapNhatSP, btnXoaSP, btnLuu;
	private HoaDonDao hdDao = new HoaDonDao();
	private KhachHangDao khDao = new KhachHangDao();
	private NhanVienDao nvDao = new NhanVienDao();
	private LinhKienDao lkDao = new LinhKienDao();
	private LoaiLinhKienDao llkDao = new LoaiLinhKienDao();
	private CTHoaDonDao cthdDao = new CTHoaDonDao();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private LocalDate day = LocalDate.now();
	private static int Enter;
	private JTextField txtThanhTien;
	private JTextField txtGiaThanh;
	private JTextField txtMaLK;

	/**
	 * Create the frame.
	 */
	public FrameXuLyDonHang(String maNV) {
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

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1924, 610);
		pMain.add(panel);
		panel.setLayout(null);
		tableTimKH_1 = new JTable();

		String col1[] = { "Mã KH", "Tên KH", "SDT", "Địa chỉ" };
		tableModel1 = new DefaultTableModel(col1, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 610, 1924, 389);
		pMain.add(scrollPane);

		scpTimKH = new JScrollPane();
		scpTimKH.setBounds(256, 63, 475, 201);
		panel.add(scpTimKH);
		tableTimKH_1 = new JTable(tableModel1);
		tableTimKH_1.setRowHeight(25);
		tableTimKH_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableTimKH_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableTimKH_1.getSelectedRow();
				System.out.println(rowSelect);
				String maKH = (String) tableTimKH_1.getValueAt(rowSelect, 0);
				String tenKH = (String) tableTimKH_1.getValueAt(rowSelect, 1);
				String soDT = (String) tableTimKH_1.getValueAt(rowSelect, 2);
				String diaChi = (String) tableTimKH_1.getValueAt(rowSelect, 3);
				txtTenKH.setText(tenKH);
				txtMaKH.setText(maKH);
				txtSDT.setText(soDT);
				txtDiaChi.setText(diaChi);
				scpTimKH.setVisible(false);
				txtTenKH.setEditable(false);
				btnLuh.setEnabled(true);
			}

		});

		scpTimKH.setViewportView(tableTimKH_1);
		scpTimKH.setVisible(false);

		JLabel lblNewLabel = new JLabel("Tên khách hàng : ", SwingConstants.RIGHT);
		lblNewLabel.setBounds(47, 29, 199, 35);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		panel.add(lblNewLabel);

		JLabel lblMKhchHng = new JLabel("Mã khách hàng : ", SwingConstants.RIGHT);
		lblMKhchHng.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblMKhchHng.setBounds(38, 103, 208, 25);
		panel.add(lblMKhchHng);

		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại : ", SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(38, 173, 208, 25);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Địa Chỉ : ", SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(38, 239, 208, 25);
		panel.add(lblNewLabel_1_2);

		txtTenKH = new JTextField();
		txtTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scpTimKH.setVisible(true);
				Enter = 1;
			}
		});
		txtTenKH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Enter == 0) {
					String tenKH = txtTenKH.getText();
					try {
						showTimKiemKH(tenKH);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				} else
					Enter = 0;
			}

			private void showTimKiemKH(String tenKH) throws ClassNotFoundException, SQLException {
				KhachHangDao khDao = new KhachHangDao();
				int tblRow = tableModel1.getRowCount();
				for (int i = tblRow - 1; i >= 0; i--) {
					tableModel1.removeRow(i);
				}
				for (KhachHang kh : khDao.getMaKHTenKD(tenKH)) {
					tableModel1
							.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSoDienThoai(), kh.getDiaChi() });
				}

			}
		});
		txtTenKH.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtTenKH.setBounds(256, 29, 341, 36);
		panel.add(txtTenKH);
		txtTenKH.setColumns(10);

		txtMaKH = new JTextField();
		txtMaKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(256, 94, 341, 35);
		panel.add(txtMaKH);

		txtSDT = new JTextField();
		txtSDT.setHorizontalAlignment(SwingConstants.LEFT);
		txtSDT.setEditable(false);
		txtSDT.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtSDT.setColumns(10);
		txtSDT.setBounds(256, 163, 341, 35);
		panel.add(txtSDT);

		txtDiaChi = new JTextField();
		txtDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		txtDiaChi.setEditable(false);
		txtDiaChi.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(256, 229, 341, 35);
		panel.add(txtDiaChi);

		lblNewLabel_1 = new JLabel("Loại linh kiện : ", SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(694, 29, 208, 35);
		panel.add(lblNewLabel_1);

		lblMKhchHng_1 = new JLabel("Mã linh kiện : ", SwingConstants.RIGHT);
		lblMKhchHng_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblMKhchHng_1.setBounds(694, 108, 208, 25);
		panel.add(lblMKhchHng_1);

		lblNewLabel_1_3 = new JLabel("Giá thành : ", SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(694, 173, 208, 25);
		panel.add(lblNewLabel_1_3);

		lblNewLabel_1_4 = new JLabel("Thành tiền : ", SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(694, 239, 208, 25);
		panel.add(lblNewLabel_1_4);

		cbbLoaiLK = new JComboBox<String>();
		cbbLoaiLK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenLoai = (String) cbbLoaiLK.getSelectedItem();

				try {
					loadLKCungLoai(tenLoai);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				xoaTrangSP();
				list.setBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Danh sách " + tenLoai + " ", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)));
			}
		});
		cbbLoaiLK.setEnabled(false);
		cbbLoaiLK.setBounds(912, 29, 341, 35);
		panel.add(cbbLoaiLK);

		JLabel lblNewLabel_1_3_1 = new JLabel("Số lượng : ", SwingConstants.RIGHT);
		lblNewLabel_1_3_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(1087, 174, 97, 25);
		panel.add(lblNewLabel_1_3_1);

		txtSoluong = new JTextField();
		txtSoluong.setEnabled(false);
		txtSoluong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkSoLuong()) {
					String maLk = txtMaLK.getText();
					LinhKien linhKien = null;
					try {
						linhKien = lkDao.getLKTheoID(maLk);
						int soLuong = Integer.valueOf(txtSoluong.getText());
						if (soLuong > linhKien.getSoLuong()) {
							lblThongBaoSoLuong.setText("Không đủ số lượng rồi :((");
							showMessage("Error Hãy nhập số lượng !!", txtSoluong);
						} else {
							System.out.println("linh kien " + linhKien.getSoLuong());
							int giaThanh = currencyVN.parse(txtGiaThanh.getText()).intValue();
							txtThanhTien.setText(currencyVN.format(soLuong * giaThanh));
							lblThongBaoSoLuong.setText("Còn lại " + linhKien.getSoLuong() + " " + linhKien.getLoaiLK().getTenLoaiLK());
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		txtSoluong.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(1206, 168, 47, 35);
		panel.add(txtSoluong);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1374, 29, 474, 238);
		panel.add(scrollPane_1);

		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setBorder(
				new TitledBorder(null, "Danh sách sản phẩm ", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnXoaSP.setEnabled(true);
				btnLuu.setEnabled(true);
				LinhKien s = null;
				try {
					s = loadInfoLKFromName(list.getSelectedValue().toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (s != null) {
					txtMaLK.setText(s.getMaLK());
					txtGiaThanh.setText(currencyVN.format(s.getDonGia()));
					lblThongBaoSoLuong.setText("Còn lại : " + s.getSoLuong() + " " + s.getLoaiLK().getTenLoaiLK() + "");
				} else {
					lblThongBaoSoLuong.setText("");
				}
				
			}
		});
		scrollPane_1.setViewportView(list);

		comboBox_1 = new JComboBox<String>();

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDH = (String) comboBox_1.getSelectedItem();
				if (maDH != null) {
					DonHang dh = null;
					try {
						dh = hdDao.getDHTheoID(maDH);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (dh != null) {
						lblTongThanTien.setText(currencyVN.format(dh.getTongThanhTien()));
						lblDh.setText(dh.getMaDH());
					}
					try {
						loadCTDH(dh.getMaDH());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					KhachHang kh = null;
					try {
						kh = khDao.getKHTheoMa(dh.getKhachHang().getMaKH());
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					if (kh != null) {
						txtTenKH.setText(kh.getTenKH());
						txtMaKH.setText(kh.getMaKH());
						txtSDT.setText(kh.getSoDienThoai());
						txtDiaChi.setText(kh.getDiaChi());
					}
					btnThemLK.setEnabled(true);
				} else {
					xoaTrangKH();
				}
			}
		});

		comboBox_1.setBounds(98, 354, 148, 34);
		panel.add(comboBox_1);

		lblNewLabel_1_5 = new JLabel("Đơn Hàng", SwingConstants.CENTER);
		lblNewLabel_1_5.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 18));
		lblNewLabel_1_5.setBounds(98, 415, 148, 34);
		panel.add(lblNewLabel_1_5);

		lblNewLabel_1_6 = new JLabel("Tổng thành tiền là : ", SwingConstants.RIGHT);
		lblNewLabel_1_6.setForeground(Color.GREEN);
		lblNewLabel_1_6.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 20));
		lblNewLabel_1_6.setBounds(274, 354, 230, 34);
		panel.add(lblNewLabel_1_6);

		lblTongThanTien = new JLabel("", SwingConstants.CENTER);
		lblTongThanTien.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblTongThanTien.setBounds(514, 354, 217, 34);
		panel.add(lblTongThanTien);

		lblThongBaoSoLuong = new JLabel("", SwingConstants.CENTER);
		lblThongBaoSoLuong.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblThongBaoSoLuong.setBounds(797, 354, 489, 34);
		panel.add(lblThongBaoSoLuong);

		btnThanhToan = new JButton("Thanh Toán Đơn Hàng");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int tongTien = 0;
				try {
					tongTien = currencyVN.parse(lblTongThanTien.getText()).intValue();
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				if (tongTien > 0) {
					try {
						if(hdDao.capnhatTrangThaiDonHang(lblDh.getText())) {
							JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công");
						};
						troVeBanDau();
						loadDH();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					showMessage("Hãy thêm hàng vào đơn hàng này để thanh toán", txtDiaChi);
				}
			}
		});
		btnThanhToan.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnPrint.png")));
		btnThanhToan.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnThanhToan.setBounds(1618, 518, 230, 51);
		panel.add(btnThanhToan);

		btnLuh = new JButton("Lưu đơn hàng");
		btnLuh.setEnabled(false);
		btnLuh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDH = lblDh.getText();
				String maKh = txtMaKH.getText();
				KhachHang kh = null;
				try {
					kh = khDao.getKHTheoMa(maKh);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				NhanVien nv = null;
				try {
					nv = nvDao.getNVTheoMa(maNV);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				HoaDon hd = new HoaDon(maDH, Date.valueOf(day), kh, nv, 0, 0);
				try {
					hdDao.themDH(hd);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				try {
					loadDH();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				btnLuh.setEnabled(false);
				btnThemLK.setEnabled(true);
				comboBox_1.setSelectedIndex(0);
			}
		});
		btnLuh.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnSave.png")));
		btnLuh.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnLuh.setBounds(1087, 518, 199, 51);
		panel.add(btnLuh);

		btnThemLK = new JButton("Thêm linh kiện");
		btnThemLK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThemLK.setEnabled(false);
				btnLuh.setEnabled(false);
				list.setEnabled(true);
				enabledPSP(true);
				txtSoluong.setEnabled(true);
				try {
					cbbLoaiLK.setEnabled(true);
					loadLLK();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnThemLK.setEnabled(false);
		btnThemLK.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/chip.png")));
		btnThemLK.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnThemLK.setBounds(1087, 436, 199, 51);
		panel.add(btnThemLK);

		btnTaoHD = new JButton("Tạo đơn hàng");
		btnTaoHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(panel, "Bạn có chắc muốn tạo mới hóa đơn?",
						"Thông báo xác nhận tạo hóa đơn", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					troVeBanDau();
					enabledPKH(false);
					xoaTrangKH();
					btnThemLK.setEnabled(false);
					int tblRow = tableModel.getRowCount();
					for (int i = tblRow - 1; i >= 0; i--) {
						tableModel.removeRow(i);
					}
					try {
						int soDH = DemHD() + 1;
						lblDh.setText("DH" + soDH);
						enabledPKH(true);

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnTaoHD.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnThem.png")));
		btnTaoHD.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnTaoHD.setBounds(801, 518, 199, 51);
		panel.add(btnTaoHD);

		btnXoaTrang = new JButton("Xoá trắng");
		btnXoaTrang.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnXoaTrang.setBounds(1354, 518, 199, 51);
		panel.add(btnXoaTrang);

		btnCapNhatSP = new JButton("Cập nhập sản phẩm");
		btnCapNhatSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkSoLuong()) {
					String maLk = txtMaLK.getText();
					String maHD = lblDh.getText();
					LinhKien sp = null;
					try {
						sp = lkDao.getLKTheoID(maLk);

					} catch (Exception e4) {
						e4.printStackTrace();
					}
					CTHoaDon ctHoaDon = cthdDao.getCTHD(maHD, maLk);
					int soSLSPcanCN = 0;
					if (ctHoaDon.getSoLuong() > Integer.valueOf(txtSoluong.getText()).intValue()) {
						int cnSLSP = ctHoaDon.getSoLuong() - Integer.valueOf(txtSoluong.getText()).intValue();
						soSLSPcanCN = sp.getSoLuong() + cnSLSP;
					} else {
						int cnSLSP = Integer.valueOf(txtSoluong.getText()).intValue() - ctHoaDon.getSoLuong();
						soSLSPcanCN = sp.getSoLuong() - cnSLSP;
					}
					try {
						lkDao.capNhatSoLuongSP(soSLSPcanCN, maLk);
					} catch (ClassNotFoundException | SQLException e4) {
						e4.printStackTrace();
					}
					try {
						cthdDao.capNhat(Integer.valueOf(txtSoluong.getText()).intValue(), maHD, maLk);
						loadCTDH(maHD);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					int tongThanhTien = 0;
					try {
						tongThanhTien = tongThanhTien();
					} catch (ParseException e3) {
						e3.printStackTrace();
					}
					lblTongThanTien.setText(currencyVN.format(tongThanhTien));
					try {
						hdDao.capnhatTongTien(tongThanhTien, maHD);
					} catch (ClassNotFoundException | SQLException e2) {
						e2.printStackTrace();
					}
					list.setBorder(null);
					btnCapNhatSP.setEnabled(false);
					lblThongBaoSoLuong.setText("");
					showMessage("Đã cập nhật " + (String) cbbLoaiLK.getSelectedItem() + " thành công :))", txtDiaChi);
					enabledPSP(false);
					btnXoaSP.setEnabled(false);
					xoaTrangPanelSP();
					try {
						loadCTDH(maHD);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnCapNhatSP.setEnabled(false);
		btnCapNhatSP.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnCapNhat.png")));
		btnCapNhatSP.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnCapNhatSP.setBounds(1618, 354, 230, 51);
		panel.add(btnCapNhatSP);

		btnXoaSP = new JButton("Xoá Linh Kiện");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"Bạn có chắc chắn muốn xoá linh kiện này không ?") == JOptionPane.YES_OPTION) {
					String maHD = lblDh.getText();
					String maSP = txtMaLK.getText();
					CTHoaDon cthd = cthdDao.getCTHD(maHD, maSP);
					LinhKien lk = null;
					try {
						lk = lkDao.getLKTheoID(maSP);

					} catch (Exception e4) {
						e4.printStackTrace();
					}
					int cnSL = lk.getSoLuong() + cthd.getSoLuong();
					try {
						lkDao.capNhatSoLuongSP(cnSL, maSP);
					} catch (ClassNotFoundException | SQLException e2) {
						e2.printStackTrace();
					}
					try {
						cthdDao.delete(maHD, maSP);
						loadCTDH(maHD);
						troVeBanDau();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					int tongThanhTien = 0;
					try {
						tongThanhTien = tongThanhTien();
					} catch (ParseException e3) {
						e3.printStackTrace();
					}
					lblTongThanTien.setText(currencyVN.format(tongThanhTien));
					try {
						hdDao.capnhatTongTien(tongThanhTien, maHD);
					} catch (ClassNotFoundException | SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnXoaSP.setEnabled(false);
		btnXoaSP.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnXoa.png")));
		btnXoaSP.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnXoaSP.setBounds(1618, 436, 230, 51);
		panel.add(btnXoaSP);

		btnLuu = new JButton("Lưu linh kiện");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinhKien lk = null;
				int soLuong = Integer.valueOf(txtSoluong.getText()).intValue();
				HoaDon getDH = null;
				try {
					lk = lkDao.getLKTheoID(txtMaLK.getText());
					getDH = hdDao.getHDTheoID(lblDh.getText());
//					if (soLuong > lk.getSoLuong()) {
					CTHoaDon cthd = new CTHoaDon(lk, getDH, soLuong);
					try {
						if (!cthdDao.checkCTHD(cthd)) {
							try {
								cthdDao.themCTHD(cthd);
								lkDao.capNhatSoLuongSP(
										lk.getSoLuong() - Integer.valueOf(txtSoluong.getText()).intValue(),
										lk.getMaLK());
								loadCTDH(lblDh.getText());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						} else {
							try {
								loadCTDH(lblDh.getText());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					int tongThanhTien = 0;
					try {
						tongThanhTien = tongThanhTien();
						lblTongThanTien.setText(currencyVN.format(tongThanhTien));
						hdDao.capnhatTongTien(tongThanhTien, lblDh.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					list.setBorder(null);
					btnLuu.setEnabled(false);
					btnThemLK.setEnabled(true);

					lblThongBaoSoLuong.setText("");

					enabledPSP(false);
					showMessage("Đã thêm " + (String) cbbLoaiLK.getSelectedItem() + " thành công :))", txtDiaChi);
					xoaTrangPanelSP();
//					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnSave.png")));
		btnLuu.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));
		btnLuu.setBounds(1354, 436, 199, 51);
		panel.add(btnLuu);

		lblDh = new JLabel("DH01", SwingConstants.CENTER);
		lblDh.setForeground(Color.red);
		lblDh.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 32));
		lblDh.setBounds(98, 491, 148, 44);
		panel.add(lblDh);

		txtThanhTien = new JTextField();
		txtThanhTien.setHorizontalAlignment(SwingConstants.LEFT);
		txtThanhTien.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtThanhTien.setEditable(false);
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(912, 229, 341, 35);
		panel.add(txtThanhTien);

		txtGiaThanh = new JTextField();
		txtGiaThanh.setHorizontalAlignment(SwingConstants.LEFT);
		txtGiaThanh.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtGiaThanh.setEditable(false);
		txtGiaThanh.setColumns(10);
		txtGiaThanh.setBounds(912, 168, 161, 35);
		panel.add(txtGiaThanh);

		txtMaLK = new JTextField();
		txtMaLK.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaLK.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtMaLK.setEditable(false);
		txtMaLK.setColumns(10);
		txtMaLK.setBounds(912, 98, 341, 35);
		panel.add(txtMaLK);

		String col[] = { "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá thành", "Số lượng", "Thành tiền" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = table.getSelectedRow();
				String maLk = (String) table.getValueAt(rowSelect, 0);
				String tenLk = (String) table.getValueAt(rowSelect, 1);
				String loaiLK = (String) table.getValueAt(rowSelect, 2);
				String donGia = (String) table.getValueAt(rowSelect, 3);
				int soLuong = (Integer) table.getValueAt(rowSelect, 4);
				String thanhTien = (String) table.getValueAt(rowSelect, 5);
				try {
					loadLLK();
					LoaiLinhKien loaiLk = llkDao.getLLKTheoTen(loaiLK);
					for (int i = 0; i < cbbLoaiLK.getItemCount(); i++) {
						if (loaiLk.getTenLoaiLK().equalsIgnoreCase((String) cbbLoaiLK.getItemAt(i))) {
							cbbLoaiLK.setSelectedIndex(i);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < list.getModel().getSize(); i++) {
					if (tenLk.equalsIgnoreCase(list.getModel().getElementAt(i))) {
						list.setSelectedIndex(i);
					}
				}
				list.setEnabled(false);
				txtMaLK.setText(maLk);
				txtSoluong.setText(String.valueOf(soLuong));
				txtGiaThanh.setText(donGia);
				txtThanhTien.setText(thanhTien);
				cbbLoaiLK.setEditable(false);
				btnCapNhatSP.setEnabled(true);
				btnXoaSP.setEnabled(true);
				btnLuh.setEnabled(false);
				btnThemLK.setEnabled(true);
				cbbLoaiLK.setEnabled(true);
				txtSoluong.setEditable(true);
				txtSoluong.setEnabled(true);
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
	}

	protected LinhKien loadInfoLKFromName(String ten) throws ClassNotFoundException, SQLException {
		return lkDao.getMaSPTenSP(ten);
	}

	protected void loadLKCungLoai(String tenLoai) {
		listModel.removeAllElements();
		try {
			listModel.addAll(lkDao.getAllSpFromLLK(tenLoai));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void loadLLK() throws ClassNotFoundException, SQLException {
		cbbLoaiLK.removeAllItems();
		for (LoaiLinhKien llk : llkDao.getAll()) {

			cbbLoaiLK.addItem(llk.getTenLoaiLK());
		}

	}

	public void troVeBanDau() {
		xoaTrangPanelSP();
		enabledPKH(false);
		enabledPSP(false);
		setThongBaoSL("");
		btnThemLK.setEnabled(true);
		btnXoaSP.setEnabled(false);
		btnCapNhatSP.setEnabled(false);

	}

	public void inhoantat() {
		lblDh.setText("");
		lblTongThanTien.setText("");
		lblThongBaoSoLuong.setText("");
		int tblRow = tableModel.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	public void enabledPKH(boolean b) {
		txtTenKH.setEditable(b);
	}

	public void enabledPSP(boolean b) {
		cbbLoaiLK.setEditable(b);
		txtSoluong.setEditable(b);
	}

	public void xoaTrangPanelSP() {
		cbbLoaiLK.removeAllItems();
		xoaTrangSP();
	}

	public void xoaTrangSP() {
		txtSoluong.setText("");
		txtGiaThanh.setText("");
		txtThanhTien.setText("");
		txtMaLK.setText("");
	}

	public void xoaTrangKH() {
		txtTenKH.setText("");
		txtMaKH.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		lblThongBaoSoLuong.setText("");
	}

	public void setThongBaoSL(String s) {
		lblThongBaoSoLuong.setText(s);
	}

	public void showTimKiemKH(String tenKH) throws ClassNotFoundException, SQLException {
		KhachHangDao khDao = new KhachHangDao();
		int tblRow = tableModel1.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (KhachHang kh : khDao.getMaKHTenKD(tenKH)) {
			tableModel1.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSoDienThoai(), kh.getDiaChi() });
		}
	}

	public int DemHD() throws ClassNotFoundException, SQLException {
		return hdDao.demSoDH();
	}

	public boolean checkSoLuong() {
		String soLuong = txtSoluong.getText().trim();
		if (!(soLuong.length() > 0 && soLuong.matches("[1-9][0-9]*"))) {
			showMessage("Error Hãy nhập số lượng !!", txtSoluong);
			btnLuu.setEnabled(false);
			return false;
		}
		return true;
	}

	public int tongThanhTien() throws ParseException {
		int tblRow1 = tableModel.getRowCount();
		int tongThanhTien = 0;
		for (int i = tblRow1 - 1; i >= 0; i--) {
			int row = currencyVN.parse(tableModel.getValueAt(i, 5).toString()).intValue();
			tongThanhTien += row;
		}
		return tongThanhTien;
	}

	public void loadDH() throws ClassNotFoundException, SQLException {
		comboBox_1.removeAllItems();
		for (int i = hdDao.getallDH().size() - 1; i >= 0; i--) {
			comboBox_1.addItem(hdDao.getallDH().get(i).getMaDH());
		}
	}

	public void loadCTDH(String maDH) throws Exception {
		int tblRow = tableModel.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (CTHoaDon cthd : cthdDao.getallCTHD(maDH)) {
			tableModel.addRow(new Object[] { cthd.getLinhKien().getMaLK(), cthd.getLinhKien().getTenLK(),
					cthd.getLinhKien().getLoaiLK().getTenLoaiLK(), currencyVN.format(cthd.getLinhKien().getDonGia()),
					cthd.getSoLuong(), currencyVN.format(cthd.getSoLuong() * cthd.getLinhKien().getDonGia()) });
		}
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
