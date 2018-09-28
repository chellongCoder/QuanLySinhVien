/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.control;

import com.bachkhoa.model.MonHocTinChi;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class SinhVienTCService {
    public Vector<MonHocTinChi> danhSachMonHocTinChi(){
        return SerilizeFactory.docFile("database\\CacMonHocTinChi.db");
    }
     
//    công thức (Điểm giữa kì*3 + điểm cuối kì *0.7)/10
//    điểm giữa kì tính theo thang 10, điểm cuối kì tính theo thang 10, tổng kết trên 4 là qua
    
    public double tinhDiemTotNghiep(double diemGiuaKy, double diemCuoiKy) { 
        return (0.3*diemGiuaKy + 0.7*diemCuoiKy);
    }
    
//     public static void main(String[] args) {
//        Vector<MonHocTinChi> vec = new Vector<>();
//        
//            MonHocTinChi toanCaoCap1 = new MonHocTinChi("toán cao cấp 1", 3, null,0,0);
//            MonHocTinChi triet1 = new MonHocTinChi("triết 1", 2, null,0,0);
//            MonHocTinChi toanCaoCap3 = new MonHocTinChi("toán cao cấp 3", 3, toanCaoCap1,0,0);
//            MonHocTinChi toanCaoCap2 = new MonHocTinChi("toán cao cấp 2", 3, toanCaoCap3,0,0);
//            MonHocTinChi vatLyDaiCuong1 = new MonHocTinChi("vật lý đại cương 1", 3, null,0,0);
//            MonHocTinChi tinHocDaiCuong = new MonHocTinChi("tin học đại cương", 2, null,0,0);
//            MonHocTinChi vatLyDaiCuong2 = new MonHocTinChi("vật lý đại cương 2", 2, vatLyDaiCuong1,0,0);
//            MonHocTinChi lapTrinhCanBan = new MonHocTinChi("lập trình căn bản", 3, tinHocDaiCuong,0,0);
//            MonHocTinChi quanTriMang = new MonHocTinChi("Quản trị mạng", 3, lapTrinhCanBan,0,0);
//            
//            vec.add(toanCaoCap1);
//            vec.add(triet1);
//            vec.add(toanCaoCap3);
//            vec.add(toanCaoCap2);
//            vec.add(vatLyDaiCuong1);
//            vec.add(tinHocDaiCuong);
//            vec.add(vatLyDaiCuong2);
//            vec.add(lapTrinhCanBan);
//            vec.add(quanTriMang);
//            
//        boolean check = SerilizeFactory.luuFile(vec, "database\\CacMonHocTinChi.db");
//        if(check) {
//            System.out.println("lưu file thành công");
//        } else {
//            System.out.println("lưu file thất bại");
//        }
////        Vector<MonHocTinChi> vec = docFile("data.db");
////          for(MonHocTinChi mh : vec) {
////              System.out.println(mh);
////          }
//        
//    }
}
