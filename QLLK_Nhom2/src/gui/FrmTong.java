package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JDesktopPane;

@SuppressWarnings("serial")
public class FrmTong extends JFrame {
	private FrameHeThongTrangChu frameHeThongTrangchu;
	private FrameDanhMucLK frameDanhMucLK;
	private FrameDanhMucNcc frameDanhMucNCC;
	private FrameXuLyDonHang frameXuLyDH;
	
	private JMenu mnTimKiem, mnHeThong, mnDanhMuc, mnXuLy, mnThongKe;
	private JMenuItem mntmTrangChu, mntmDM_LK, mntmDM_DMLK, mntmDM_NV, mntmDM_KH, mntmDM_NCC, mntmTao_DH, mntmTao_HD, mntmTimKiem_KH, mntmTimKiem_NV,
			mntmNewMenuItem_1, mntmTimKiem_LK, mntmTK_DT, mntmTK_KH, mntmTK_LK, mntmTK_NV;
	private JPanel contentPane;
	private JPanel pCenter;
	private JDesktopPane desktopPane;
	private FrameTimKiemLK frameTimKiemLK;
	private FrameTimKiemNV frameTimKiemNV;
	private FrameTimKiemKH frameTimKiemKH;
	private FrameTimKiemLLK frameTimKiemLLK;
	private FrameDanhMucNV frameDanhMucNV;
	private FrameDanhMucKH frameDanhMucKH;
	private FrameThongKeDoanhThu frameThongKeDT;
	private FrameThongKeKhachHang frameThongKeKH;
	private FrameThongKeSanPham frameThongKeSP;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTong frame = new FrmTong("QL1","Quản lý");
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
	public FrmTong(String maNV,String chucVu) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1950, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pCenter = new JPanel();
		pCenter.setBackground(Color.PINK);
		contentPane.add(pCenter);
		pCenter.setBounds(5, 43, 1950, 1071);
		pCenter.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.ORANGE);
		desktopPane.setBounds(0, 0, 1950, 1080);
		pCenter.add(desktopPane);
		
		frameHeThongTrangchu = new FrameHeThongTrangChu(maNV);
		frameHeThongTrangchu.setBounds(-10, -35, 1950, 1080);
		desktopPane.add(frameHeThongTrangchu);
		frameHeThongTrangchu.setVisible(true);
		setTitle("Quản Lý Linh Kiện");
		
		
////--=====================Xử lý==========================--
//
		frameXuLyDH = new FrameXuLyDonHang(maNV);
		desktopPane.add(frameXuLyDH);

//		frameXuLyHD = new FrameXuLyHoaDon(maNV);
//		desktopPane.add(frameXuLyHD);
//
////--======================Danh muc==================--
//
		frameDanhMucNV = new FrameDanhMucNV();
		desktopPane.add(frameDanhMucNV);

		frameDanhMucKH = new FrameDanhMucKH();
		desktopPane.add(frameDanhMucKH);
//
		frameDanhMucLK = new FrameDanhMucLK();
		desktopPane.add(frameDanhMucLK);

//		frameDanhMucDCHT = new FrameDanhMucDCHT();
//		desktopPane.add(frameDanhMucDCHT);
//
//		frameDanhMucDM = new FrameDanhMucDM();
//		desktopPane.add(frameDanhMucDM);
//
		frameDanhMucNCC = new FrameDanhMucNcc();
		desktopPane.add(frameDanhMucNCC);
//		
//		frameDanhMucNXB = new FrameDanhMucNXB();
//		desktopPane.add(frameDanhMucNXB);
//		
//		frameDanhMucTG = new FrameDanhMucTG();
//		desktopPane.add(frameDanhMucTG);

//--=========================Thống kê===================--

		frameThongKeDT = new FrameThongKeDoanhThu();

		desktopPane.add(frameThongKeDT);
//
		frameThongKeKH = new FrameThongKeKhachHang();
		desktopPane.add(frameThongKeKH);
//
//		frameThongKeNV = new FrameThongKeNV();
//		desktopPane.add(frameThongKeNV);
//
		frameThongKeSP = new FrameThongKeSanPham();
		desktopPane.add(frameThongKeSP);
