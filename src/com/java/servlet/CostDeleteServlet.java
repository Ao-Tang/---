package com.java.servlet;

import com.java.dao.BillDao;
import com.java.model.Bill;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "CostDeleteServlet", value = "/CostDeleteServlet")
public class CostDeleteServlet extends HttpServlet {
    Bill bill = new Bill();
    BillDao billDao = new BillDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds = request.getParameter("delIds");
        Connection con = null;
        DbUtil dbUtil = new DbUtil();
        System.out.println("序列号为"+delIds);
        try{
            JSONObject result=new JSONObject();
            con = dbUtil.getCon();
            billDao.billDelete(con,delIds);
            ResponseUtil.write(response, result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}