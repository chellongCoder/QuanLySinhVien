/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.model;

import java.io.Serializable;

/**
 *
 * @author Long Nguyen Nhat
 */
public class MonHocNienChe implements Serializable{
    private String TenMonHoc;
    private double diemGiuaKy;
    private double diemCuoiKy;
    private int soDonViHocTrinh;

    public MonHocNienChe(String TenMonHoc, double diemGiuaKy, double diemCuoiKy, int soDonViHocTrinh) {
        this.TenMonHoc = TenMonHoc;
        this.diemGiuaKy = diemGiuaKy;
        this.diemCuoiKy = diemCuoiKy;
        this.soDonViHocTrinh = soDonViHocTrinh;
    }

    public MonHocNienChe() {
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String TenMonHoc) {
        this.TenMonHoc = TenMonHoc;
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

    public int getSoDonViHocTrinh() {
        return soDonViHocTrinh;
    }

    public void setSoDonViHocTrinh(int soDonViHocTrinh) {
        this.soDonViHocTrinh = soDonViHocTrinh;
    }

    @Override
    public String toString() {
        return this.TenMonHoc;
    }
    
    
}
