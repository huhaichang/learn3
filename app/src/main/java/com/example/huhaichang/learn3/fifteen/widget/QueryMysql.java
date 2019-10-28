package com.example.huhaichang.learn3.fifteen.widget;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huhaichang on 2019/9/1.
 */

public class QueryMysql {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "APP1234";
    private static final String DBURL = "jdbc:mysql://120.24.241.25:3306/test?useSSL=false";//远程连接有问题 本地172.21.2.249
    public static List<String> query(){
        List<String> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName(DBDRIVER);

            //要允许权限
            // mysql -u root -p
           // use mysql;
           // update user set host = '%' where user = 'root';
           // FLUSH PRIVILEGES;
            //阿里云要开放3306端口
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);

            String sql = "select * from userid";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String zhanghao = rs.getString("user");
                Log.i("账号：",zhanghao+" ");
                userList.add(zhanghao);
            }
            conn.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userList;
    }


}
