/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.control;

import com.bachkhoa.model.MonHocNienChe;
import java.util.Vector;

/**
 *
 * @author Long Nguyen Nhat
 */
public class SinhVienNCService {

    public Vector<MonHocNienChe> danhSachMonHocNienChe() {
        return SerilizeFactory.docFileNienChe("database\\CacMonHocNienChe.db");
    }
//    điểm tổng kết 1 môn công thức (Điểm giữa kì*3 + điểm cuối kì *0.7)/10
//    điểm giữa kì tính theo thang 10, điểm cuối kì tính theo thang 10, tổng kết trên 4 là qua

    public static double tinhDiemTongKetMonNC(double diemGiuaKy, double diemCuoiKy) {
        return (0.3 * diemGiuaKy + 0.7 * diemCuoiKy);
    }

//    tinh điểm trung bình chung toàn năm
//    công thức A= (a1*n1 + a2*n2 + ... + an*nn) / (n1 + n2 + ... + nn)
//    với a là điểm tổng kết từng môn, n là số đơn vị học phần của từng môn
    public static double tinhTrungBinhChungCacMonNC(Vector<Double> diemTongKetMon, Vector<Integer> soDonViHocTrinh) {
        double sum = 0;
        for (int i = 0; i < diemTongKetMon.size(); i++) {
            sum += (diemTongKetMon.get(i) * soDonViHocTrinh.get(i));
        }
        int sumSoHocTrinh = 0;
        for (int i = 0; i < soDonViHocTrinh.size(); i++) {
            sumSoHocTrinh += soDonViHocTrinh.get(i);
        }
        return sum / sumSoHocTrinh;
    }
//
//    public static void main(String[] args) {
//        Vector<MonHocNienChe> vec = new Vector<>();
//
//        MonHocNienChe giaiTich1 = new MonHocNienChe("giải tích 1", 0, 0, 4);
//        MonHocNienChe giaiTich2 = new MonHocNienChe("giải tích 2", 0, 0, 3);
//        MonHocNienChe daiSo = new MonHocNienChe("đại số", 0, 0, 3);
//        MonHocNienChe xacSuatThongKe = new MonHocNienChe("xác suất thống kê", 0, 0, 3);
//        MonHocNienChe phuongPhapTinh = new MonHocNienChe("Phương pháp tính", 0, 0, 3);
//        MonHocNienChe triet = new MonHocNienChe("những NLCB của chủ nghĩa MLN", 0, 0, 5);
//        MonHocNienChe tuTuong = new MonHocNienChe("tư tưởng hồ chí minh", 0, 0, 4);
//        MonHocNienChe theChat = new MonHocNienChe("giáo dục thể chất 1", 0, 0, 2);
//
//        vec.add(giaiTich1);
//        vec.add(giaiTich2);
//        vec.add(daiSo);
//        vec.add(xacSuatThongKe);
//        vec.add(phuongPhapTinh);
//        vec.add(triet);
//        vec.add(tuTuong);
//        vec.add(theChat);
////        
//        for (int i = 0; i < vec.size(); i++) {
//            System.out.println(vec.get(i));
//        }
////        
//        boolean check = SerilizeFactory.luuFileMonNienChe(vec, "database\\CacMonHocNienChe.db");
//        if (check) {
//            System.out.println("lưu file thành công");
//        } else {
//            System.out.println("lưu file thất bại");
//        }
////         Vector<MonHocNienChe> vec = SerilizeFactory.docFileNienChe("CacMonHocNienChe.db");
////        for (MonHocNienChe mh : vec) {
////            System.out.println(mh);
////        }
////        Vector<Double> dou = new Vector<>();
////        dou.add(new Double(5.6));
////        dou.add(new Double(7.6));
////        dou.add(new Double(7.9));
////        dou.add(new Double(8));
////        
////        Vector<Integer> integer = new Vector<>();
////        integer.add(new Integer(5));
////        integer.add(new Integer(7));
////        integer.add(new Integer(4));
////        integer.add(new Integer(6));
////        
////        System.out.println("điểm trung bình chung: " + tinhTrungBinhChungCacMonNC(dou, integer));
//    }
}
