package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDao;
import entity.NhanVien;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings({ "serial", "unused" })
public class FrameTimKiemNV extends JInternalFrame {
	private JTextField txtTimTenNV;
	private JTextField txtTimSoDT;

	@SuppressWarnings("unused")
	private JTable tableNV;

	@SuppressWarnings("unused")
	private LocalDate endDay = LocalDate.now();
	private JTable tableCA;
	private DefaultTableModel dftableCA;
	private NhanVienDao nhanVienDao;
	@SuppressWarnings("rawtypes")
	private JComboBox cboMaNV;
	@SuppressWarnings("rawtypes")
	private JComboBox cboChucVu;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTimKiemNV frame = new FrameTimKiemNV();
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
	public FrameTimKiemNV() throws ClassNotFoundException {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		setSize(1920, 1080);
		JPanel pTKNV = new JPanel();
		pTKNV.setBackground(Color.WHITE);
		pTKNV.setBounds(0, 0, 1186, 632);
		pTKNV.setPreferredSize(new Dimension(1920, 1080));
		getContentPane().add(pTKNV, BorderLayout.CENTER);
		pTKNV.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm thông tin nhân viên");
		lblNewLabel.setBounds(266, 29, 1253, 61);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		pTKNV.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setBounds(534, 153, 150, 37);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pTKNV.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên nhân viên");
		lblNewLabel_1_1.setBounds(524, 238, 150, 33);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pTKNV.add(lblNewLabel_1_1);

		txtTimTenNV = new JTextField();
		txtTimTenNV.setBounds(819, 232, 573, 37);
		txtTimTenNV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTimTenNV.getText().trim().length() == 0 && cboChucVu.getSelectedIndex() == 0) {
					cboMaNV.setEnabled(true);
					cboChucVu.setEnabled(true);
				}
			}
		});
		txtTimTenNV.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				disableCbo();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				disableCbo();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				disableCbo();
			}
		});
		txtTimTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimTenNV.setColumns(10);
		pTKNV.add(txtTimTenNV);

		txtTimSoDT = new JTextField();
		txtTimSoDT.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				disableCbo();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				disableCbo();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				disableCbo();
			}
		});
		txtTimSoDT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTimSoDT.getText().trim().length() == 0 && cboChucVu.getSelectedIndex() == 0) {
					cboMaNV.setEnabled(true);
					cboChucVu.setEnabled(true);
				}
			}
		});
		txtTimSoDT.setBounds(822, 311, 576, 38);
		txtTimSoDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimSoDT.setColumns(10);
		pTKNV.add(txtTimSoDT);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1_1.setBounds(531, 310, 150, 37);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pTKNV.add(lblNewLabel_1_1_1);


		JButton btnTim = new JButton("Tìm");
		btnTim.setBounds(968, 466, 150, 40);
		btnTim.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				xoaHetDuLieu();
				ArrayList<NhanVien> dsNV = nhanVienDao.getAllNV();
				ArrayList<NhanVien> dsNVTheoCV = new ArrayList<NhanVien>();
				ArrayList<NhanVien> dsNVTim = new ArrayList<>();
				String chucVu = "";
				String sdtTim = "";
				if(txtTimSoDT.getText().trim().length() != 0) {
					checkSytax();
				}
				
				if(cboChucVu.getSelectedIndex() != 0) {
					chucVu = cboChucVu.getSelectedItem().toString();
					for(NhanVien nv : dsNV) {
						if(nv.getChuVu().equals(chucVu)) {
							dsNVTheoCV.add(nv);
						}
					}
				}
				if(cboMaNV.getSelectedIndex() != 0) {
					loadNVTheoMa();
				} else if(cboChucVu.getSelectedIndex() == 0) {
					if(txtTimTenNV.getText().trim().length() != 0 && txtTimSoDT.getText().trim().length() == 0) {
						dsNVTim = loadNVTheoTen();
					} else if(txtTimTenNV.getText().trim().length() == 0 && txtTimSoDT.getText().trim().length() != 0) {
						dsNVTim = loadNVTheoSDT();
					} else if(txtTimTenNV.getText().trim().length() != 0 && txtTimSoDT.getText().trim().length() != 0) {
						dsNVTim = loadNVTheoTenSDT();
					} else if(txtTimTenNV.getText().trim().length() == 0 && txtTimSoDT.getText().trim().length() == 0) {
						for(NhanVien nv : dsNV) {
							dftableCA.addRow(new Object[] {
									nv.getMaNV(),
									nv.getTenNV(),
									nv.getcMND(),
									nv.getsDT(),
									nv.getDiaChi(),
									nv.getChuVu()
								});
						}
					}
				} else if(cboChucVu.getSelectedIndex() != 0) {
					if(txtTimTenNV.getText().trim().length() != 0 && txtTimSoDT.getText().trim().length() == 0) {
						ArrayList<NhanVien> dsNVTheoTen = loadNVTheoTen();
						for(NhanVien nv : dsNVTheoTen) {
							if(nv.getChuVu().equals(chucVu)) {
								dsNVTim.add(nv);
							}
						}
					} else if(txtTimTenNV.getText().trim().length() == 0 && txtTimSoDT.getText().trim().length() != 0) {
						ArrayList<NhanVien> dsNVTheoSDT = loadNVTheoSDT();
						for(NhanVien nv : dsNVTheoSDT) {
							if(nv.getChuVu().equals(chucVu)) {
								dsNVTim.add(nv);
							}
						}
					} else if(txtTimTenNV.getText().trim().length() != 0 && txtTimSoDT.getText().trim().length() != 0) {
						ArrayList<NhanVien> dsNVTheoTenSDT = loadNVTheoTenSDT();
						for(NhanVien nv : dsNVTheoTenSDT) {
							if(nv.getChuVu().equals(chucVu)) {
								dsNVTim.add(nv);
							}
						}
						
					} else if(txtTimTenNV.getText().trim().length() == 0 && txtTimSoDT.getText().trim().length() == 0) {
						for(NhanVien nv : dsNVTheoCV) {
							dsNVTim.add(nv);
						}
					}
				}
				
				loadNV(dsNVTim);
				dsNVTim.removeAll(dsNVTim);
				if(tableCA.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Không có nhân viên cần tìm!");
				}
			}
		});
		btnTim.setIcon(new ImageIcon(FrameTimKiemNV.class.getResource("/image/btnTim.png")));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pTKNV.add(btnTim);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 556, 1884, 462);
		pTKNV.add(scrollPane);

		tableCA = new JTable();
		tableCA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		String[] table = { "Mã nhân viên", "Tên nhân viên", "CMND", "Số điện thoại", "Địa Chỉ", "Chức vụ"};
		dftableCA = new DefaultTableModel(table, 0);
		tableCA = new JTable(dftableCA);
		tableCA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

			}
		});
		scrollPane.setViewportView(tableCA);
		
		JButton btnXaTrng = new JButton("Xóa trắng");
		btnXaTrng.setBounds(1253, 460, 150, 40);
		btnXaTrng.setIcon(new ImageIcon(FrameTimKiemNV.class.getResource("/image/btnXoaTrang.png")));
		btnXaTrng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputKH();
				
			}
		});
		btnXaTrng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pTKNV.add(btnXaTrng);
		
		loadCboMaNV();
		 cboMaNV.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(cboMaNV.getSelectedIndex() != 0) {
		 			txtTimTenNV.setEditable(false);
		 			txtTimTenNV.setFocusable(false);
		 			txtTimSoDT.setEditable(false);
		 			txtTimSoDT.setFocusable(false);
		 			cboChucVu.setEnabled(false);
		 		} else {
		 			txtTimTenNV.setEditable(true);
		 			txtTimTenNV.setFocusable(true);
		 			txtTimSoDT.setEditable(true);
		 			txtTimSoDT.setFocusable(true);
		 			cboChucVu.setEnabled(true);
		 		}
		 		txtTimTenNV.setText("");
		 		cboChucVu.setSelectedIndex(0);
		 	}
		 });
		pTKNV.add(cboMaNV);
		
		JLabel lblChucVu = new JLabel("Chức Vụ");
		lblChucVu.setBounds(567, 393, 119, 33);
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pTKNV.add(lblChucVu);
		
		loadCboChucVu();
		pTKNV.add(cboChucVu);

	}
	
	public void xoaHetDuLieu() {
		DefaultTableModel dm = (DefaultTableModel) tableCA.getModel();
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadCboMaNV() {
		nhanVienDao = new NhanVienDao();
		Vector<String> vector = new Vector<>();
		vector.add("Tất cả");
		ArrayList<NhanVien> dsNV = nhanVienDao.getAllNV();
		for(NhanVien nv : dsNV) {
			vector.add(nv.getMaNV());
		}
		cboMaNV = new JComboBox(vector);
		cboMaNV.setBounds(820, 152, 574, 38);
	}
	
	public void loadNVTheoMa() {
		String maNV = "";
		nhanVienDao = new NhanVienDao();
		if(cboMaNV.getSelectedIndex() != 0) {
			maNV = cboMaNV.getSelectedItem().toString();
			try {
				NhanVien nv = nhanVienDao.getNVTheoMa(maNV);
				dftableCA.addRow(new Object[] {
						nv.getMaNV(),
						nv.getTenNV(),
						nv.getcMND(),
						nv.getsDT(),
						nv.getDiaChi(),
						nv.getChuVu()
					});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void loadNVTheoChucVu() {
		String chucVu = "";
		nhanVienDao = new NhanVienDao();
		ArrayList<NhanVien> dsNV = nhanVienDao.getAllNV();
		ArrayList<NhanVien> dsNVTheoCV = new ArrayList<>();
		if(cboChucVu.getSelectedIndex() != 0) {
			chucVu = cboChucVu.getSelectedItem().toString();
			for(NhanVien nv : dsNV) {
				if(nv.getChuVu().equals(chucVu)) {
					dsNVTheoCV.add(nv);
				}
			}
			for(NhanVien nv : dsNVTheoCV) {
				dftableCA.addRow(new Object[] {
						nv.getMaNV(),
						nv.getTenNV(),
						nv.getcMND(),
						nv.getsDT(),
						nv.getDiaChi(),
						nv.getChuVu()
					});
			}
			
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadCboChucVu() {
		nhanVienDao = new NhanVienDao();
		Vector<String> vector = new Vector<>();
		vector.add("Tất cả");
		ArrayList<NhanVien> dsNV = nhanVienDao.getAllNV();
		for(NhanVien nv : dsNV) {
			vector.add(nv.getChuVu());
		}
		cboChucVu = new JComboBox(vector);
		cboChucVu.setBounds(820, 391, 580, 38);
		cboChucVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboChucVu.getSelectedIndex() == 0) {
					cboMaNV.setEnabled(true);
				} else {
					cboMaNV.setEnabled(false);
				}
			}
		});
	}
	
	public ArrayList<NhanVien> loadNVTheoTen() {
		String tenNV = txtTimTenNV.getText();
		nhanVienDao = new NhanVienDao();
		ArrayList<NhanVien> dsNVTheoTen = nhanVienDao.getNVTheoTen(tenNV);
		return dsNVTheoTen;
	}
	
	public void loadNV(ArrayList<NhanVien> dsNV) {
		for(NhanVien nv : dsNV) {
			dftableCA.addRow(new Object[] {
					nv.getMaNV(),
					nv.getTenNV(),
					nv.getcMND(),
					nv.getsDT(),
					nv.getDiaChi(),
					nv.getChuVu()
				});
		}
		
	}
	
	public ArrayList<NhanVien> loadNVTheoSDT() {
		String sdt = txtTimSoDT.getText();
		nhanVienDao = new NhanVienDao();
		ArrayList<NhanVien> dsNVTheoSDT = nhanVienDao.getNVTheoSDT(sdt);
		return dsNVTheoSDT;
	}
	
	public ArrayList<NhanVien> loadNVTheoTenSDT() {
		String tenNV = txtTimTenNV.getText();
		String sdt = txtTimSoDT.getText();
		nhanVienDao = new NhanVienDao();
		ArrayList<NhanVien> dsNVTheoTenSDT = nhanVienDao.getNVTheoTenSDT(tenNV, sdt);
		return dsNVTheoTenSDT;
	}
	
	public ArrayList<NhanVien> loadNVTheoTenSDTCV() {
		String tenNV = txtTimTenNV.getText();
		String sdt = txtTimSoDT.getText();
		String chucVu = cboChucVu.getSelectedItem().toString();
		nhanVienDao = new NhanVienDao();
		ArrayList<NhanVien> dsNVTheoTenSDTCV = nhanVienDao.getNVTheoTenSDT(tenNV, sdt);
		return dsNVTheoTenSDTCV;
			
	}
	
	public void disableCbo() {
		if(txtTimTenNV.getText().trim().length() != 0 || txtTimSoDT.getText().trim().length() != 0) {
			cboMaNV.setEnabled(false);
		}
		
	}
	
	public boolean checkSytax() {
		String input = txtTimSoDT.getText();
		String patternStr = "^\\d+$";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(input);
		boolean matcherFound = matcher.find();
		if(!matcherFound) {
			JOptionPane.showMessageDialog(null, "Số điện thoại nhập không hợp lệ");
			return false;
		}
		return true;
	}

	public void emityiuputKH(){
	    txtTimTenNV.setText("");
	    txtTimSoDT.setText("");
	    cboChucVu.setEnabled(true);
	    cboMaNV.setEnabled(true);
	}
	
	
}