//
////--==============================TimKiem-==================		
//
		frameTimKiemKH = new FrameTimKiemKH();
		desktopPane.add(frameTimKiemKH);
//
		frameTimKiemNV = new FrameTimKiemNV();
		desktopPane.add(frameTimKiemNV);
//
		frameTimKiemLK = new FrameTimKiemLK();
		desktopPane.add(frameTimKiemLK);
		
		frameTimKiemLLK = new FrameTimKiemLLK();
		desktopPane.add(frameTimKiemLLK);
//
//		frameTimKiemDCHT = new FrameTimKiemDCHT();
//		desktopPane.add(frameTimKiemDCHT);

//--==============================Menu======================--

		JPanel pMenu = new JPanel();
		pMenu.setBounds(5, 5, 1950, 38);
		contentPane.add(pMenu);
		pMenu.setLayout(new CardLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("window"));
		pMenu.add(menuBar, "name_417659960722100");
//		=================================================================

		mnHeThong = new JMenu("H\u1EC7 Th\u1ED1ng");
		mnHeThong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHeThong.setIcon(new ImageIcon(FrmTong.class.getResource("/image/chip.png")));
		menuBar.add(mnHeThong);

		mntmTrangChu = new JMenuItem("Trang ch\u1EE7");
		mntmTrangChu.setBackground(new Color(135, 206, 250));
		mntmTrangChu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameHeThongTrangchu.setVisible(true);
				mntmTrangChu.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTrangChu.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItem_homeElectronic.png")));
		mnHeThong.add(mntmTrangChu);

		JMenuItem mntmDangXuat = new JMenuItem("\u0110\u0103ng xu\u1EA5t");
		mntmDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLogin frmLogin = new FrmLogin();
				frmLogin.pack();
				frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmLogin.setBounds(300, 100, 800, 400);
				frmLogin.setFocusCycleRoot(true);
				frmLogin.setFocusableWindowState(true);
				frmLogin.setVisible(true);
				dispose();
			}
		});
		mntmDangXuat.setIcon(new ImageIcon(FrmTong.class.getResource("/image/plug.png")));
		mnHeThong.add(mntmDangXuat);

		JMenuItem mntmThoat = new JMenuItem("Tho\u00E1t");
		mntmThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmThoat.setIcon(new ImageIcon(FrmTong.class.getResource("/image/door.png")));
		mnHeThong.add(mntmThoat);

//		==============================================================================
		mnDanhMuc = new JMenu("Danh mục");
		mnDanhMuc.setIcon(new ImageIcon(FrmTong.class.getResource("/image/categories.png")));
		menuBar.add(mnDanhMuc);

		mntmDM_DMLK = new JMenuItem("Loại Linh Kiện");
		mntmDM_DMLK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
//				frameDanhMucDM.setVisible(true);
				mntmDM_DMLK.setBackground(new Color(135, 206, 250));
				try {
//					frameDanhMucDM.loadDM();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmDM_DMLK.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemQLLK.png")));
		mnDanhMuc.add(mntmDM_DMLK);

		mntmDM_LK = new JMenuItem("Linh kiện");
		mntmDM_LK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucLK.setVisible(true);
				mntmDM_LK.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucLK.loadSP();
					frameDanhMucLK.loadLlkToCombo();
					frameDanhMucLK.loadNccToCombo();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmDM_LK.setIcon(new ImageIcon(FrmTong.class.getResource("/image/cpu.png")));
		mnDanhMuc.add(mntmDM_LK);

		mntmDM_NCC = new JMenuItem("Nhà cung cấp");
		mntmDM_NCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				hienTatCa();
				anTatCa();
				chinhMau();
				frameDanhMucNCC.setVisible(true);
				try {
					frameDanhMucNCC.loadNCC();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				mntmDM_NCC.setBackground(new Color(135, 206, 250));
			}
		});
		mntmDM_NCC.setIcon(new ImageIcon(FrmTong.class.getResource("/image/supplier.png")));
		mnDanhMuc.add(mntmDM_NCC);
