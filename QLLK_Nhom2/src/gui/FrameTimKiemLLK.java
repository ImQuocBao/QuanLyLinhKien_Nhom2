package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.LoaiLinhKienDao;
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
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrameTimKiemLLK extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtTenLLK;

	@SuppressWarnings("unused")
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	@SuppressWarnings("unused")
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	@SuppressWarnings("unused")
	private String tenLoaiLKTim = "";
	private LoaiLinhKienDao loaiLinhKienDao = new LoaiLinhKienDao();
	@SuppressWarnings("unused")
	private ArrayList<LinhKien> dsLKTheoTen;
	@SuppressWarnings("unused")
	private ArrayList<LinhKien> dsLKTheoDonGia;
	@SuppressWarnings("unused")
	private ArrayList<LinhKien> dsLKTheoTenLLK;
	@SuppressWarnings("unused")
	private ArrayList<LinhKien> dsLKTheoTenNCC;
	@SuppressWarnings("unused")
	private ArrayList<LinhKien> dsLKTheoLoai;
	@SuppressWarnings("unused")
	private ArrayList<LinhKien> dsLKTheoNCC;
	@SuppressWarnings("unused")
	private ArrayList<LinhKien> dsLKTheoLoaiNCC;
	@SuppressWarnings("unused")
	private List<NhaCungCap> dsNCC;
	@SuppressWarnings("unused")
	private LoaiLinhKienDao loaiLinhKien;
	@SuppressWarnings("rawtypes")
	private JComboBox cboMaLLK;
	
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
	public FrameTimKiemLLK() throws Exception {
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
		panel_1.setBounds(10, 123, 1915, 357);
		pTK_DH.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã Loại Linh Kiện");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(400, 52, 294, 35);
		panel_1.add(lblNewLabel);

		txtTenLLK = new JTextField();
		txtTenLLK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTenLLK.getText().trim().length() != 0) {
					cboMaLLK.setEnabled(false);
				} else {
					cboMaLLK.setEnabled(true);
				}
			}
		});
		txtTenLLK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenLLK.setBounds(715, 137, 502, 44);
		txtTenLLK.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				cboMaLLK.setEnabled(false);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				cboMaLLK.setEnabled(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				cboMaLLK.setEnabled(false);
			}
		});
		panel_1.add(txtTenLLK);
		txtTenLLK.setColumns(10);


		JButton btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(FrameTimKiemLLK.class.getResource("/image/btnTim.png")));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaHetDuLieu();
				if(cboMaLLK.getSelectedIndex() != 0) {
					try {
						loadLLKTheoMa();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						
						if(txtTenLLK.getText().trim().length() > 0) {
							try {
								loadLLKTheoTen();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							loadLLK();
						}
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
				
				if(table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Không có linh kiện cần tìm!");
				}
			}
		});
		btnTim.setBounds(797, 209, 232, 68);
		panel_1.add(btnTim);
		
		JLabel lblTnLoiLinh = new JLabel("Tên Loại Linh Kiện");
		lblTnLoiLinh.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTnLoiLinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTnLoiLinh.setBounds(419, 142, 263, 39);
		panel_1.add(lblTnLoiLinh);
		
		loadCboMaLLK();
		cboMaLLK.setBounds(715, 46, 501, 52);
		panel_1.add(cboMaLLK);

		String col[] = { "Mã Loại Linh Kiện", "Tên Loại Linh Kiện"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBounds(0, 0, 1, 1);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 485, 1918, 533);
		pTK_DH.add(scrollPane);
		JLabel lblNewLabel_2 = new JLabel("Tìm Kiếm Loại Linh Kiện");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(295, 35, 1253, 79);
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
	
	public void loadLLK() throws ClassNotFoundException, SQLException {
		loaiLinhKienDao = new LoaiLinhKienDao();
		List<LoaiLinhKien> dsLLK = loaiLinhKienDao.getAll();
		for(LoaiLinhKien llk : dsLLK) {
			tableModel.addRow(new Object[] {
					llk.getMaLoaiLK(),
					llk.getTenLoaiLK()
			});
		}
	}
	
	public void loadLLKTheoMa() throws ClassNotFoundException, SQLException {
		String maLLK = cboMaLLK.getSelectedItem().toString();
		loaiLinhKienDao = new LoaiLinhKienDao();
		LoaiLinhKien llk = loaiLinhKienDao.getLLKTheoID(maLLK);
		tableModel.addRow(new Object[] {
				llk.getMaLoaiLK(),
				llk.getTenLoaiLK()
		});
	}
	
	public void loadLLKTheoTen() throws Exception {
		String tenLLK = txtTenLLK.getText();
		loaiLinhKienDao = new LoaiLinhKienDao();
		LoaiLinhKien llk = loaiLinhKienDao.getLLKTheoTen(tenLLK);
		tableModel.addRow(new Object[] {
				llk.getMaLoaiLK(),
				llk.getTenLoaiLK()
		});
	}
	
	public void loadCboMaLLK() throws ClassNotFoundException, SQLException {
		loaiLinhKienDao = new LoaiLinhKienDao();
		List<LoaiLinhKien> dsLLK = loaiLinhKienDao.getAll();
		Vector<String> dsMaLLK = new Vector<>();
		dsMaLLK.add("Tất cả");
		for(LoaiLinhKien llk : dsLLK) {
			dsMaLLK.add(llk.getMaLoaiLK());
		}
		cboMaLLK = new JComboBox<String>(dsMaLLK);
		cboMaLLK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboMaLLK.getSelectedIndex() != 0) {
					txtTenLLK.setEnabled(false);
					txtTenLLK.setFocusable(false);
				} else {
					txtTenLLK.setEnabled(true);
					txtTenLLK.setFocusable(true);
				}
			}
		});
	}


	@SuppressWarnings("unused")
	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(null, message);
	}
}