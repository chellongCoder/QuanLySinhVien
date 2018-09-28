/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachkhoa.control;

import com.bachkhoa.model.MonHocNienChe;
import com.bachkhoa.model.MonHocTinChi;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;
import java.security.spec.*;
//import org.bouncycastle.jce.*;
/**
 *
 * @author Admin
 */
public class SerilizeFactory {

    public static boolean luuFile(Vector<MonHocTinChi> vec, String path) {
        
        try {
            @SuppressWarnings("unchecked")
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(vec);
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Vector<MonHocTinChi> docFile(String path) {
        @SuppressWarnings({ "deprecation", "unused", "unchecked" })
        Vector<MonHocTinChi> vec = new Vector<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object data = ois.readObject();
            vec = (Vector<MonHocTinChi>) data;
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        return vec;
    }

    public static boolean luuFileMonNienChe(Vector<MonHocNienChe> vec, String path) {
        try {
            @SuppressWarnings({ "deprecation", "unused", "unchecked" })
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(vec);
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Vector<MonHocNienChe> docFileNienChe(String path) {
         @SuppressWarnings({ "deprecation", "unused", "unchecked" })
        Vector<MonHocNienChe> vec = new Vector<>();
        try {
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object data = ois.readObject();
            vec = (Vector<MonHocNienChe>) data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vec;
    }

//    public static void main(String[] args) {
//        Vector<MonHocNienChe> vec = docFileNienChe("CacMonHocNienChe.db");
//        for (MonHocNienChe mh : vec) {
//            System.out.println(mh);
//        }
//    }
}
