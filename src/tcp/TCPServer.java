/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Thắng Bùi
 */
public class TCPServer {

    public ArrayList<KhachHang> getList() {
        ArrayList<KhachHang> list = new ArrayList<>();

        try {
            FileReader fr = new FileReader("src\\tcp\\Khachsan.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";

            while ((s = br.readLine()) != null) {
                String[] data = s.split("\\$");
                KhachHang e = new KhachHang(data[0], data[1], data[2]);
                list.add(e);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        try {
            //1
            ServerSocket myServer = new ServerSocket(3000);
            System.out.println("Server is ready!");

            while (true) {
                Socket socket = myServer.accept();
                System.out.println("Client connected!");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                Scanner scan = new Scanner(System.in);
                String message = "";
                int c = 0;

                while (c != 4) {
                    c = dis.readInt();
                    switch (c) {
                        case 1:
                            String result = "";
                            for (KhachHang emp : server.getList()) {
                                result += emp.showInfo();
                            }
                            dos.writeUTF(result);
                            break;
                        case 2:
                            message = dis.readUTF();

                            File file = new File("src\\tcp\\Khachsan.txt");
                            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write("\n" + message);

                            bw.close();
                            fw.close();
                            break;
                        case 3:
                            String result3 = "";
                            message = dis.readUTF();
                            String[] data = message.split("\\$");
                            System.out.println(data[1]);
                            
                            float tinhTien = 0;
                            for (int i = 0; i < server.getList().size(); i++) {
                                if (data[0].equalsIgnoreCase(server.getList().get(i).getName())) {
                                    if (server.getList().get(i).getLoaiPhong().equalsIgnoreCase("S")) {
                                        result3 += String.valueOf(Integer.parseInt(data[1]) * 500);
                                    } else if (server.getList().get(i).getLoaiPhong().equalsIgnoreCase("A")) {
                                        result3 += String.valueOf(Integer.parseInt(data[1]) * 400);
                                    }  else  {
                                        result3 += String.valueOf(Integer.parseInt(data[1]) * 300);
                                    }
                                }
                            }
                            dos.writeUTF(result3);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Bạn nhập sai hãy nhập lại");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
