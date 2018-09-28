/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.ui;

import com.bachkhoa.control.SerilizeFactory;
import com.bachkhoa.control.SinhVienTCService;
import com.bachkhoa.model.MonHocTinChi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Long Nguyen Nhat
 */
public class QuanLySinhVienTinChi extends JFrame {

//    những thuộc tính panel trái
    JList<MonHocTinChi> listMonHocTinChi;
    JButton btnDangKiMonHocTinChi, btnCacMonTotNghiep;

//    những thuộc tính panel phải
    DefaultTableModel dtmDanhSachMonHocTinChiDangKy, dtmMonTotNghiep;
    JTable tblDanhSachMonHocTinChi, tblMonTotNghiep;
    JTextField txtDiemGiuaKy, txtDiemCuoiKy, txtTenMonHoc, txtSoTinChi, txtMonHocTinChiDieuKien;
    JButton btnKiemTraQuaMon;
    MonHocTinChi MonHocTinChiSelected;
    Vector<MonHocTinChi> MonHocTinChiDaTotNghiep = new Vector<>();

    public QuanLySinhVienTinChi(String title) {
        super(title);
        addControls();
        addEvents();
        hienThiDanhMucLenList();
    }

    private void hienThiDanhMucLenList() {
        SinhVienTCService sv = new SinhVienTCService();
        Vector<MonHocTinChi> vec = sv.danhSachMonHocTinChi();
        listMonHocTinChi.setListData(vec);
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

//        tạo ra 2 panel chính
        JPanel pnLeft = new JPanel();
        pnLeft.setPreferredSize(new Dimension(250, 0));
        JPanel pnRight = new JPanel();
        JSplitPane spMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
        spMain.setOneTouchExpandable(true);//tạo di chuyển cho dải phân cách
        con.add(spMain);

        pnLeft.setLayout(new BorderLayout());

//        tạo Jlist môn học
        listMonHocTinChi = new JList<MonHocTinChi>();
        JScrollPane scListMonHocTinChi = new JScrollPane(listMonHocTinChi,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnLeft.add(scListMonHocTinChi, BorderLayout.CENTER);

//        các Jbutton của pnLeft
        JPanel pnButton = new JPanel();
        pnLeft.add(pnButton, BorderLayout.SOUTH);
        pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnDangKiMonHocTinChi = new JButton("Đăng Kí");
        btnCacMonTotNghiep = new JButton("Các môn tốt nghiệp");
        pnButton.add(btnDangKiMonHocTinChi);
        pnButton.add(btnCacMonTotNghiep);

//        tạo border
        TitledBorder borderListMonHocTinChi = new TitledBorder(BorderFactory.createLineBorder(Color.red), "Danh sách môn học", TEXT_CURSOR, TitledBorder.CENTER);
        borderListMonHocTinChi.setTitleColor(Color.red);
        listMonHocTinChi.setBorder(borderListMonHocTinChi);

//        thiết kế giao diện phải
        pnRight.setLayout(new BorderLayout());

//        thiết kế giao diện trên phải
        JPanel pnTopOfRight = new JPanel();
        pnTopOfRight.setPreferredSize(new Dimension(0, 200));
        JPanel pnBottomOfRight = new JPanel();
        pnBottomOfRight.setPreferredSize(new Dimension(0, 300));
        JSplitPane spRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTopOfRight, pnBottomOfRight);
        spRight.setOneTouchExpandable(true);
        pnRight.add(spRight);
//        thiết kế bảng trên
        pnTopOfRight.setLayout(new BorderLayout());
        dtmDanhSachMonHocTinChiDangKy = new DefaultTableModel();
        dtmDanhSachMonHocTinChiDangKy.addColumn("Tên Môn Học");
        dtmDanhSachMonHocTinChiDangKy.addColumn("Số Tín chỉ");
        dtmDanhSachMonHocTinChiDangKy.addColumn("Môn Học Điều Kiện");
        dtmDanhSachMonHocTinChiDangKy.addColumn("kết quả đăng kí");
        tblDanhSachMonHocTinChi = new JTable(dtmDanhSachMonHocTinChiDangKy);
        JScrollPane scDanhSachMonHocTinChiDangKi = new JScrollPane(tblDanhSachMonHocTinChi,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        TitledBorder borderDanhSachMonHocTinChiDangKy = new TitledBorder(BorderFactory.createLineBorder(Color.red), "danh sách môn học đăng kí");
        scDanhSachMonHocTinChiDangKi.setBorder(borderDanhSachMonHocTinChiDangKy);
        pnTopOfRight.add(scDanhSachMonHocTinChiDangKi, BorderLayout.CENTER);

//        thiết kế giao diện dưới phải
        pnBottomOfRight.setLayout(new BorderLayout());
        JPanel pnCenterOfRight = new JPanel();
        pnCenterOfRight.setPreferredSize(new Dimension(0, 300));
        JPanel pnBottomOfBottomOfRight = new JPanel();
        pnBottomOfBottomOfRight.setPreferredSize(new Dimension(0, 300));
        JSplitPane spBottomOfRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnCenterOfRight, pnBottomOfBottomOfRight);
        spBottomOfRight.setOneTouchExpandable(true);
        pnBottomOfRight.add(spBottomOfRight);
//        thiết kế center
        pnCenterOfRight.setLayout(new BoxLayout(pnCenterOfRight, BoxLayout.Y_AXIS));

        JPanel pnTenMonHoc = new JPanel();
        pnCenterOfRight.add(pnTenMonHoc);
        pnTenMonHoc.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTenMonHoc = new JLabel("tên Môn học: ");
        txtTenMonHoc = new JTextField(30);
        pnTenMonHoc.add(lblTenMonHoc);
        pnTenMonHoc.add(txtTenMonHoc);

        JPanel pnSoTinChi = new JPanel();
        pnCenterOfRight.add(pnSoTinChi);
        pnSoTinChi.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblSoTinChi = new JLabel("Số tín chỉ: ");
        txtSoTinChi = new JTextField(30);
        pnSoTinChi.add(lblSoTinChi);
        pnSoTinChi.add(txtSoTinChi);

        JPanel pnMonHocTinChiDieuKien = new JPanel();
        pnCenterOfRight.add(pnMonHocTinChiDieuKien);
        pnMonHocTinChiDieuKien.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblMonHocTinChiDieuKien = new JLabel("Môn học điều kiện: ");
        txtMonHocTinChiDieuKien = new JTextField(30);
        pnMonHocTinChiDieuKien.add(lblMonHocTinChiDieuKien);
        pnMonHocTinChiDieuKien.add(txtMonHocTinChiDieuKien);

        JPanel pnDiemGiuaKy = new JPanel();
        pnCenterOfRight.add(pnDiemGiuaKy);
        pnDiemGiuaKy.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblDiemGiuaKy = new JLabel("Điểm giữa kỳ: ");
        txtDiemGiuaKy = new JTextField(30);
        pnDiemGiuaKy.add(lblDiemGiuaKy);
        pnDiemGiuaKy.add(txtDiemGiuaKy);

        JPanel pnDiemCuoiKy = new JPanel();
        pnCenterOfRight.add(pnDiemCuoiKy);
        pnDiemCuoiKy.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblDiemCuoiKy = new JLabel("Điểm cuối kỳ: ");
        txtDiemCuoiKy = new JTextField(30);
        pnDiemCuoiKy.add(lblDiemCuoiKy);
        pnDiemCuoiKy.add(txtDiemCuoiKy);

        lblTenMonHoc.setPreferredSize(lblMonHocTinChiDieuKien.getPreferredSize());
        lblSoTinChi.setPreferredSize(lblMonHocTinChiDieuKien.getPreferredSize());
        lblDiemCuoiKy.setPreferredSize(lblMonHocTinChiDieuKien.getPreferredSize());
        lblDiemGiuaKy.setPreferredSize(lblMonHocTinChiDieuKien.getPreferredSize());

        JPanel pnButtonCenter = new JPanel();
        pnCenterOfRight.add(pnButtonCenter);
        pnButtonCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnKiemTraQuaMon = new JButton("kiểm tra qua môn");
        pnButtonCenter.add(btnKiemTraQuaMon);

        TitledBorder borderThongTinMonHocTinChi = new TitledBorder(BorderFactory.createLineBorder(Color.red), "Thông tin môn học", TEXT_CURSOR, TitledBorder.CENTER);
        pnCenterOfRight.setBorder(borderThongTinMonHocTinChi);

////        thiết kế bên dưới của bên phải
        pnBottomOfBottomOfRight.setLayout(new BorderLayout());
        dtmMonTotNghiep = new DefaultTableModel();
        dtmMonTotNghiep.addColumn("Tên Môn Học");
        dtmMonTotNghiep.addColumn("Điểm Giữa KỲ");
        dtmMonTotNghiep.addColumn("Điểm Cuối Kỳ");
        dtmMonTotNghiep.addColumn("Kết quả tốt nghiệp");
        tblMonTotNghiep = new JTable(dtmMonTotNghiep);
        JScrollPane scMonTotNghiep = new JScrollPane(tblMonTotNghiep, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnBottomOfBottomOfRight.add(scMonTotNghiep, BorderLayout.CENTER);
        TitledBorder borderMonTotNghiep = new TitledBorder(BorderFactory.createLineBorder(Color.red), "Xét Kiểm tra tốt nghiệp");
        borderMonTotNghiep.setTitlePosition(TitledBorder.CENTER);
        borderMonTotNghiep.setTitleColor(Color.red);
        scMonTotNghiep.setBorder(borderMonTotNghiep);

//        JPanel pnLast = new JPanel();
//        JSplitPane spBottom = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnBottomOfBottomOfRight, spRight)
    }

    private void addEvents() {
        listMonHocTinChi.addMouseListener(new MouseListener() { //xử lí click môn học

            @Override
            public void mouseClicked(MouseEvent e) {
                MonHocTinChiSelected = listMonHocTinChi.getSelectedValue();
                if (MonHocTinChiSelected == null) {
                    return;
                }

//                System.out.println("clicked!");
                txtDiemGiuaKy.setText("");
                txtDiemCuoiKy.setText("");
                txtMonHocTinChiDieuKien.setText(MonHocTinChiSelected.getMonHocTinChiDieuKien() + "");
                txtSoTinChi.setText(MonHocTinChiSelected.getSoTinChi() + "");
                txtTenMonHoc.setText(MonHocTinChiSelected.getTenMonHoc());

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        btnCacMonTotNghiep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyHienThiCacMonTotNghiep();
            }

        });

        btnDangKiMonHocTinChi.addActionListener(new ActionListener() { //xử lý đăng kí môn học
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MonHocTinChiSelected.getMonHocTinChiDieuKien()==null) {
                    hienThiMonHocTinChiDangKi();
                    return;
                }
                if (!(MonHocTinChiDaTotNghiep.contains(MonHocTinChiSelected.getMonHocTinChiDieuKien()))) {
                    JOptionPane.showMessageDialog(null, "bạn chưa qua môn " + MonHocTinChiSelected.getMonHocTinChiDieuKien().toString());
                } else {
                    hienThiMonHocTinChiDangKi();
                }
                
            }
        });

