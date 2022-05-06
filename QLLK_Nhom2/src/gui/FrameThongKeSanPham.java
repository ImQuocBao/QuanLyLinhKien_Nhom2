package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.CTHoaDonDao;
import dao.HoaDonDao;
import dao.NhaCungCapDao;
import dao.LinhKienDao;

import entity.CTHoaDon;
import entity.HoaDon;
import entity.LinhKien;
import entity.NhaCungCap;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings({ "serial", "unused" })
public class FrameThongKeSanPham extends JInternalFrame {
	private JTextField txt_bd;
	@SuppressWarnings("unused")
	private JTextField textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txt_kt;
	@SuppressWarnings("unused")
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private JLabel lbl_soluong;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameThongKeSanPham frame = new FrameThongKeSanPham();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameThongKeSanPham() {
		getContentPane().setLayout(null);
		setBounds(-5, -26, 1950, 1080);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thống Kê Linh Kiện");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(739, 54, 448, 73);
		getContentPane().add(lblNewLabel);
		
		JLabel lblngayBD = new JLabel("Từ Ngày");
		lblngayBD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblngayBD.setBounds(258, 209, 108, 27);
		getContentPane().add(lblngayBD);
		
		txt_bd = new JTextField();
		txt_bd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_bd.setBounds(341, 201, 277, 36);
		getContentPane().add(txt_bd);
		txt_bd.setColumns(10);
		
		JLabel lblngaykt = new JLabel("Đến ngày");
		lblngaykt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblngaykt.setBounds(715, 208, 108, 27);
		getContentPane().add(lblngaykt);
		
		txt_kt = new JTextField();
		txt_kt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_kt.setBounds(809, 202, 292, 36);
		getContentPane().add(txt_kt);
		txt_kt.setColumns(10);
		
		JButton btn_xoarong = new JButton("Xoá rỗng");
		btn_xoarong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_xoarong.setBounds(1548, 206, 132, 37);
		getContentPane().add(btn_xoarong);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 304, 1876, 635);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] tableName= {"Mã Linh kiện", "Tên","Đơn giá ", "Số Lượng",  "Tên NCC"};
		tableModel=new DefaultTableModel(tableName,0);
		table.setModel(tableModel);
		
		JButton btn_sp = new JButton("LK trong kho");
		btn_sp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_sp.setBounds(1375, 206, 140, 37);
		getContentPane().add(btn_sp);
		
		JButton btn_soluong = new JButton("LK đã bán");
		btn_soluong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_soluong.setBounds(1200, 206, 140, 37);
		getContentPane().add(btn_soluong);
		
		JLabel lbl_SP = new JLabel("Số sản phẩm ");
		lbl_SP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_SP.setBounds(1454, 972, 178, 27);
		getContentPane().add(lbl_SP);
		
		lbl_soluong = new JLabel("0");
		lbl_soluong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_soluong.setBounds(1642, 972, 60, 27);
		getContentPane().add(lbl_soluong);
		moKhoaTextfields(false);
		btn_soluong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				moKhoaTextfields(true);
				// TODO Auto-generated method stub
				XoaTrenTableModel();
				Integer soluong=0;
				String ngaybd=txt_bd.getText().toString();
				String ngaykt=txt_kt.getText().toString();
				
				String bd=txt_bd.getText().trim();
				String kt=txt_kt.getText().trim();
				if(bd.length()==0 &&kt.length()==0) {
					txt_bd.requestFocus();
					CTHoaDonDao ctHoaDonDAO=new CTHoaDonDao();
					ArrayList<CTHoaDon> list;
					
						list = ctHoaDonDAO.getallCTHD();
						for(CTHoaDon cthd:list) {
							LinhKienDao sanPhamDAO = new  LinhKienDao();
							LinhKien lKien = sanPhamDAO.getLinhKienMa(cthd.getLinhKien().getMaLK());
							
							NhaCungCapDao nhaCungCapDAO=new NhaCungCapDao();
							NhaCungCap nhaCungCap=nhaCungCapDAO.getNCCMaNcc(lKien.getNhaCungCap().getMaNCC());
							soluong+=cthd.getSoLuong();
							tableModel.addRow(new Object[] {
									
									cthd.getLinhKien().getMaLK(),
									lKien.getTenLK(),
									currencyVN.format(lKien.getDonGia()),
									cthd.getSoLuong(),
									nhaCungCap.getTenNCC()
									
							});
						}
						lbl_soluong.setText(String.valueOf(soluong));
				}

				else if(!(bd.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}")||bd.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}"))){
					JOptionPane.showMessageDialog(scrollPane, "Ngày phải là số theo định dạng YYYY-M(M)-D(D)");
				}
				else if(bd.length()>0 && kt.length()==0) {
					JOptionPane.showMessageDialog(scrollPane, "Bạn chưa nhập ngày kết thúc");
				}
				else if(bd.length()==0 && kt.length()>0) {
					JOptionPane.showMessageDialog(scrollPane, "Bạn chưa nhập ngày bắt đầu");
				}
				
				else if(bd.length()!=0 && kt.length()!=0) {
					txt_bd.requestFocus();
					CTHoaDonDao ctHoaDonDAO=new CTHoaDonDao();
					ArrayList<CTHoaDon> list;
					try {
						list = ctHoaDonDAO.getSP(ngaybd, ngaykt);
						for(CTHoaDon cthd:list) {
							LinhKienDao sanPhamDAO = new  LinhKienDao();
							LinhKien lKien = sanPhamDAO.getLinhKienMa(cthd.getLinhKien().getMaLK());
							
							NhaCungCapDao nhaCungCapDAO=new NhaCungCapDao();
							NhaCungCap nhaCungCap=nhaCungCapDAO.getNCCMaNcc(lKien.getNhaCungCap().getMaNCC());
							soluong+=cthd.getSoLuong();
							tableModel.addRow(new Object[] {
									cthd.getLinhKien().getMaLK(),
									lKien.getTenLK(),
									currencyVN.format(lKien.getDonGia()),
									cthd.getSoLuong(),
									nhaCungCap.getTenNCC()
									
							});
						}
						lbl_soluong.setText(String.valueOf(soluong));
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
				
		}
		});
		btn_xoarong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				moKhoaTextfields(true);
				txt_bd.setText("");
				txt_kt.setText("");
				txt_bd.requestFocus();
			}
		});
		btn_sp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				moKhoaTextfields(false);
				LinhKienDao sanPhamDAO=new LinhKienDao();
				ArrayList<LinhKien> list=sanPhamDAO.getallLK();
				XoaTrenTableModel();
				
				int soluong=0;
				for(LinhKien lk:list) {
					NhaCungCapDao nhaCungCapDAO=new NhaCungCapDao();
					NhaCungCap nhaCungCap=nhaCungCapDAO.getNCCMaNcc(lk.getNhaCungCap().getMaNCC());
					
					soluong+=lk.getSoLuong();
					tableModel.addRow(new Object[] {
							lk.getMaLK(),
							lk.getTenLK(),
							currencyVN.format(lk.getDonGia()),
							lk.getSoLuong(),
							nhaCungCap.getTenNCC()
					});
				}
				lbl_soluong.setText(String.valueOf(soluong));
			}
		});
}
	private void XoaTrenTableModel() {
		DefaultTableModel dftm=(DefaultTableModel) table.getModel();
		dftm.getDataVector().removeAllElements();
	
	}
	private void moKhoaTextfields(boolean b) {
		txt_bd.setEditable(b);
		txt_kt.setEditable(b);
		
	}
}
