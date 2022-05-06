package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.LinhKienDao;
import dao.LoaiLinhKienDao;
import dao.NhaCungCapDao;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FrameTimKiemLK extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtTenLK;
	private JComboBox<String> cboLLK, cboNCC, cboGiaThanh;

	@SuppressWarnings("unused")
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private LinhKienDao linhKienDao = new LinhKienDao();
	private String tenLoaiLKTim = "";
	private String tenNCC = "";
	private LoaiLinhKienDao loaiLinhKienDao = new LoaiLinhKienDao();
	private NhaCungCapDao nhaCungCapDao = new NhaCungCapDao();
	private String maLLK = "";
	private String maNCC = "";
	private List<LinhKien> dsLK = linhKienDao.getallSP();
	private List<LinhKien> dsLKTim = new ArrayList<>();
	private ArrayList<LinhKien> dsLKTheoTen;
	private ArrayList<LinhKien> dsLKTheoLoai;
	private ArrayList<LinhKien> dsLKTheoNCC;
	private ArrayList<LinhKien> dsLKTheoLoaiNCC;
	private List<NhaCungCap> dsNCC;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTimKiemSP frame = new FrameTimKiemSP();
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
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrameTimKiemLK() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-5, -26, 1950, 1080);
		setPreferredSize(new Dimension(1920, 1080));
		setMaximumSize(new Dimension(1920, 1080));
		setMinimumSize(new Dimension(1920, 1080));
		setMaximizable(true);
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(false);
		JPanel pTK_DH = new JPanel();
		pTK_DH.setBackground(Color.WHITE);
		pTK_DH.setBounds(0, 0, 1186, 654);
		getContentPane().add(pTK_DH);
		pTK_DH.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 113, 1904, 312);
		pTK_DH.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên Linh Kiện");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(451, 34, 150, 20);
		panel_1.add(lblNewLabel);

		txtTenLK = new JTextField();
		txtTenLK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenLK.setBounds(776, 28, 500, 30);
		panel_1.add(txtTenLK);
		txtTenLK.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Loại Linh Kiện :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(451, 169, 150, 20);
		panel_1.add(lblNewLabel_1);

		loadCboLoaiLK();
		cboLLK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboLLK.setBounds(777, 163, 500, 34);
		cboLLK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboLLK.getSelectedIndex() != 0) {
					tenLoaiLKTim = cboLLK.getSelectedItem().toString();
					try {
						maLLK = loaiLinhKienDao.getMaTheoTen(tenLoaiLKTim);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					tenLoaiLKTim = "";
				}
			}
		});
		panel_1.add(cboLLK);

		cboGiaThanh = new JComboBox();
		cboGiaThanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cboGiaThanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboGiaThanh.setModel(new DefaultComboBoxModel(
				new String[] { "Tất cả", "Dưới 1000.000 đ", "Từ 1000.000 đ đến 2000.000 đ", "Trên 2000.000 đ" }));
		cboGiaThanh.setBounds(776, 90, 500, 34);
		panel_1.add(cboGiaThanh);

		JLabel lblNewLabel_1_1 = new JLabel("Giá thành :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setBounds(447, 97, 150, 20);
		panel_1.add(lblNewLabel_1_1);

		JButton btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(FrameTimKiemLK.class.getResource("/image/btnTim.png")));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaHetDuLieu();
				if (validateLSP()) {
					String tenLK = txtTenLK.getText().trim();
					int selDonGia = cboGiaThanh.getSelectedIndex();
					try {
						dsLK = linhKienDao.getallSP();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ArrayList<LinhKien> listLKTheoGia = new ArrayList<LinhKien>();
					ArrayList<LinhKien> listLKTheoTen = new ArrayList<LinhKien>();
					if(selDonGia == 1) {
						listLKTheoGia = loadLKDonGia1();
					} else if(selDonGia == 2) {
						listLKTheoGia = loadLKDonGia2();
					} else if(selDonGia == 3) {
						listLKTheoGia = loadLKDonGia3();
					}
					
						if(tenLK.trim().length() != 0) {
							listLKTheoTen = linhKienDao.getLKTheoTen(tenLK);
							if(tenLoaiLKTim != "" && tenNCC == "" && selDonGia == 0) {
								loadLKTheoTenLoai(tenLK, maLLK);
							} else if(tenLoaiLKTim != "" && tenNCC != ""  && selDonGia == 0) {
								loadLKTheoTenLoaiNCC(tenLK, maLLK, maNCC);
							} else if(tenLoaiLKTim == "" && tenNCC == "" && selDonGia == 0) {
								loadLKTheoTen();
							} else if(tenLoaiLKTim == "" && tenNCC == "" && selDonGia != 0) {
								xoaHetDuLieu();
								if(selDonGia == 1) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() < 1000000) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 2) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 1000000 && lk.getDonGia() < 2000000) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 3) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 2000000) {
											dsLKTim.add(lk);
										}
									}
								}
								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							} else if(tenLoaiLKTim != "" && tenNCC == ""  && selDonGia != 0) {
								xoaHetDuLieu();
								if(selDonGia == 1) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() < 1000000 && lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 2) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 1000000 && lk.getDonGia() < 2000000
												&& lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 3) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 2000000
												&& lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
											dsLKTim.add(lk);
										}
									}
								}
								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							} else if(tenLoaiLKTim == "" && tenNCC != "" && selDonGia != 0) {
								xoaHetDuLieu();
								if(selDonGia == 1) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() < 1000000 && lk.getNhaCungCap().getMaNCC().equals(maNCC)) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 2) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 1000000 && lk.getDonGia() < 2000000
												&& lk.getNhaCungCap().getMaNCC().equals(maNCC)) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 3) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 2000000
												&& lk.getNhaCungCap().getMaNCC().equals(maNCC)) {
											dsLKTim.add(lk);
										}
									}
								}

								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							} else if(tenLoaiLKTim != "" && tenNCC != "" && selDonGia != 0) {
								xoaHetDuLieu();
								if(selDonGia == 1) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() < 1000000 
												&& lk.getNhaCungCap().getMaNCC().equals(maNCC)
												&& lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 2) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 1000000 && lk.getDonGia() < 2000000
												&& lk.getNhaCungCap().getMaNCC().equals(maNCC)
												&& lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
											dsLKTim.add(lk);
										}
									}
								} else if(selDonGia == 3) {
									for(LinhKien lk : listLKTheoTen) {
										if(lk.getDonGia() >= 2000000
												&& lk.getNhaCungCap().getMaNCC().equals(maNCC)
												&& lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
											dsLKTim.add(lk);
										}
									}
								}
								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							}
						} else {
							if(tenLoaiLKTim != "" && tenNCC == "" && selDonGia == 0) {
								loadLKTheoLoai(maLLK);
							} else if(tenLoaiLKTim != "" && tenNCC != ""  && selDonGia == 0) {
								loadLKTheoLoaiNCC(maNCC, maLLK);
							} else if(tenLoaiLKTim == "" && tenNCC == "" && selDonGia != 0) {
								dsLKTim = listLKTheoGia;
								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							} else if(tenLoaiLKTim != "" && tenNCC == ""  && selDonGia != 0) {
								for(LinhKien lk : listLKTheoGia) {
									if(lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
										dsLKTim.add(lk);
									}
								}
								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							} else if(tenLoaiLKTim == "" && tenNCC != "" && selDonGia != 0) {
								for(LinhKien lk : listLKTheoGia) {
									if(lk.getNhaCungCap().getMaNCC().equals(maNCC)) {
										dsLKTim.add(lk);
									}
								}
								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							} else if(tenLoaiLKTim != "" && tenNCC != "" && selDonGia != 0) {
								for(LinhKien lk : listLKTheoGia) {
									if(lk.getNhaCungCap().getMaNCC().equals(maNCC) && lk.getLoaiLK().getMaLoaiLK().equals(maLLK)) {
										dsLKTim.add(lk);
									}
								}
								loadLKTim(dsLKTim);
								dsLKTim.removeAll(dsLKTim);
							}
						}
					if(txtTenLK.getText().trim().length() == 0 
							&& selDonGia == 0 && tenLoaiLKTim == "" && tenNCC == "") {
						try {
							dsLKTim = linhKienDao.getallSP();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						loadLKTim(dsLKTim);
						dsLKTim.removeAll(dsLKTim);
					}
					if(table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Không có linh kiện cần tìm!");
					}
				}
			}
		});
		btnTim.setBounds(1356, 234, 150, 40);
		panel_1.add(btnTim);

		JLabel lblNewLabel_1_3 = new JLabel("Nhà Cung Cấp :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_3.setBounds(444, 243, 150, 20);
		panel_1.add(lblNewLabel_1_3);

		loadCboNCC();
		cboNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboNCC.setBounds(776, 238, 500, 34);
		panel_1.add(cboNCC);
		cboNCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboNCC.getSelectedIndex() != 0) {
					tenNCC = cboNCC.getSelectedItem().toString();
					maNCC = nhaCungCapDao.getMaNCCTheoTen(tenNCC);
				} else {
					tenNCC = "";
				}
			}
		});
		String col[] = { "Mã Linh Kiện", "Tên Linh Kiện", "Đơn Giá", "Số lượng", "Loại Linh Kiện","Trạng Thái", "Nhà Cung Cấp"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBounds(0, 0, 1, 1);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		table.getColumnModel().getColumn(5).setPreferredWidth(45);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(9, 433, 1905, 591);
		pTK_DH.add(scrollPane);
		JLabel lblNewLabel_2 = new JLabel("Tìm Kiếm Linh Kiện");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(324, 17, 1253, 66);
		pTK_DH.add(lblNewLabel_2);

	}

	public void xoaHetDuLieu() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
	

	
	public ArrayList<LinhKien> loadLKDonGia1() {
		ArrayList<LinhKien> dsLKDonGia1 = new ArrayList<LinhKien>();
		for(LinhKien lk : dsLK) {
			if(lk.getDonGia() < 1000000) {
				dsLKDonGia1.add(lk);
			}
		}
		return dsLKDonGia1;
	}
	
	public ArrayList<LinhKien> loadLKDonGia2() {
		ArrayList<LinhKien> dsLKDonGia2 = new ArrayList<LinhKien>();
		for(LinhKien lk : dsLK) {
			if(lk.getDonGia() >= 1000000 && lk.getDonGia() < 2000000) {
				dsLKDonGia2.add(lk);
			}
		}
		return dsLKDonGia2;
	}
	
	public ArrayList<LinhKien> loadLKDonGia3() {
		ArrayList<LinhKien> dsLKDonGia3 = new ArrayList<LinhKien>();
		for(LinhKien lk : dsLK) {
			if(lk.getDonGia() >= 2000000) {
				dsLKDonGia3.add(lk);
			}
		}
		return dsLKDonGia3;
	}
	
	public void loadLKTheoTenLoai(String tenLK, String maLLK) {
		ArrayList<LinhKien> dsLKTheoTenLoai = linhKienDao.getLKTenLoai(tenLK, maLLK);
		xoaHetDuLieu();
		for(LinhKien lk : dsLKTheoTenLoai) {
			tableModel.addRow(new Object[] {
					lk.getMaLK(),
					lk.getTenLK(),
					currencyVN.format(lk.getDonGia()),
					lk.getSoLuong(),
					linhKienDao.getTenLoaiLK(lk.getLoaiLK().getMaLoaiLK()),
					lk.getTrangThai() == 1 ? "Còn" : "Hết",
					linhKienDao.getTenNCC(lk.getNhaCungCap().getMaNCC())
			});
		}
	}
	
	public void loadLKTheoTenLoaiNCC(String tenLK, String maLLK, String maNCC) {
		ArrayList<LinhKien> dsLKTheoTenLoaiNCC = linhKienDao.getLKTenLoaiNCC(tenLK, maLLK, maNCC);
		xoaHetDuLieu();
		for(LinhKien lk : dsLKTheoTenLoaiNCC) {
			tableModel.addRow(new Object[] {
					lk.getMaLK(),
					lk.getTenLK(),
					currencyVN.format(lk.getDonGia()),
					lk.getSoLuong(),
					linhKienDao.getTenLoaiLK(lk.getLoaiLK().getMaLoaiLK()),
					lk.getTrangThai() == 1 ? "Còn" : "Hết",
					linhKienDao.getTenNCC(lk.getNhaCungCap().getMaNCC())
			});
		}
	}

	public void loadLKTim(List<LinhKien> dsLKTim2) {
		xoaHetDuLieu();
		for(LinhKien lk : dsLKTim2) {
			tableModel.addRow(new Object[] {
					lk.getMaLK(),
					lk.getTenLK(),
					currencyVN.format(lk.getDonGia()),
					lk.getSoLuong(),
					linhKienDao.getTenLoaiLK(lk.getLoaiLK().getMaLoaiLK()),
					lk.getTrangThai() == 1 ? "Còn" : "Hết",
					linhKienDao.getTenNCC(lk.getNhaCungCap().getMaNCC())
			});
		}
	}

	public void loadLKTheoTen() {
		String tenLK = txtTenLK.getText();
		dsLKTheoTen = linhKienDao.getLKTheoTen(tenLK);
		for(LinhKien lk : dsLKTheoTen) {
			tableModel.addRow(new Object[] {
					lk.getMaLK(),
					lk.getTenLK(),
					currencyVN.format(lk.getDonGia()),
					lk.getSoLuong(),
					linhKienDao.getTenLoaiLK(lk.getLoaiLK().getMaLoaiLK()),
					lk.getTrangThai() == 1 ? "Còn" : "Hết",
					linhKienDao.getTenNCC(lk.getNhaCungCap().getMaNCC())
			});
		}
		if(table.getRowCount() == 0) {
			showMessage("Không có linh kiện theo tên linh kiện vừa nhập", txtTenLK);
		}
	}
	
	
	
	public void loadLKTheoLoai(String maLLK) {
		xoaHetDuLieu();
		dsLKTheoLoai = linhKienDao.getLKTheoLoai(maLLK);
		for(LinhKien lk : dsLKTheoLoai) {
			tableModel.addRow(new Object[] {
					lk.getMaLK(),
					lk.getTenLK(),
					currencyVN.format(lk.getDonGia()),
					lk.getSoLuong(),
					linhKienDao.getTenLoaiLK(lk.getLoaiLK().getMaLoaiLK()),
					lk.getTrangThai() == 1 ? "Còn" : "Hết",
					linhKienDao.getTenNCC(lk.getNhaCungCap().getMaNCC())
			});
		}
	}
	
	public void loadLKTheoNCC(String maNCC) {
		xoaHetDuLieu();
		dsLKTheoNCC = linhKienDao.getLKTheoNCC(maNCC);
		for(LinhKien lk : dsLKTheoNCC) {
			tableModel.addRow(new Object[] {
					lk.getMaLK(),
					lk.getTenLK(),
					currencyVN.format(lk.getDonGia()),
					lk.getSoLuong(),
					linhKienDao.getTenLoaiLK(lk.getLoaiLK().getMaLoaiLK()),
					lk.getTrangThai() == 1 ? "Còn" : "Hết",
					linhKienDao.getTenNCC(lk.getNhaCungCap().getMaNCC())
			});
		}
	}
	
	public void loadLKTheoLoaiNCC(String maNCC, String maLLK) {
		xoaHetDuLieu();
		dsLKTheoLoaiNCC = linhKienDao.getLKNCCLoai(maNCC, maLLK);
		for(LinhKien lk : dsLKTheoLoaiNCC) {
			tableModel.addRow(new Object[] {
					lk.getMaLK(),
					lk.getTenLK(),
					currencyVN.format(lk.getDonGia()),
					lk.getSoLuong(),
					linhKienDao.getTenLoaiLK(lk.getLoaiLK().getMaLoaiLK()),
					lk.getTrangThai() == 1 ? "Còn" : "Hết",
					linhKienDao.getTenNCC(lk.getNhaCungCap().getMaNCC())
			});
		}
	}

	public void loadCboLoaiLK() {
		loaiLinhKienDao = new LoaiLinhKienDao();
		Vector<String> listTenLoaiLK = new Vector<>();
		listTenLoaiLK.add("Tất cả");
		List<LoaiLinhKien> dsLoaiLK;
		try {
			dsLoaiLK = loaiLinhKienDao.getAll();
			for(LoaiLinhKien loaiLK : dsLoaiLK) {
				listTenLoaiLK.add(loaiLK.getTenLoaiLK());
			}
			cboLLK = new JComboBox<>(listTenLoaiLK);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void loadCboNCC() {
		nhaCungCapDao = new NhaCungCapDao();
		Vector<String> listTenNCC = new Vector<>();
		listTenNCC.add("Tất cả");
		 try {
			dsNCC = nhaCungCapDao.getallNCC();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(NhaCungCap ncc : dsNCC) {
			listTenNCC.add(ncc.getTenNCC());
		}
		cboNCC = new JComboBox<String>(listTenNCC);
		
	}

	public boolean validateLSP() {
		String tenLSP = txtTenLK.getText();
		if (!(tenLSP.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Tên loại sản phẩm không được có kí tự đặt biệt VD:Sách", txtTenLK);
			return false;
		}
		return true;
	}

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(null, message);
	}

}