        tblDanhSachMonHocTinChi.addMouseListener(new MouseListener() { //xử lí hiển thị tại textfield
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblDanhSachMonHocTinChi.getSelectedRow();
                if (row == -1) {
                    return;
                }
                String TenMonHoc = tblDanhSachMonHocTinChi.getValueAt(row, 0) + "";
                String soTinChi = tblDanhSachMonHocTinChi.getValueAt(row, 1) + "";
                String monHocDieuKien = tblDanhSachMonHocTinChi.getValueAt(row, 2) + "";
                txtTenMonHoc.setText(TenMonHoc);
                txtSoTinChi.setText(soTinChi);
                txtMonHocTinChiDieuKien.setText(monHocDieuKien);
                txtDiemGiuaKy.setText("");
                txtDiemCuoiKy.setText("");

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        btnKiemTraQuaMon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKiemTraQuaMon();
            }

        });

    }

    private void xuLyHienThiCacMonTotNghiep() {
        if (MonHocTinChiDaTotNghiep.isEmpty()) {
            JOptionPane.showMessageDialog(null, "chưa có danh sách các môn đã tốt nghiệp");
            return;
        }
        MonHocTinChiDaTotNghiep = SerilizeFactory.docFile("database\\MonHocTinChiDaTotNghiep.db");
        listMonHocTinChi.setListData(MonHocTinChiDaTotNghiep);
    }

    private void xuLyKiemTraQuaMon() {//xử lý kiểm tra  môn này có qua không
        try {
            double diemGiuaKy = Double.parseDouble(txtDiemGiuaKy.getText());
            double diemCuoiKy = Double.parseDouble(txtDiemCuoiKy.getText());
            SinhVienTCService service = new SinhVienTCService();
            double diemTongKet = service.tinhDiemTotNghiep(diemGiuaKy, diemCuoiKy);
            if (diemTongKet >= 4) { //qua môn
                JOptionPane.showMessageDialog(null, "điểm tổng kết: " + diemTongKet + "\nmôn này qua rồi nha!");
                Vector<Object> vec = new Vector<>();
                vec.add(txtTenMonHoc.getText());
                vec.add(txtDiemGiuaKy.getText());
                vec.add(txtDiemCuoiKy.getText());
                vec.add("PASS");
                dtmMonTotNghiep.addRow(vec);
                if (xuLyLuuMonHocTinChi()) {
                    return;
                }
            } else { //tạch
                JOptionPane.showMessageDialog(null, "điểm tổng kết: " + diemTongKet + "\nmôn này tạch rồi nha!");
                Vector<Object> vec = new Vector<>();
                vec.add(txtTenMonHoc.getText());
                vec.add(txtDiemGiuaKy.getText());
                vec.add(txtDiemCuoiKy.getText());
                vec.add("FAIL");
                dtmMonTotNghiep.addRow(vec);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "nhập sai rồi! số thực mà");
            return;
        }
    }

    private boolean xuLyLuuMonHocTinChi() {//lưu môn học đã tốt nghiệp vào file
        String TenMonHoc = txtTenMonHoc.getText();
//        int soTinChi = Integer.parseInt(txtSoTinChi.getText());
//        double diemGiuaKy = Double.parseDouble(txtDiemGiuaKy.getText());
//        double diemCuoiKy = Double.parseDouble(txtDiemCuoiKy.getText());
        for (MonHocTinChi MonHocTinChi : MonHocTinChiDaTotNghiep) {//nếu đã lưu rồi thì không lưu nữa
            if (MonHocTinChi.getTenMonHoc().equalsIgnoreCase(TenMonHoc)) {
                return false;
            }
        }
//        MonHocTinChi mh = new MonHocTinChi(TenMonHoc, soTinChi, MonHocTinChiSelected, diemGiuaKy, diemCuoiKy);
        MonHocTinChiDaTotNghiep.add(listMonHocTinChi.getSelectedValue()); //đưa môn học đã qua vào danh sách đã qua môn

        if (SerilizeFactory.luuFile(MonHocTinChiDaTotNghiep, "database\\MonHocTinChiDaTotNghiep.db")) {
            return true;
        }
        return false;
    }

    public void hienThiMonHocTinChiDangKi() {//hiển thị các môn đã đăng kí lên bảng trên
        if (MonHocTinChiSelected == null) {
            JOptionPane.showMessageDialog(null, "đã chọn môn đâu mà đăng ký cái QQ!");
            return;

        }

        Vector<Object> vec = new Vector<>();

        vec.add(MonHocTinChiSelected.getTenMonHoc());
        vec.add(MonHocTinChiSelected.getSoTinChi());
        vec.add(MonHocTinChiSelected.getMonHocTinChiDieuKien());
        vec.add("SUCCESS");

        dtmDanhSachMonHocTinChiDangKy.addRow(vec);
    }

    public void ShowWindow() {
        this.setSize(900, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
