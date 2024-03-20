package com.example.gorcerydelivery.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gorcerydelivery.entity.Cart;
import com.example.gorcerydelivery.entity.Customer;
import com.example.gorcerydelivery.entity.Order;
import com.example.gorcerydelivery.entity.Product;
import com.example.gorcerydelivery.entity.TransactionDetails;
import com.example.gorcerydelivery.exception.ResourceNotFoundException;
import com.example.gorcerydelivery.repository.CartRepository;
import com.example.gorcerydelivery.repository.CustomerRepository;
import com.example.gorcerydelivery.repository.OrderRepository;
import com.razorpay.RazorpayClient;






@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	private static final String ORDER_PLACED = "Placed";
	private static final String KEY = "rzp_test_1MsX62zRuimqkm";
    private static final String KEY_SECRET = "Ch0J8V71JnYWRlAJvseEmjPx";
    private static final String CURRENCY = "INR";

	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	public ProductService productService;

	@Autowired
	public CartService cartService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartRepository c;

	public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, CartService cartService,
			CustomerService customerService) {
		super();
		this.orderRepository = orderRepository;
		this.productService = productService;
		// this.cartService = cartService;
		this.customerService = customerService;
	}

	@Override
	public Order addOrder(Order order, long customerId, long cartId) throws ResourceNotFoundException {
		
		Cart cart = cartService.getCartById(cartId);
		Customer customer = customerService.getCustomerBycustomerId(customerId);
		order.setTotalPrice(order.getMrpPrice() * cart.getQuantity());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderedDate(order.getOrderedDate());
		order.setMrpPrice(cart.getMrpPrice());
		order.setQuantity(cart.getQuantity());
		System.out.println(">>>>>" + cart.getQuantity());
		order.setCustomer(customer);
		// order.setCartId(order.getCartId());
		// order.setTotalPrice(order.getTotalPrice());
		Order o = orderRepository.save(order);
		c.deleteById(cartId);
		return o;
	}


	@Override
	public Order getOrderById(long orderId) throws ResourceNotFoundException{
		
		return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("orderId not found"+orderId));

	} 

	@Override
	public Order updateOrder(Order order, long orderId) throws ResourceNotFoundException{
		
		Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("orderId not found"+orderId));
		existingOrder.setTotalPrice(order.getMrpPrice());
		existingOrder.setPaymentStatus(order.getPaymentStatus());
		existingOrder.setMrpPrice(order.getMrpPrice());
		existingOrder.setOrderStatus(order.getOrderStatus());
		existingOrder.setCustomer(order.getCustomer());
		existingOrder.setOrderedDate(order.getOrderedDate());
		orderRepository.save(existingOrder);
		return existingOrder;
	}

	@Override
	public List<Order> getOrderByCustomerId(long customerId) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		String currentDate = sdf.format(date);
		String[] array = currentDate.split("/");
		int month = Integer.parseInt(array[0]);
		int day = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		java.util.Date d = new java.util.Date(month, day, year);
		System.out.println(d);
		List<Order> orders = orderRepository.findByCustomerCustomerId(customerId);
		System.out.println(orders);
		return orderRepository.findByCustomerCustomerId(customerId);
	}



	@Override
	public Order addOrderProduct(Order order, long customerId) throws ResourceNotFoundException {
		Customer customer = customerService.getCustomerBycustomerId(customerId);
		order.setTotalPrice(order.getTotalPrice());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderedDate(order.getOrderedDate());
		order.setCustomer(customer);
		order.setProduct(order.getProduct());
		System.out.println("################"+ order.getProduct().get(0).getProductQuantity());
		Order o = orderRepository.save(order);
		return o;
	}

	@Override
	public void deleteOrder(long orderId) {
		//orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("OrderId not found" +orderId));
		orderRepository.deleteById(orderId);

		
	}

	@Override
	public TransactionDetails createTransaction(Double amount) {
		try {
			

			
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", (amount * 100) );
            jsonObject.put("currency", CURRENCY);

            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

            com.razorpay.Order order = razorpayClient.orders.create(jsonObject);

            TransactionDetails transactionDetails =  prepareTransactionDetails(order);
            return transactionDetails;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private TransactionDetails prepareTransactionDetails(com.razorpay.Order order) {
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");

        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);
        return transactionDetails;
    }

    @Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	
}
