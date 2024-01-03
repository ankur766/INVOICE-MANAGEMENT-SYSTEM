package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.crud.Crud;
import com.dao.InvoiceDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.response.HRCApiResponse;

import kiitankur.servlet.http.HttpServlet;
import kiitankur.servlet.http.HttpServletRequest;
import kiitankur.servlet.http.HttpServletResponse;

public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Add() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Crud crud = Crud.getInstance();
		InvoiceDao invoiceInfo;
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			invoiceInfo = gson.fromJson(request.getReader(), InvoiceDao.class);

			System.out.println("Received: " + invoiceInfo.toString());

			HRCApiResponse apiResponse = crud.addData(invoiceInfo);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String sendResponse = new Gson().toJson(apiResponse);

			PrintWriter out = response.getWriter();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			out.print(sendResponse);
			out.flush();

			response.setStatus(apiResponse.getStatusCode());
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
