package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshoppe.ecommerce.entity.Book;
import com.webshoppe.ecommerce.entity.Flower;
import com.webshoppe.ecommerce.entity.Toy;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;
import com.webshoppe.ecommerce.repository.BookRepository;
import com.webshoppe.ecommerce.repository.FlowerRepository;
import com.webshoppe.ecommerce.repository.ToyRepository;
import com.webshoppe.ecommerce.service.BookCatalogService;
import com.webshoppe.ecommerce.service.FlowerCatalogService;
import com.webshoppe.ecommerce.service.ToyCatalogService;

public class GeneralCatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ToyCatalogService toyCatalogService;
    private FlowerCatalogService flowerCatalogService;
    private BookCatalogService bookCatalogService;

    @Override
    public void init() throws ServletException {
        final JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager();
        
        final ToyRepository toyRepository = new ToyRepository(jdbcConnectionManager);
        final FlowerRepository flowerRepository = new FlowerRepository(jdbcConnectionManager);
        final BookRepository bookRepository = new BookRepository(jdbcConnectionManager);
        
        toyCatalogService = new ToyCatalogService(toyRepository);
        flowerCatalogService = new FlowerCatalogService(flowerRepository);
        bookCatalogService = new BookCatalogService(bookRepository);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        final List<Toy> toys = toyCatalogService.getToyCatalog();
        request.setAttribute("catalog", toys);
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request,  response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        final String categoryParam = request.getParameter("select-category");
        
        final String minimumPriceParam = request.getParameter("minimum-price");
        final BigDecimal minimumPrice = new BigDecimal(minimumPriceParam);
        
        final String maximumPriceParam = request.getParameter("maximum-price");
        final BigDecimal maximumPrice = new BigDecimal(maximumPriceParam);
        
        if(categoryParam.equals("toy")) {
            showToys(request, response, minimumPrice, maximumPrice);
        } else if(categoryParam.equals("book")) {
            showBooks(request, response, minimumPrice, maximumPrice);
        } else if(categoryParam.equals("flower")) {
            showFlowers(request, response, minimumPrice, maximumPrice);
        }
    }

	private void showFlowers(HttpServletRequest request, HttpServletResponse response, final BigDecimal minimumPrice, final BigDecimal maximumPrice)
			throws IOException, ServletException {
		final List<Flower> flowers = flowerCatalogService.getFlowerCatalog(minimumPrice, maximumPrice);
		request.setAttribute("catalog", flowers);
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request,  response);
	}

	private void showBooks(HttpServletRequest request, HttpServletResponse response, final BigDecimal minimumPrice, final BigDecimal maximumPrice)
			throws IOException, ServletException {
		final List<Book> books = bookCatalogService.getBookCatalog(minimumPrice, maximumPrice);
		request.setAttribute("catalog", books);
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request,  response);
	}

	private void showToys(HttpServletRequest request, HttpServletResponse response, final BigDecimal minimumPrice, final BigDecimal maximumPrice)
			throws IOException, ServletException {
		final List<Toy> toys = toyCatalogService.getToyCatalog(minimumPrice, maximumPrice);
		request.setAttribute("catalog", toys);
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/catalog.jsp");
        dispatcher.forward(request,  response);
	}

}
