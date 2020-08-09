/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Thắng Bùi
 */
public class TCPClient {

    public static void main(String[] args) {
        try {
            
            // Thay message = job
            //2: Client tạo socket
            Socket socket = new Socket("localhost", 3000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scan = new Scanner(System.in);
            String message = "";
            int c = 0;
            while (c != 4) {
                System.out.println("--------------MENU----------------");
                System.out.println("1. Xem danh sách nhân viên.");
                System.out.println("2. Thêm khach hang.");
                System.out.println("3. Tinh tien.");
                System.out.println("4. Thoát.");
                System.out.println("Bạn chọn: ");
                c = Integer.parseInt(scan.nextLine());
                dos.writeInt(c);
                switch (c) {
                    case 1:
                        message = "";
                        message = dis.readUTF();
                        System.out.println(message);
                        break;
                    case 2:
                        message = "";
                        System.out.print("ma kh: ");
                        String maKH = scan.nextLine();
                        System.out.print("ten: ");
                        String name = scan.nextLine();
                        System.out.print("loai phong: ");
                        String loaiPhong = scan.nextLine();
                        
                        message += maKH+"$"+name+"$"+loaiPhong;
                        dos.writeUTF(message);                            
                        break;
                    case 3:
                        message ="";
                        System.out.println("Nhập tên khach hang: ");
                        String tenKH = scan.nextLine();
                        
                        System.out.println("Nhap so ngay o: ");
                        String ngay = scan.nextLine();
                        message += tenKH + "$" + ngay;
                        dos.writeUTF(message);
                        
                        String result = dis.readUTF();
                        System.out.println(result);
                        break;
                    case 4:
                        dos.writeUTF("done");
                        break;
                    default:
                        System.out.println("Bạn nhập chưa đúng.");
                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
