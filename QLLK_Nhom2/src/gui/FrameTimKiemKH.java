package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDao;
import entity.KhachHang;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("unused")
public class FrameTimKiemKH extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private JPanel contentPane;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtSoDT;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbGioiTinh;
	@SuppressWarnings("rawtypes")
	private JComboBox cboGioiTinh;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbGioiTinh_1;
	private JTable tableKH;
	private JButton btnXaTrng, btnTim;
	private DefaultTableModel dftableKH;
	private List<KhachHang> dsKHTim;
	protected KhachHangDao khachHangDao;
	protected List<KhachHang> dsKHTheoTen;
	protected List<KhachHang> dsKHTheoDiaChi;
	protected List<KhachHang> dsKHTheoSDT;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTimKiemKH frame = new FrameTimKiemKH();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrameTimKiemKH() throws ClassNotFoundException {
		cmbGioiTinh  = new JComboBox<String>();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1950, 1080);
		JPanel pTKKH = new JPanel();
		pTKKH.setBackground(Color.WHITE);
		pTKKH.setBounds(-5 ,-25, 1000, 570);
		getContentPane().add(pTKKH);
		pTKKH.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T??m ki???m th??ng tin kh??ch h??ng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(510, 25, 970, 57);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		pTKKH.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("T??n kh??ch h??ng :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(407, 139, 183, 34);
		pTKKH.add(lblNewLabel_1_1);
		
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(665, 137, 600, 40);
		pTKKH.add(txtTenKH);
		
		JLabel lblNewLabel_1_2 = new JLabel("?????a ch??? :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(408, 228, 140, 33);
		pTKKH.add(lblNewLabel_1_2);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(668, 224, 600, 40);
		pTKKH.add(txtDiaChi);
		
		JLabel lblNewLabel_1_3 = new JLabel("S??? ??i???n tho???i :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(407, 318, 140, 29);
		pTKKH.add(lblNewLabel_1_3);
		
		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(670, 309, 600, 40);
		pTKKH.add(txtSoDT);
		
		btnTim = new JButton("T??m");
		btnTim .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				khachHangDao = new KhachHangDao();
				String tenKH = txtTenKH.getText();
				String diaChi = txtDiaChi.getText();
				String sdt = txtSoDT.getText();
				ArrayList<KhachHang> dsKHTheoGT = new ArrayList<>();
				ArrayList<KhachHang> dsKHTim = new ArrayList<>();
				ArrayList<KhachHang> dsKHTenDiaChi = new ArrayList<>();
				if(tenKH.trim().length() != 0) {
					try {
						dsKHTheoTen = khachHangDao.getMaKHTenKD(tenKH);
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				if(diaChi.trim().length() != 0) {
					dsKHTheoDiaChi = khachHangDao.getKHTheoDiaChi(diaChi);
				}
				
				if(sdt.trim().length() !=0) {
					dsKHTheoSDT = khachHangDao.getKHTheoSDT(sdt);
				}
				
				if(tenKH.trim().length() != 0 && diaChi.trim().length() != 0) {
					dsKHTenDiaChi = khachHangDao.getKHTenDiaChi(tenKH, diaChi);
				}
				
				
				try {
					List<KhachHang> dsKH = khachHangDao.getalltbKhachHang();
					if(cboGioiTinh.getSelectedIndex() == 1) {
						for(KhachHang kh : dsKH) {
							if(kh.getGioiTinh().toString().equals("Nam")) {
								dsKHTheoGT.add(kh);
							}
						}
						
						if(tenKH.trim().length() > 0 && diaChi.trim().length() == 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoTen) {
								if(kh.getGioiTinh().toString().equals("Nam")) {
									dsKHTim.add(kh);
								}
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() > 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoDiaChi) {
								if(kh.getGioiTinh().toString().equals("Nam")) {
									dsKHTim.add(kh);
								}
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() == 0 && sdt.trim().length() > 0 ) {
							for(KhachHang kh : dsKHTheoSDT) {
								if(kh.getGioiTinh().toString().equals("Nam")) {
									dsKHTim.add(kh);
								}
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() == 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoGT) {
								dsKHTim.add(kh);
							}
						} else if(tenKH.trim().length() != 0 && diaChi.trim().length() != 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTenDiaChi) {
								if(kh.getGioiTinh().equals("N???")) {
									dsKHTim.add(kh);
								}
							}
						}
						
					} else if(cboGioiTinh.getSelectedIndex() == 2){
						for(KhachHang kh : dsKH) {
							if(kh.getGioiTinh().toString().equals("N???")) {
								dsKHTheoGT.add(kh);
							}
						}
						if(tenKH.trim().length() == 0 && diaChi.trim().length() == 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoGT) {
								dsKHTim.add(kh);
							}
						}
						
						if(tenKH.trim().length() > 0 && diaChi.trim().length() == 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoTen) {
								if(kh.getGioiTinh().toString().equals("N???")) {
									dsKHTim.add(kh);
								}
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() > 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoDiaChi) {
								if(kh.getGioiTinh().toString().equals("N???")) {
									dsKHTim.add(kh);
								}
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() == 0 && sdt.trim().length() > 0 ) {
							for(KhachHang kh : dsKHTheoSDT) {
								if(kh.getGioiTinh().toString().equals("N???")) {
									dsKHTim.add(kh);
								}
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() == 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoGT) {
								dsKHTim.add(kh);
							}
						} else if(tenKH.trim().length() != 0 && diaChi.trim().length() != 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTenDiaChi) {
								if(kh.getGioiTinh().equals("N???")) {
									dsKHTim.add(kh);
								}
							}
						}
					} else if(cboGioiTinh.getSelectedIndex() == 0) {
						if(tenKH.trim().length() == 0 && diaChi.trim().length() == 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKH) {
								dsKHTim.add(kh);
							}
						}
						
						if(tenKH.trim().length() > 0 && diaChi.trim().length() == 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoTen) {
								dsKHTim.add(kh);
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() > 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTheoDiaChi) {
								dsKHTim.add(kh);
							}
						} else if(tenKH.trim().length() == 0 && diaChi.trim().length() == 0 && sdt.trim().length() > 0 ) {
							for(KhachHang kh : dsKHTheoSDT) {
								dsKHTim.add(kh);
							}
						}else if(tenKH.trim().length() != 0 && diaChi.trim().length() != 0 && sdt.trim().length() == 0) {
							for(KhachHang kh : dsKHTenDiaChi) {
								dsKHTim.add(kh);
							}
						}
					}
					if(dsKHTim.size() != 0) {
						loadKH(dsKHTim);
						dsKHTim.removeAll(dsKHTim);
					} else {
						xoaHetDuLieu();
						JOptionPane.showMessageDialog(null, "Kh??ng c?? kh??ch h??ng c???n t??m!");
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}    
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.setIcon(new ImageIcon(FrameTimKiemKH.class.getResource("/image/btnTim.png")));
		btnTim.setBounds(1435, 386, 150, 40);
		pTKKH.add(btnTim );
		
	
		JLabel lblNewLabel_1_3_1 = new JLabel("Gi???i t??nh :");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3_1.setBounds(412, 384, 140, 31);
		pTKKH.add(lblNewLabel_1_3_1);
		
		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"T???t C???", "Nam", "N???"}));
		cboGioiTinh.setBounds(673, 381, 598, 40);
		pTKKH.add(cboGioiTinh);
		
		btnXaTrng = new JButton("X??a tr???ng");
		btnXaTrng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputKH();
				
			}
		});
		btnXaTrng.setIcon(new ImageIcon(FrameTimKiemKH.class.getResource("/image/btnXoaTrang.png")));
		btnXaTrng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXaTrng.setBounds(1616, 386, 150, 40);
		pTKKH.add(btnXaTrng);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 495, 1909, 523);
		pTKKH.add(scrollPane);
		
		tableKH = new JTable();
		tableKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		String[] table = {  "M?? kh??ch h??ng","T??n Kh??ch h??ng", "S??? ??i???n tho???i","?????a ch???","Gi???i t??nh"} ;
		dftableKH = new DefaultTableModel(table, 0);
		tableKH = new JTable(dftableKH);
		
		scrollPane.setViewportView(tableKH);
	}
	
	public void loadKH(ArrayList<KhachHang> dsKH) {
		xoaHetDuLieu();
		for(KhachHang kh : dsKH) {
			dftableKH.addRow(new Object[] {
					kh.getMaKH(),
					kh.getTenKH(),
					kh.getSoDienThoai(),
					kh.getDiaChi(),
					kh.getGioiTinh()
			});
		}
	}
	
	public void xoaHetDuLieu() {
		DefaultTableModel dm = (DefaultTableModel) tableKH.getModel();
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
	
	
	public void emityiuputKH(){
	    txtTenKH.setText("");
	    txtDiaChi.setText("");
	    txtSoDT.setText("");
	 	cboGioiTinh.setSelectedItem(0);
	 	txtTenKH.requestFocus();
	}
	

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
			txt.requestFocus();
			JOptionPane.showMessageDialog(null, message);
	}

}
