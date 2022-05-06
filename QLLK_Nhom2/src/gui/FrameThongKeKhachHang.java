package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.CTHoaDonDao;
import dao.KhachHangDao;
import dao.NhaCungCapDao;

import dao.LinhKienDao;
import entity.CTHoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhaCungCap;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "unused" })
public class FrameThongKeKhachHang extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameThongKeKhachHang frame = new FrameThongKeKhachHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txt_bd;
	private JTextField txt_kt;


	/**
	 * Create the frame.
	 */
	public FrameThongKeKhachHang() {
		setBounds(-5, -26, 1950, 1080);
		getContentPane().setLayout(null);
		
		JLabel lblname = new JLabel("Thống kê khách hàng");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblname.setBounds(650, 55, 479, 88);
		getContentPane().add(lblname);
		
		JLabel lblngayBD = new JLabel("Từ Ngày");
		lblngayBD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblngayBD.setBounds(317, 213, 108, 27);
		getContentPane().add(lblngayBD);
		
		txt_bd = new JTextField();
		txt_bd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_bd.setBounds(406, 202, 252, 47);
		getContentPane().add(txt_bd);
		
		JLabel lblngaykt = new JLabel("Đến ngày");
		lblngaykt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblngaykt.setBounds(676, 213, 108, 27);
		getContentPane().add(lblngaykt);
		
		txt_kt = new JTextField();
		txt_kt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_kt.setBounds(774, 204, 313, 45);
		getContentPane().add(txt_kt);
		
		
		JButton btn_thongke = new JButton("Thống kê");
		btn_thongke.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_thongke.setBounds(1201, 201, 167, 56);
		getContentPane().add(btn_thongke);
		
		JButton btn_xoarong = new JButton("Xoá rỗng");
		btn_xoarong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_xoarong.setBounds(1454, 201, 160, 57);
		getContentPane().add(btn_xoarong);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 318, 1904, 617);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] tableName= {"Mã KH", "Tên KH"," SĐT ", "Địa chỉ",  "Giới tính", "Số hoá đơn"};
		tableModel=new DefaultTableModel(tableName,0);
		table.setModel(tableModel);
		
		JLabel lblNamTong = new JLabel("Tổng hoá đơn:");
		lblNamTong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNamTong.setBounds(1099, 977, 150, 27);
		getContentPane().add(lblNamTong);
		
		JLabel lbl_hoadon = new JLabel("0");
		lbl_hoadon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_hoadon.setBounds(1259, 980, 46, 21);
		getContentPane().add(lbl_hoadon);
		
		JLabel lbl_khachhang = new JLabel("0");
		lbl_khachhang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_khachhang.setBounds(685, 967, 46, 27);
		getContentPane().add(lbl_khachhang);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng khách hàng:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(468, 967, 166, 27);
		getContentPane().add(lblNewLabel_2);
		btn_xoarong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_bd.setText("");
				txt_kt.setText("");
			}
		});
		btn_thongke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				XoaTrenTableModel();
				String ngaybd=txt_bd.getText().toString();
				String ngaykt=txt_kt.getText().toString();
				
				String bd=txt_bd.getText().trim();
				String kt=txt_kt.getText().trim();
				
				
				if(bd.length()==0 && kt.length()==0) {
					txt_bd.requestFocus();
					XoaTrenTableModel();
					KhachHangDao khacHangDAO=new KhachHangDao();
					
					//ArrayList<KhachHang> list=khacHangDAO.getkhtheoNgay(ngaybd, ngaykt);
					int sokhachhang=0;
					int sohoadon=0;
					for(Entry<KhachHang, Integer> dsKH : khacHangDAO.getallKH().entrySet()) {
						KhachHang kh = dsKH.getKey();
						sokhachhang+=1;
						sohoadon+=dsKH.getValue();
						tableModel.addRow(new Object[] {
							kh.getMaKH(),
							kh.getTenKH(),
							kh.getSoDienThoai(),
							kh.getDiaChi(),
							kh.getGioiTinh(),
							dsKH.getValue()
						});	
					}	
					
					lbl_khachhang.setText(String.valueOf(sokhachhang));
					lbl_hoadon.setText(String.valueOf(sohoadon));
				
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
				else if(bd.length()!=0 &&kt.length()!=0) {
					txt_bd.requestFocus();
		
					KhachHangDao khacHangDAO=new KhachHangDao();
					
					//ArrayList<KhachHang> list=khacHangDAO.getkhtheoNgay(ngaybd, ngaykt);
					int sokhachhang=0;
					int sohoadon=0;
					for(Entry<KhachHang, Integer> dsKH : khacHangDAO.getkhtheoNgay(ngaybd, ngaykt).entrySet()) {
						KhachHang kh = dsKH.getKey();
						sokhachhang+=1;
						sohoadon+=dsKH.getValue();
						tableModel.addRow(new Object[] {
							kh.getMaKH(),
							kh.getTenKH(),
							kh.getSoDienThoai(),
							kh.getDiaChi(),
							kh.getGioiTinh(),
							dsKH.getValue()
						});
					}	
					lbl_khachhang.setText(String.valueOf(sokhachhang));
					lbl_hoadon.setText(String.valueOf(sohoadon));
					
				}
				
			}
		});
	}
	private void XoaTrenTableModel() {
		DefaultTableModel dftm=(DefaultTableModel) table.getModel();
		dftm.getDataVector().removeAllElements();
	
	}
}