//		---------------------------Huyền------------

		mntmDM_KH = new JMenuItem("Khách hàng");
		mntmDM_KH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				hienTatCa();
				anTatCa();
				chinhMau();
				frameDanhMucNV.setVisible(true);
				frameDanhMucNV.setVisible(false);
				frameDanhMucKH.setVisible(true);
				mntmDM_KH.setBackground(new Color(135, 206, 250));
				
			}
		});
		mntmDM_KH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemQLKH.png")));
		mnDanhMuc.add(mntmDM_KH);

		mntmDM_NV = new JMenuItem("Nhân viên");
		mntmDM_NV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucNV.setVisible(true);
				mntmDM_NV.setBackground(new Color(135, 206, 250));
			}
		});
		mntmDM_NV.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemQLNV.png")));
		mnDanhMuc.add(mntmDM_NV);
		//=====================================================================
		mnXuLy = new JMenu("Xử lý");
		mnXuLy.setIcon(new ImageIcon(FrmTong.class.getResource("/image/predictive-models.png")));
		menuBar.add(mnXuLy);

		mntmTao_HD = new JMenuItem("Thêm hóa đơn");
		mntmTao_HD.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTaoHD.png")));
		mntmTao_HD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
//				frameXuLyHD.setVisible(true);
				mntmTao_HD.setBackground(new Color(135, 206, 250));
			}
		});
		mnXuLy.add(mntmTao_HD);

		mntmTao_DH = new JMenuItem("Thêm đơn hàng");
		mntmTao_DH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTaoDH.png")));
		mntmTao_DH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameXuLyDH.setVisible(true);
				try {
					frameXuLyDH.loadDH();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				mntmTao_DH.setBackground(new Color(135, 206, 250));
			}
		});
		mnXuLy.add(mntmTao_DH);

