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

import dao.NhanVienDao;
import entity.NhanVien;

import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;


public class FrameDanhMucNV extends JInternalFrame{

	/**
	 * Launch the application.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtCMND;
	private JTextField txtSoDT;
	private JTextField txtDiaChi;
	private JComboBox<String> cmbChucVu;
	private JTable tableNV;
	private DefaultTableModel dftableNV;
	@SuppressWarnings("unused")
	private JButton btnLuu,btnThem,btnXoaTrang,btnCapNhat;
	private ArrayList<NhanVien> listNV;
	
	NhanVienDao nv_dao;
	private JButton btnXoa;
	public FrameDanhMucNV() throws ClassNotFoundException {
		
		nv_dao = new NhanVienDao();
		cmbChucVu  = new JComboBox<String>();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		
		setResizable(true);
		setBounds(-5, -26, 1950, 1080);
		JPanel panelNV = new JPanel();
		panelNV.setBounds(0, 0, 1186, 639);
		getContentPane().add(panelNV);
		panelNV.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nhân viên", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, new Color(30, 144, 255)));
		panel.setBounds(26, 10, 1885, 525);
		panelNV.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		panel_1.setForeground(Color.CYAN);
		panel_1.setBorder(new TitledBorder(null, "Quản lý nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 21, 1854, 371);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên");
		lblMNhnVin.setForeground(Color.BLACK);
		lblMNhnVin.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblMNhnVin.setBounds(30, 53, 265, 26);
		panel_1.add(lblMNhnVin);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(305, 48, 422, 38);
		panel_1.add(txtMaNV);
		txtMaNV.setEditable(false);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setForeground(Color.BLACK);
		lblTnNhnVin.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblTnNhnVin.setBounds(954, 53, 232, 26);
		panel_1.add(lblTnNhnVin);
		
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(1238, 48, 451, 39);
		panel_1.add(txtTenNV);
		
		JLabel lblChngMinhNhn = new JLabel("Chứng minh nhân dân");
		lblChngMinhNhn.setForeground(Color.BLACK);
		lblChngMinhNhn.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblChngMinhNhn.setBounds(30, 158, 265, 26);
		panel_1.add(lblChngMinhNhn);
		
		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCMND.setColumns(10);
		txtCMND.setBounds(305, 153, 422, 38);
		panel_1.add(txtCMND);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setForeground(Color.BLACK);
		lblSinThoi.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblSinThoi.setBounds(954, 158, 232, 26);
		panel_1.add(lblSinThoi);
		
		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(1238, 153, 451, 38);
		panel_1.add(txtSoDT);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setForeground(Color.BLACK);
		lblaCh.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblaCh.setBounds(30, 262, 265, 26);
		panel_1.add(lblaCh);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(305, 250, 422, 38);
		panel_1.add(txtDiaChi);
		
		JLabel lblNewLabel = new JLabel("Chức vụ");
		lblNewLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblNewLabel.setBounds(954, 262, 232, 26);
		panel_1.add(lblNewLabel);
		
		cmbChucVu = new JComboBox<String>();
		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbChucVu.setModel(new DefaultComboBoxModel<String>(new String[] {"Quản lý", "Nhân viên"}));
		cmbChucVu.setBounds(1238, 250, 451, 38);
		panel_1.add(cmbChucVu);
		
		
		 btnXoa = new JButton("Xóa");
	        btnXoa.setBackground(SystemColor.activeCaption);
	        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        btnXoa.setBounds(86, 413, 200, 65);
	        btnXoa.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnXoa.png")));
	        panel.add(btnXoa);
	        btnXoa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					try {
						int vt = tableNV.getSelectedRow();
						String maNV = txtMaNV.getText();
						JOptionPane.showConfirmDialog(lblNewLabel, "Bạn chắc chắn muốn xóa");
						dftableNV.removeRow(vt);
					    nv_dao.deleteNhanVien(maNV);
					   JOptionPane.showMessageDialog(lblNewLabel, "Xóa thành công");
					    
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
			}); 
				
	        
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setBackground(SystemColor.activeCaption);
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String maNV = txtMaNV.getText();
				NhanVien nv;
				try {
					nv  = nv_dao.getNVTheoMa(maNV);
				    NhanVien nv1 = new NhanVien(nv.getMaNV(), txtTenNV.getText(), txtCMND.getText(), txtSoDT.getText(), txtDiaChi.getText(), cmbChucVu.getSelectedItem()+"");
				    nv_dao.updateNhanVien(nv1);
				    JOptionPane.showMessageDialog(lblNewLabel, "Lưu thành công");
				    System.out.println(nv1);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		}); 

		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLuu.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnSave.png")));
		btnLuu.setBounds(420, 413, 200, 65);
		panel.add(btnLuu);
		
		btnThem = new JButton("Thêm");
//		btnThem.setEnabled(false);
		btnThem.setBackground(SystemColor.activeCaption);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ma = txtMaNV.getText().toUpperCase();
					String ten = txtTenNV.getText();
					String cmnd = txtCMND.getText();
					String sdt = txtSoDT.getText();
					String diachi = txtDiaChi.getText();
					String chuvu = (String) cmbChucVu.getSelectedItem();
					NhanVien nv = new NhanVien(ma, ten, cmnd, sdt, diachi, chuvu);
				
					if(valiDataNV()) {
						
						try {
							if (nv_dao.getNVTheoMa(ma) == null) {
								System.out.println(nv);
								String[] row = { nv.getMaNV(), nv.getTenNV(), nv.getcMND(), nv.getsDT(), nv.getDiaChi(), nv.getChuVu()};
								dftableNV.addRow(row);
								nv_dao.insertNhanVien(nv);
								System.out.println(nv);
								JOptionPane.showMessageDialog(lblNewLabel, "Thêm thành công");
								emityiuputNV();
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
		btnThem.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnThem.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(791, 413, 200, 65);
		panel.add(btnThem);
		
		btnCapNhat = new JButton("Sửa");
		btnCapNhat.setBackground(SystemColor.activeCaption);
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vt = tableNV.getSelectedRow();

				if(valiDataNV()) {
					dftableNV.setValueAt(txtMaNV.getText(), vt, 0);
					dftableNV.setValueAt(txtTenNV.getText(), vt, 1);
					dftableNV.setValueAt(txtCMND.getText(), vt, 2);
					dftableNV.setValueAt(txtSoDT.getText(), vt, 3);
					dftableNV.setValueAt(txtDiaChi.getText(), vt, 4);
					dftableNV.setValueAt(cmbChucVu.getSelectedItem(), vt, 5);
					JOptionPane.showMessageDialog(lblNewLabel, "Nhấn nút lưu để lưu");
					btnLuu.setEnabled(true);
				}
				
				
			}
		});
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapNhat.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnCapNhat.png")));
		btnCapNhat.setBounds(1152, 413, 200, 65);
		panel.add(btnCapNhat);
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBackground(SystemColor.activeCaption);
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThem.setEnabled(true);
				txtMaNV.setEditable(true);
				btnCapNhat.setEnabled(false);
				emityiuputNV();
//				btnThem.setEnabled(false);
			}
		});
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setBounds(1543, 413, 200, 65);
		panel.add(btnXoaTrang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 546, 1885, 458);
		panelNV.add(scrollPane);
		
		tableNV = new JTable();
		tableNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				cmbChucVu.setEditable(true);
				int vt = tableNV.getSelectedRow();
				txtMaNV.setText(tableNV.getValueAt(vt, 0).toString());
		        txtTenNV.setText(tableNV.getValueAt(vt, 1).toString());
		        txtCMND.setText(tableNV.getValueAt(vt, 2).toString());
		        txtSoDT.setText(tableNV.getValueAt(vt, 3).toString());
		        txtDiaChi.setText(tableNV.getValueAt(vt, 4).toString());
		        cmbChucVu.setSelectedItem(tableNV.getValueAt(vt, 5).toString());
		        btnXoa.setEnabled(true);
		    	btnCapNhat.setEnabled(true);
			}
		});
			
		
		String[] table = {  "Mã nhân viên","Tên nhân viên","CMND", "Số điện thoại","Địa chỉ","Chức vụ" } ;
		dftableNV = new DefaultTableModel(table, 0);
		tableNV.setRowHeight(30);
		scrollPane.setViewportView(tableNV);
		showTableNV();
		
		selectedRow();
        
       
	}
	private void selectedRow() {
		tableNV.setRowSelectionInterval(0, 0);
		txtMaNV.setText(tableNV.getValueAt(0, 0).toString());
        txtTenNV.setText(tableNV.getValueAt(0, 1).toString());
        txtCMND.setText(tableNV.getValueAt(0, 2).toString());
        txtSoDT.setText(tableNV.getValueAt(0, 3).toString());
        txtDiaChi.setText(tableNV.getValueAt(0, 4).toString());
        cmbChucVu.setSelectedItem(tableNV.getValueAt(0, 5).toString());
	}
	//them nhan vien
	

	public void emityiuputNV(){
	    txtMaNV.setText("");
	    txtTenNV.setText("");
	    txtCMND.setText("");
	    txtDiaChi.setText("");
	    txtSoDT.setText("");
	 	txtMaNV.requestFocus();
	 	tableNV.clearSelection();
	 	btnXoa.setEnabled(false);
	}
//	
	public  void showTableNV() throws ClassNotFoundException{
		
//	    this.setLocationRelativeTo(null);
	    listNV = new NhanVienDao().getAllNhanVien();
	    dftableNV = (DefaultTableModel) tableNV.getModel();
	    if(tableNV.getRowCount() > 0){
	        for (int i = tableNV.getRowCount() - 1; i > -1; i--) {
	        	dftableNV.removeRow(i);
	        }
	    }
	    dftableNV.setColumnIdentifiers(new Object[]{
	    		"Mã nhân viên","Tên nhân viên","CMND", "Số điện thoại","Địa chỉ","Chức vụ"
	    });
	   for(NhanVien kh : listNV){
		   dftableNV.addRow(new Object[]{
	          kh.getMaNV(),kh.getTenNV(),kh.getcMND(),kh.getsDT(), kh.getDiaChi(),kh.getChuVu()
	       });
	   }
	}
	
	public boolean valiDataNV() {
		String maNV = txtMaNV.getText().trim();
		String ten=txtTenNV.getText().trim();
		String sdt=txtSoDT.getText().trim();
		String cmnd = txtCMND.getText().trim();
		if(!(maNV.length()>0 && maNV.matches("^(NV|nv)[0-9]{2,}$"))) {
			showMessage("Error: Ma nhan vien phai bat dau NV[SỐ] ví dụ NV03, ít nhất là 2 số", txtMaNV);
			txtTenNV.requestFocus();
			return false;
		}
		
		if (!(ten.length() > 0 && ten.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Ten Phai Theo Mau : Nguyen Van Anh", txtTenNV);
			txtTenNV.requestFocus();
			return false;
		}
		if(!(sdt.length()>0 && sdt.matches("^[0-9]{10}$"))) {
			showMessage("Error: SDT  Khach Hang Phai Theo Mau:^[0-9]{10}$\n VD:0329324401",txtSoDT);
			txtSoDT.requestFocus();
			return false;
		}
		if(!(cmnd.length()>0 && cmnd.matches("^[0-9]{9}$"))) {
			txtCMND.requestFocus();
			showMessage("Error: Chứng minh nhân dân phải có 9  chữ số",txtSoDT);
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
