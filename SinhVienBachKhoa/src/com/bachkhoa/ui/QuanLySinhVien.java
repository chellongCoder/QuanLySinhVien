/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Long Nguyen Nhat
 */
public class QuanLySinhVien extends JFrame{
    JButton btnSinhVienTinChi, btnSinhVienNienChe;
    public QuanLySinhVien (String title) {
        super(title);
        addControls();
        addEvents();
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        btnSinhVienTinChi = new JButton("sinh viên tín chỉ");
        btnSinhVienNienChe = new JButton("sinh viên niên chế");
        con.add(btnSinhVienTinChi);
        con.add(btnSinhVienNienChe);
        
    }

    private void addEvents() {
        btnSinhVienTinChi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuanLySinhVienTinChi ui = new QuanLySinhVienTinChi("quản lý môn học tín chỉ");
                ui.ShowWindow();
            }
        });
        btnSinhVienNienChe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuanLySinhVienNienChe ui= new QuanLySinhVienNienChe("quản lý môn học niên chế");
                ui.ShowWindow();
            }
        });
    }
    
    public void showWindow() {
        this.setSize(300,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
