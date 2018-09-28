/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.ui;

import com.bachkhoa.control.SinhVienNCService;
import com.bachkhoa.control.SinhVienTCService;
import com.bachkhoa.model.MonHocNienChe;
import com.bachkhoa.model.MonHocTinChi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.Frame.TEXT_CURSOR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
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
public class QuanLySinhVienNienChe extends JFrame {

    //    những thuộc tính panel trái
    JList<MonHocNienChe> listMonHocNienChe;

//    những thuộc tính panel phải
    DefaultTableModel dtmDanhSachMonHocNienCheDangKy, dtmMonTotNghiep;
    JTable tblDanhSachMonHocNienChe, tblMonTotNghiep;
    JTextField txtDiemGiuaKy, txtDiemCuoiKy, txtTenMonHocNienChe, txtSoDonViHocTrinh;
    JButton btnKetQuaMon, btnKiemTraTotNghiep;
    MonHocNienChe MonHocNienCheSelected;
    double diemTrungBinhMonNC;
    int tongSoDonViHocTrinhCacMon = 0;

    public QuanLySinhVienNienChe(String title) {
        super(title);
        addControls();
        addEvents();
        hienThiDanhMucLenList();
    }

    private void hienThiDanhMucLenList() {
        SinhVienNCService sv = new SinhVienNCService();
        Vector<MonHocNienChe> vec = sv.danhSachMonHocNienChe();
        listMonHocNienChe.setListData(vec);
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
        listMonHocNienChe = new JList<MonHocNienChe>();
        JScrollPane scListMonHocNienChe = new JScrollPane(listMonHocNienChe,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnLeft.add(scListMonHocNienChe, BorderLayout.CENTER);

//        tạo border
        TitledBorder borderListMonHocNienChe = new TitledBorder(BorderFactory.createLineBorder(Color.red), "Danh sách môn học", TEXT_CURSOR, TitledBorder.CENTER);
        borderListMonHocNienChe.setTitleColor(Color.red);
        listMonHocNienChe.setBorder(borderListMonHocNienChe);

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
        dtmDanhSachMonHocNienCheDangKy = new DefaultTableModel();
        dtmDanhSachMonHocNienCheDangKy.addColumn("Tên Môn Học");
        dtmDanhSachMonHocNienCheDangKy.addColumn("Số Đơn vị học trình");
        dtmDanhSachMonHocNienCheDangKy.addColumn("điểm giữa kỳ");
        dtmDanhSachMonHocNienCheDangKy.addColumn("điểm cuối kì");
        dtmDanhSachMonHocNienCheDangKy.addColumn("tổng kết môn");
        tblDanhSachMonHocNienChe = new JTable(dtmDanhSachMonHocNienCheDangKy);
        JScrollPane scDanhSachMonHocNienChe = new JScrollPane(tblDanhSachMonHocNienChe,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        TitledBorder borderDanhSachMonHocNienChe = new TitledBorder(BorderFactory.createLineBorder(Color.red), "Thông tin môn học");
        scDanhSachMonHocNienChe.setBorder(borderDanhSachMonHocNienChe);
        pnTopOfRight.add(scDanhSachMonHocNienChe, BorderLayout.CENTER);

////        thiết kế giao diện dưới phải
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

        JPanel pnTenMonHocNienChe = new JPanel();
        pnCenterOfRight.add(pnTenMonHocNienChe);
        pnTenMonHocNienChe.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lbltenMonHocNienChe = new JLabel("tên Môn học: ");
        txtTenMonHocNienChe = new JTextField(30);
        pnTenMonHocNienChe.add(lbltenMonHocNienChe);
        pnTenMonHocNienChe.add(txtTenMonHocNienChe);

        JPanel pnSoDonViHocTrinh = new JPanel();
        pnCenterOfRight.add(pnSoDonViHocTrinh);
        pnSoDonViHocTrinh.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblSoDonViHocTrinh = new JLabel("số đơn vị học trình: ");
        txtSoDonViHocTrinh = new JTextField(30);
        pnSoDonViHocTrinh.add(lblSoDonViHocTrinh);
        pnSoDonViHocTrinh.add(txtSoDonViHocTrinh);

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

        lblDiemCuoiKy.setPreferredSize(lblSoDonViHocTrinh.getPreferredSize());
        lblDiemGiuaKy.setPreferredSize(lblSoDonViHocTrinh.getPreferredSize());
        lbltenMonHocNienChe.setPreferredSize(lblSoDonViHocTrinh.getPreferredSize());

        JPanel pnButtonCenter = new JPanel();
        pnCenterOfRight.add(pnButtonCenter);
        pnButtonCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnKetQuaMon = new JButton("kết quả Môn");
        pnButtonCenter.add(btnKetQuaMon);

        TitledBorder borderThongTinMonHocNienChe = new TitledBorder(BorderFactory.createLineBorder(Color.red), "kết quả môn học", TEXT_CURSOR, TitledBorder.CENTER);
        pnCenterOfRight.setBorder(borderThongTinMonHocNienChe);

////        thiết kế bên dưới của bên phải
        pnBottomOfBottomOfRight.setLayout(new BorderLayout());
        dtmMonTotNghiep = new DefaultTableModel();
        dtmMonTotNghiep.addColumn("Trung bình chung toàn khóa");
        dtmMonTotNghiep.addColumn("Tổng số đơn vị học trình");
        dtmMonTotNghiep.addColumn("Xếp loại");
        tblMonTotNghiep = new JTable(dtmMonTotNghiep);
        JScrollPane scMonTotNghiep = new JScrollPane(tblMonTotNghiep, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnBottomOfBottomOfRight.add(scMonTotNghiep, BorderLayout.CENTER);

        btnKiemTraTotNghiep = new JButton("kiểm tra tốt nghiệp");
        pnBottomOfBottomOfRight.add(btnKiemTraTotNghiep, BorderLayout.SOUTH);

        TitledBorder borderMonTotNghiep = new TitledBorder(BorderFactory.createLineBorder(Color.red), "Xét Kiểm tra tốt nghiệp");
        borderMonTotNghiep.setTitlePosition(TitledBorder.CENTER);
        borderMonTotNghiep.setTitleColor(Color.red);
        scMonTotNghiep.setBorder(borderMonTotNghiep);

//        JPanel pnLast = new JPanel();
//        JSplitPane spBottom = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnBottomOfBottomOfRight, spRight)
    }

    private void addEvents() {
        listMonHocNienChe.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MonHocNienCheSelected = listMonHocNienChe.getSelectedValue();
                if (MonHocNienCheSelected == null) {
                    return;
                }
                hienThiMonHocNienChe();
//                System.out.println("clicked!");
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
        btnKetQuaMon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ketQuanMon();
            }
        });
        btnKiemTraTotNghiep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKiemTraTotNghiep();
            }

        });
    }

    private void ketQuanMon() {
        Vector<Object> vec = new Vector<Object>();
        try {
            vec.add(txtTenMonHocNienChe.getText());
            vec.add(txtSoDonViHocTrinh.getText());
            vec.add(txtDiemGiuaKy.getText());
            vec.add(txtDiemCuoiKy.getText());
            diemTrungBinhMonNC = SinhVienNCService.tinhDiemTongKetMonNC(
                    Double.parseDouble(txtDiemGiuaKy.getText()),
                    Double.parseDouble(txtDiemCuoiKy.getText())
            );
            vec.add(diemTrungBinhMonNC + "");
            dtmDanhSachMonHocNienCheDangKy.addRow(vec);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "nhập sai cái gì đó rồi! nhập lại đi nha");
        }

    }

    private void xuLyKiemTraTotNghiep() { //xử lý kiểm tra hết năm
        //lấy ra số hàng
        int row = dtmDanhSachMonHocNienCheDangKy.getRowCount();
        //2 vector chứa số điểm tổng kết từng môn và số học trình từng môn
        Vector<Double> tongKet = new Vector<>();
        Vector<Integer> hocTrinh = new Vector<>();

        try {
            for (int i = 0; i < row; i++) {
                tongKet.add(Double.parseDouble(dtmDanhSachMonHocNienCheDangKy.getValueAt(i, 4) + ""));
                hocTrinh.add(Integer.parseInt(dtmDanhSachMonHocNienCheDangKy.getValueAt(i, 1) + ""));
                tongSoDonViHocTrinhCacMon += hocTrinh.get(i);
            }

            double diemTrungBinhChungToanNam = SinhVienNCService.tinhTrungBinhChungCacMonNC(tongKet, hocTrinh);
            Vector<Object> vec = new Vector<>();
            vec.add(diemTrungBinhChungToanNam);
            vec.add(tongSoDonViHocTrinhCacMon);
            if (diemTrungBinhChungToanNam < 4) {
                vec.add("KÉM");
            } else if (diemTrungBinhChungToanNam >= 4 && diemTrungBinhChungToanNam < 5) {
                vec.add("YẾU");
            } else if (diemTrungBinhChungToanNam >= 5 && diemTrungBinhChungToanNam < 6) {
                vec.add("TRUNG BÌNH");
            } else if (diemTrungBinhChungToanNam >= 6 && diemTrungBinhChungToanNam < 7) {
                vec.add("TRUNG BÌNH KHÁ");
            } else if (diemTrungBinhChungToanNam >= 7 && diemTrungBinhChungToanNam < 8) {
                vec.add("KHÁ");
            } else if (diemTrungBinhChungToanNam >= 8 && diemTrungBinhChungToanNam < 9) {
                vec.add("GIỎI");
            } else {
                vec.add("XUẤT SẮC");
            }
            dtmMonTotNghiep.setRowCount(0);
            dtmMonTotNghiep.addRow(vec);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "chưa có dữ liệu chưa biết kết quả được!");
        }

    }

    private void hienThiMonHocNienChe() {
        txtTenMonHocNienChe.setText(MonHocNienCheSelected.getTenMonHoc());
        txtSoDonViHocTrinh.setText(MonHocNienCheSelected.getSoDonViHocTrinh() + "");
        txtDiemGiuaKy.setText("");
        txtDiemCuoiKy.setText("");
    }

    public void ShowWindow() {
        this.setSize(900, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
    }
}
