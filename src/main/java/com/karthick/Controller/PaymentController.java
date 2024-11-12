package com.karthick.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Model.Order;
import com.karthick.Model.PaymentOrder;
import com.karthick.Model.Seller;
import com.karthick.Model.SellerReport;
import com.karthick.Model.User;
import com.karthick.Response.ApiResponse;
import com.karthick.Service.OrderService;
import com.karthick.Service.PaymentService;
import com.karthick.Service.SellerReportService;
import com.karthick.Service.SellerService;
import com.karthick.Service.TransactionService;
import com.karthick.Service.impl.USerServiceImpleMents;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private USerServiceImpleMents uSerServiceImpleMents;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private SellerReportService reportService;
	@Autowired
	private OrderService orderService;

	@Autowired
	private TransactionService transactionService;

	@PostMapping("{paymentLinkId}/payment/{paymentId}")
	public ResponseEntity<ApiResponse> PaymentSucessHandler(@RequestHeader("Authorization") String jwt,
			@PathVariable String paymentId, @RequestParam String paymentLinkId) throws Exception {
		User user = uSerServiceImpleMents.findUserByJwtToken(jwt);

		PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);
		Boolean paymentSucess = paymentService.PRoceedToPaymentOrder(paymentOrder, paymentId, paymentLinkId);

		if (paymentSucess) {
			for (Order order : paymentOrder.getOrders()) {
				transactionService.CreateTranscation(order);
				Seller seller = sellerService.GetSellerById(order.getId());
				SellerReport sellReport = reportService.GetSellerReport(seller);
				sellReport.setTotalEarnings(sellReport.getTotalEarnings() + order.getTotalSellingPrice());
				sellReport.setTotalOrders(sellReport.getTotalOrders() + 1);
				sellReport.setTotalSales(sellReport.getTotalSales() + order.getOrderItems().size());
				reportService.UpdateSellerReport(sellReport);
				;
			}
			ApiResponse response = new ApiResponse();
			response.setMessage("payment is successful");
			return new ResponseEntity<>(response, HttpStatus.OK);

		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
}
