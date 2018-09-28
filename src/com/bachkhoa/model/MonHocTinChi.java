/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class MonHocTinChi implements Serializable {

    private String TenMonHoc;
    private int SoTinChi;
    private MonHocTinChi MonHocTinChiDieuKien;
    private double diemGiuaKy;
    private double diemCuoiKy;
    


    public MonHocTinChi() {
    }

    public MonHocTinChi(String TenMonHoc, int SoTinChi, MonHocTinChi MonHocTinChiDieuKien, double diemGiuaKy, double diemCuoiky) {
        this.TenMonHoc = TenMonHoc;
        this.SoTinChi = SoTinChi;
        this.MonHocTinChiDieuKien = MonHocTinChiDieuKien;
        this.diemGiuaKy = diemGiuaKy;
        this.diemCuoiKy = diemCuoiky;
    }

    public MonHocTinChi getMonHocTinChiDieuKien() {
        return MonHocTinChiDieuKien;
    }

    public void setMonHocTinChiDieuKien(MonHocTinChi MonHocTinChiDieuKien) {
        this.MonHocTinChiDieuKien = MonHocTinChiDieuKien;
    }
   
    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String TenMonHoc) {
        this.TenMonHoc = TenMonHoc;
    }

    public int getSoTinChi() {
        return SoTinChi;
    }

    public void setSoTinChi(int SoTinChi) {
        this.SoTinChi = SoTinChi;
    }

    public double getDiemGiuaKy() {
        return diemGiuaKy;
    }

    public void setDiemGiuaKy(double diemGiuaKy) {
        this.diemGiuaKy = diemGiuaKy;
    }

    public double getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(double diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    @Override
    public String toString() {
        return this.TenMonHoc;
    }

}
