package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.HoaDonDao;
import entity.HoaDon;

import javax.swing.JScrollPane;

@SuppressWarnings({ "serial", "unused" })
public class FrameThongKeDoanhThu extends JInternalFrame {
	private JTextField txt_ngaybd;
	
	private JTable table;
	private DefaultTableModel tableModel;
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	@SuppressWarnings("unused")
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@SuppressWarnings("unused")
	private LocalDate fromDay = LocalDate.of(2021, 11, 13);
	@SuppressWarnings("unused")
	private LocalDate endDay = LocalDate.now();

	private JTextField txt_ngaykt;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameThongKeDoanhThu frame = new FrameThongKeDoanhThu();
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
	public FrameThongKeDoanhThu() {
		setBounds(-5, -26, 1950, 1080);
		getContentPane().setLayout(null);
		
		JLabel lbl_tt = new JLabel("Tổng tiền là");
		lbl_tt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_tt.setBounds(972, 970, 143, 26);
		getContentPane().add(lbl_tt);
		
		JLabel lbl_TongTien = new JLabel("0");
		lbl_TongTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_TongTien.setBounds(1127, 973, 272, 20);
		getContentPane().add(lbl_TongTien);
		
		JLabel lbl_name = new JLabel("Thống Kê Doanh Thu");
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lbl_name.setBounds(642, 50, 517, 64);
		getContentPane().add(lbl_name);
		
		JLabel lbl_ngaybd = new JLabel("Từ ngày");
		lbl_ngaybd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_ngaybd.setBounds(269, 229, 106, 34);
		getContentPane().add(lbl_ngaybd);
		
		txt_ngaybd = new JTextField();
		txt_ngaybd.setBounds(384, 230, 325, 33);
		getContentPane().add(txt_ngaybd);
		txt_ngaybd.setColumns(10);
		
		JLabel lbl_NgayKT = new JLabel("Đến ngày");
		lbl_NgayKT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_NgayKT.setBounds(755, 235, 106, 21);
		getContentPane().add(lbl_NgayKT);
		
		txt_ngaykt = new JTextField();
		txt_ngaykt.setBounds(889, 228, 334, 35);
		getContentPane().add(txt_ngaykt);
		//txt_ngaykt.setColumns(10);
		
		
		JButton btn_thongke = new JButton("Thống Kê");
		btn_thongke.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_thongke.setBounds(1322, 222, 135, 42);
		getContentPane().add(btn_thongke);
		
		JButton btn_xoarong = new JButton("Xoá trắng");
		btn_xoarong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_xoarong.setBounds(1490, 222, 143, 42);
		getContentPane().add(btn_xoarong);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 353, 1898, 592);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] tableName= {"Mã hoá đơn", "Ngày lập hoá đơn", "Mã khách hàng", "Mã nhân viên ", "Tổng tiền", "Tiền khách đưa", "Tiền thừa"};
		tableModel=new DefaultTableModel(tableName,0);
		table.setModel(tableModel);
		
		JLabel lbl_soluong = new JLabel("Số hoá đơn");
		lbl_soluong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_soluong.setBounds(401, 970, 125, 26);
		getContentPane().add(lbl_soluong);
		
		JLabel lbl_sohd = new JLabel("0");
		lbl_sohd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_sohd.setBounds(594, 970, 73, 26);
		getContentPane().add(lbl_sohd);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"Mã hoá đơn", "Ngày lập hoá đơn", "Mã khách hàng", "Mã nhân viên ", "Tổng tiền", "Tiền khách đưa", "Tiền thừa", "Trạng thái"
//			}
//		));
		
//		tableModel=new DefaultTableModel(tablename,0);
//		bang= new JTable(tableModel);
//		bang.setPreferredScrollableViewportSize(new Dimension(800,200));
//		tren.add(new JScrollPane(bang));
		
		btn_thongke.addActionListener(new ActionListener() {
			
			private JTextField txt;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				XoaTrenTableModel();
				String ngaybd=txt_ngaybd.getText().toString();
				String ngaykt=txt_ngaykt.getText().toString();
				
				String bd=txt_ngaybd.getText().trim();
				String kt=txt_ngaykt.getText().trim();
				if(bd.length()==0 && kt.length()==0) {
					txt_ngaybd.requestFocus();
					HoaDonDao hoaDonDAO=new HoaDonDao();
					ArrayList<HoaDon> list=hoaDonDAO.getallHD();
					double Tongtien=0;
					int soluong=0;
					for(HoaDon hd:list) {
						Tongtien+=hd.getTongThanhTien();
						soluong+=1;
						tableModel.addRow(new Object[] {
								hd.getMaHD(),
								hd.getNgayTao(),
								hd.getKhachHang().getMaKH(),
								hd.getNhanVien().getMaNV(),
								hd.getTongThanhTien(),
								hd.getTienMat(),
								hd.getTienDu()
						});
					}
					lbl_TongTien.setText(currencyVN.format(Tongtien));
					lbl_sohd.setText(String.valueOf(soluong));

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
				else {
				HoaDonDao hDao=new HoaDonDao();
				double TongTien=0;
				int soluong=0;
//				hDao.getHDtheoNgay(ngaybd, ngaykt);
				ArrayList<HoaDon> list=hDao.getHDtheoNgay(ngaybd, ngaykt);
				for(HoaDon hd:list) {
						soluong+=1;
						TongTien+=hd.getTongThanhTien();
						tableModel.addRow(new Object[] {
								hd.getMaHD(),
								hd.getNgayTao(),
								hd.getKhachHang().getMaKH(),
								hd.getNhanVien().getMaNV(),
								hd.getTongThanhTien(),
								hd.getTienMat(),
								hd.getTienDu()
						});
						
						}
				lbl_TongTien.setText(currencyVN.format(TongTien));
				lbl_sohd.setText(String.valueOf(soluong));
				if(list.size()==0) {
					XoaTrenTableModel();
					JOptionPane.showMessageDialog(scrollPane, "Không có hoá đơn trong ngày ");
				}
				
				}
				
//				HoaDonDAO hDao=new HoaDonDAO();
//				int a=hDao.Tongtien(ngaybd,ngaykt);
				
				
			}
		});
		btn_xoarong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_ngaybd.setText("");
				txt_ngaykt.setText("");
			}
		});
	}
	private void XoaTrenTableModel() {
		DefaultTableModel dftm=(DefaultTableModel) table.getModel();
		dftm.getDataVector().removeAllElements();
	
	}
	

	}