//		==========================================================

		mnTimKiem = new JMenu("Tìm kiếm thông tin");
		mnTimKiem.setIcon(new ImageIcon(FrmTong.class.getResource("/image/seo.png")));
		menuBar.add(mnTimKiem);

		mntmTimKiem_LK = new JMenuItem("Tìm kiếm Linh Kiện");
		mntmTimKiem_LK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemLK.setVisible(true);
				mntmTimKiem_LK.setBackground(new Color(135, 206, 250));
				try {
//					frameTimKiemSP.loadSP();
//					frameTimKiemSP.loadLSP();
//					frameTimKiemSP.loadTG();
//					frameTimKiemSP.loadNXB();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmTimKiem_LK.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKS.png")));
		mnTimKiem.add(mntmTimKiem_LK);

		mntmNewMenuItem_1 = new JMenuItem("Tìm kiếm Loại Linh Kiện");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemLLK.setVisible(true);
				mntmNewMenuItem_1.setBackground(new Color(135, 206, 250));
				try {
//					frameTimKiemDCHT.loadSP();
//					frameTimKiemDCHT.loadNXB();;
//					frameTimKiemDCHT.loadLSP();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMDCHT.png")));
		mnTimKiem.add(mntmNewMenuItem_1);

		mntmTimKiem_NV = new JMenuItem("Tìm kiếm nhân viên");
		mntmTimKiem_NV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemNV.setVisible(true);
				mntmTimKiem_NV.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTimKiem_NV.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTimKiemNV.png")));
		mnTimKiem.add(mntmTimKiem_NV);

		mntmTimKiem_KH = new JMenuItem("Tìm kiếm khách hàng");
		mntmTimKiem_KH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemKH.setVisible(true);
				mntmTimKiem_KH.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTimKiem_KH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTimKiemKH.png")));
		mnTimKiem.add(mntmTimKiem_KH);

//		=======================================================================

		mnThongKe = new JMenu("Th\u1ED1ng k\u00EA");
		mnThongKe.setIcon(new ImageIcon(FrmTong.class.getResource("/image/data-analysis.png")));
		menuBar.add(mnThongKe);

		mntmTK_DT = new JMenuItem("Th\u1ED1ng k\u00EA doanh thu");
		mntmTK_DT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameThongKeDT.setVisible(true);
				mntmTK_DT.setBackground(new Color(135, 206, 250));
				try {
//					frameThongKeDT.loadHD();
//					frameThongKeDT.khoiTaoTop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmTK_DT.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKDT.png")));
		mnThongKe.add(mntmTK_DT);

		mntmTK_KH = new JMenuItem("Th\u1ED1ng k\u00EA kh\u00E1ch h\u00E0ng");
		mntmTK_KH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameThongKeKH.setVisible(true);
				mntmTK_KH.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTK_KH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKKH.png")));
		mnThongKe.add(mntmTK_KH);

		mntmTK_LK = new JMenuItem("Thống kê Linh Kiện");
		mntmTK_LK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameThongKeSP.setVisible(true);
				try {

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mntmTK_LK.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTK_LK.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKSP.png")));
		mnThongKe.add(mntmTK_LK);

		mntmTK_NV = new JMenuItem("Th\u1ED1ng k\u00EA nh\u00E2n vi\u00EAn");
		mntmTK_NV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
//				frameThongKeNV.setVisible(true);
				mntmTK_NV.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTK_NV.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKNV.png")));
		mnThongKe.add(mntmTK_NV);
		mntmTK_NV.setVisible(false);
		
		
	}
	void hienTatCa() {
		frameDanhMucNCC.setVisible(true);
		frameDanhMucNV.setVisible(true);
		frameDanhMucKH.setVisible(true);
	}
	void anTatCa() {
		frameHeThongTrangchu.setVisible(false);
//		frameHeThongDangKyCaLam.setVisible(false);
//		frameXuLyDH.setVisible(false);
//		frameXuLyHD.setVisible(false);
//		frameDanhMucDCHT.setVisible(false);
		frameDanhMucNV.setVisible(false);
		frameDanhMucKH.setVisible(false);
		frameDanhMucLK.setVisible(false);
//		frameDanhMucDM.setVisible(false);
		frameDanhMucNCC.setVisible(false);
//		frameDanhMucNXB.setVisible(false);
//		frameDanhMucTG.setVisible(false);
		frameTimKiemLLK.setVisible(false);
		frameTimKiemLK.setVisible(false);
		frameTimKiemNV.setVisible(false);
		frameTimKiemKH.setVisible(false);
		frameThongKeDT.setVisible(false);
//		frameThongKeNV.setVisible(false);
		frameThongKeKH.setVisible(false);
		frameThongKeSP.setVisible(false);
	}

	void chinhMau() {
		mntmTrangChu.setBackground(new Color(240, 240, 240));
		mntmDM_LK.setBackground(new Color(240, 240, 240));
		mntmDM_DMLK.setBackground(new Color(240, 240, 240));
		mntmDM_NV.setBackground(new Color(240, 240, 240));
		mntmDM_KH.setBackground(new Color(240, 240, 240));
		mntmDM_NCC.setBackground(new Color(240, 240, 240));
		mntmTao_DH.setBackground(new Color(240, 240, 240));
		mntmTao_HD.setBackground(new Color(240, 240, 240));
		mntmTimKiem_KH.setBackground(new Color(240, 240, 240));
		mntmTimKiem_NV.setBackground(new Color(240, 240, 240));
		mntmNewMenuItem_1.setBackground(new Color(240, 240, 240));
		mntmTimKiem_LK.setBackground(new Color(240, 240, 240));
		mntmTK_DT.setBackground(new Color(240, 240, 240));
		mntmTK_KH.setBackground(new Color(240, 240, 240));
		mntmTK_LK.setBackground(new Color(240, 240, 240));
		mntmTK_NV.setBackground(new Color(240, 240, 240));
	}

	void chechChucVu(String nhanVien) {
		if (nhanVien.equalsIgnoreCase("Nhân viên")) {
			mnThongKe.setVisible(false);
			mntmDM_NV.setVisible(false);
		} else {
			mnThongKe.setVisible(true);
			mntmDM_NV.setVisible(true);
		}
	}
}
