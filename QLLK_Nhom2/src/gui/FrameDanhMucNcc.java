package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;

import entity.NhaCungCap;
import dao.NhaCungCapDao;


@SuppressWarnings("serial")
public class FrameDanhMucNcc extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JPanel pMain;
	private JPanel panelContent;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_7;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChiNCC;
	private JTextField txtQuocTichNCC;
	private DefaultTableModel tbtDSSPModel;
	private JTable tblNcc;

	private JButton btnThem, btnXoaTrang, btnLuu, btnXoa, btnCapNhap;
	private NhaCungCapDao spsDao = new NhaCungCapDao();
	/**
	 * Create the frame.
	 */
	public FrameDanhMucNcc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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

		JLabel lblNewLabel = new JLabel("Danh Mục Nhà Cung Cấp");
		lblNewLabel.setBounds(0, -11, 1924, 135);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		pMain.add(lblNewLabel);

		panelContent = new JPanel();
		panelContent.setBackground(Color.WHITE);
		panelContent.setBounds(0, 123, 1924, 898);
		pMain.add(panelContent);
		panelContent.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã nhà cung cấp : ", SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(340, 33, 318, 43);
		panelContent.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Tên nhà cung cấp : ", SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(340, 121, 318, 43);
		panelContent.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Địa chỉ : ", SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(340, 214, 318, 43);
		panelContent.add(lblNewLabel_3);

		lblNewLabel_7 = new JLabel("Quốc Tịch : ", SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(340, 296, 318, 43);
		panelContent.add(lblNewLabel_7);

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtMaNCC.setEditable(false);
		txtMaNCC.setBounds(683, 34, 580, 42);
		panelContent.add(txtMaNCC);
		txtMaNCC.setColumns(10);

		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(683, 121, 580, 42);
		panelContent.add(txtTenNCC);

		txtDiaChiNCC = new JTextField();
		txtDiaChiNCC.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtDiaChiNCC.setColumns(10);
		txtDiaChiNCC.setBounds(683, 214, 580, 42);
		panelContent.add(txtDiaChiNCC);

		txtQuocTichNCC = new JTextField();
		txtQuocTichNCC.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		txtQuocTichNCC.setColumns(10);
		txtQuocTichNCC.setBounds(683, 299, 580, 42);
		panelContent.add(txtQuocTichNCC);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 399, 1924, 499);
		panelContent.add(scrollPane);

		String[] col1 = { "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Địa Chỉ", "Quốc Tịch"};
		tbtDSSPModel = new DefaultTableModel(col1, 0);
		tblNcc = new JTable(tbtDSSPModel);
		tblNcc.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		scrollPane.setViewportView(tblNcc);
		
		tblNcc.setRowHeight(25);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnSave.png")));
		btnLuu.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnLuu.setBounds(1445, 33, 138, 43);
		panelContent.add(btnLuu);
		
		btnCapNhap = new JButton("Cập Nhập");
		btnCapNhap.setEnabled(false);
		btnCapNhap.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnCapNhat.png")));
		btnCapNhap.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnCapNhap.setBounds(1445, 98, 138, 43);
		panelContent.add(btnCapNhap);
		
		btnXoa = new JButton("Xoá");
		btnXoa.setEnabled(false);
		btnXoa.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnXoa.png")));
		btnXoa.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnXoa.setBounds(1445, 166, 138, 43);
		panelContent.add(btnXoa);
		
		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnThem.png")));
		btnThem.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnThem.setBounds(1445, 228, 138, 43);
		panelContent.add(btnThem);
		
		btnXoaTrang = new JButton("Xoá Trắng");
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucLK.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnXoaTrang.setBounds(1445, 296, 138, 43);
		panelContent.add(btnXoaTrang);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnCapNhap.addActionListener(this);
		btnLuu.addActionListener(this);
		tblNcc.addMouseListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			xoaTrang();
			if(soSach()+1 >= 10) {
				txtMaNCC.setText("NCC"+(soSach()+1));
			} else {
				txtMaNCC.setText("NCC0"+(soSach()+1));
			}
			
			btnXoa.setEnabled(false);
			btnCapNhap.setEnabled(false);
			btnLuu.setEnabled(true);
		} else if (o.equals(btnLuu)) {
			if(validateLSP()) {
				String tenNCC= txtTenNCC.getText().trim();
				String maNCC= txtMaNCC.getText().trim();
				String daiChi= txtDiaChiNCC.getText().trim();
				String quocGia= txtQuocTichNCC.getText().trim();
				
				NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, daiChi, quocGia);
				
				try {
					spsDao.create(nhaCungCap);
					showMessage("Lưu Thành Công !!!!", txtTenNCC);
					tbtDSSPModel.addRow(new Object[] {
							nhaCungCap.getMaNCC(),nhaCungCap.getTenNCC(),nhaCungCap.getDiaChi(),nhaCungCap.getQuocTich()
					});
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
			xoaTrang();
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnXoa)) {
			String maDungCu= txtMaNCC.getText();
			int n= JOptionPane.showConfirmDialog(
                    this, 
                    "Bạn có chắc muốn XÓA NHÀ CUNG CẤP này?", 
                    "Thông báo xác nhận XÓA NHÀ CUNG CẤP này", 
                    JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION) {		
				try {
					spsDao.delete(maDungCu);
					loadNCC();
					xoaTrang();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void loadNCC() throws ClassNotFoundException, SQLException {
		int tblRow = tblNcc.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tbtDSSPModel.removeRow(i);
		}
		for (entity.NhaCungCap sp : spsDao.getallNCC()) {
			tbtDSSPModel.addRow(new Object[] { 
						sp.getMaNCC(),sp.getTenNCC(),sp.getDiaChi(),sp.getQuocTich()
				});
		}
	}
	
	public int soSach(){
		try {
			return spsDao.demSoSach();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean validateLSP() {
		String tenNCC = txtTenNCC.getText().trim();
		if (!(tenNCC.length() > 0 && tenNCC.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên Nhà Cung Cấp không được trống hoặc có kí tự đặt biệt", txtTenNCC);
			return false;
		}
		String diaChi = txtDiaChiNCC.getText().trim();
		if (!(diaChi.length() > 0 && diaChi.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Địa Chỉ không được trống hoặc có kí tự đặt biệt VD:12 Nguyễn văn Bảo", txtDiaChiNCC);
			return false;
		}
		String quocGia = txtQuocTichNCC.getText().trim();
		if (!(quocGia.length() > 0 && quocGia.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Quốc Gia không được trống hoặc có kí tự đặt biệt VD:Việt Nam ", txtQuocTichNCC);
			return false;
		}
		return true;
	}
	void xoaTrang() {
		txtMaNCC.setText("");txtTenNCC.setText("");txtDiaChiNCC.setText("");txtQuocTichNCC.setText("");
		btnLuu.setEnabled(false);
		btnXoa.setEnabled(false);
		btnCapNhap.setEnabled(true);
	}
	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int rowSelect = tblNcc.getSelectedRow();
		txtMaNCC.setText((String) tblNcc.getValueAt(rowSelect, 0));
		txtTenNCC.setText((String) tblNcc.getValueAt(rowSelect, 1));
		txtDiaChiNCC.setText((String) tblNcc.getValueAt(rowSelect, 2));
		txtQuocTichNCC.setText((String) tblNcc.getValueAt(rowSelect, 3));
		btnThem.setEnabled(true);btnLuu.setEnabled(false);
		btnCapNhap.setEnabled(true);
		btnXoa.setEnabled(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
