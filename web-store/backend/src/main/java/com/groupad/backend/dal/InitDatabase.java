package com.groupad.backend.dal;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.groupad.backend.controller.UserController;
import com.groupad.backend.enums.EventType;
import com.groupad.backend.exception.UserExceptions.signup.AccountCreationException;
import com.groupad.backend.model.Address;
import com.groupad.backend.model.Cart;
import com.groupad.backend.model.CartPK;
import com.groupad.backend.model.Order;
import com.groupad.backend.model.OrderedProduct;
import com.groupad.backend.model.OrderedProductPK;
import com.groupad.backend.model.Product;
import com.groupad.backend.model.Review;
import com.groupad.backend.model.User;
import com.groupad.backend.model.VisitEvent;
import com.groupad.backend.repository.AddressRepository;
import com.groupad.backend.repository.CartRepository;
import com.groupad.backend.repository.OrderRepository;
import com.groupad.backend.repository.OrderedProductRepository;
import com.groupad.backend.repository.ProductRepository;
import com.groupad.backend.repository.ReviewRepository;
import com.groupad.backend.repository.VisitEventRepository;
import com.groupad.backend.repository.UserRepository;

@Configuration
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(ProductRepository pRepo, UserRepository uRepo, AddressRepository aRepo,
			CartRepository cRepo, OrderRepository oRepo, OrderedProductRepository opRepo,
			VisitEventRepository vRepo, ReviewRepository rRepo) {
		return args -> {
			// save products
			List<Product> pList = Arrays.asList(genProducts());
			pRepo.saveAll(pList);

			// save users
			List<User> uList = Arrays.asList(genUsers());
			// using user controller for password encryption

			UserController uc = new UserController(uRepo);

			uList.forEach(u -> {
				try {
					uc.putNewUser(u);
				} catch (AccountCreationException e) {
					e.printStackTrace();
				}
			});

			// address from u1
			List<Address> aList = Arrays.asList(genAddresses(uList));
			aRepo.saveAll(aList);

			// PK for shopping cart for u1
			List<CartPK> cartpkList = Arrays.asList(genCartPKs(uList, pList));

			// Remaining shopping cart in u1 account
			List<Cart> cartList = Arrays.asList(genCarts(cartpkList));
			cRepo.saveAll(cartList);

			// order from u1
			List<Order> oList = Arrays.asList(genOrders(uList, aList));
			oRepo.saveAll(oList);

			// ordered products from u1
			List<OrderedProductPK> oppkList = Arrays.asList(genOrderProductPKs(oList, pList));
			List<OrderedProduct> opList = Arrays.asList(genOrderProducts(oppkList));
			opRepo.saveAll(opList);

			// User who has bought the product can leave reviews
			List<Review> rList = Arrays.asList(genReviews(uList, pList));
			rRepo.saveAll(rList);

			// visit events (views, transactions)
			List<VisitEvent> vEventList = Arrays.asList(genVisitEvents(pList));
			vRepo.saveAll(vEventList);

			// Other entities should be tested more
		};
	}

	private Product[] genProducts() {
		Product[] list = {
				new Product("Smarties", "The classic, colourful, and crunchy candy-coated sweets", "chocolate",
						"Nestle", 15, 1.99,
						"https://i5.walmartimages.com/asr/24a3fe79-ea2a-4c1c-87af-dc824371f89f.f4356ae1a1bdc781e14eb1f0a8171375.jpeg",
						"Smarties"),
				new Product("Caramilk",
						"A golden white chocolate with a smooth, silky texture and delicious caramel taste.",
						"candy bar", "Cadbury", 20, 1.50,
						"https://i5.walmartimages.com/asr/bfc8c18c-49bd-4599-a336-af68f49a6aa5.64114f1a96e53a3f6e68c00d41d78350.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Caramilk"),
				new Product("Crunchie", "A honeycomb-style candy bar", "candy bar", "Cadbury", 10, 2.99,
						"https://i5.walmartimages.com/asr/6264ec29-9ff5-4628-bf48-d5bb008249ae.6383ee3ab4ccc1e7756cbd1b708d064a.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Crunchie"),
				new Product("Kinder Surprise", "KINDER chocolate, with a surprise and toy in every single egg",
						"chocolate", "Kinder Chocolate", 7, 2.99,
						"https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dwf48c1844/products/L2601110/large/L2601110.JPG",
						"Kinder Surprise"),
				new Product("Aero", "Smooth, creamy milk chocolate with the incredible lightness of bubbles",
						"candy bar", "Nestle", 50, 2.99,
						"https://i5.walmartimages.com/asr/14c46e32-a5c0-43ce-bd36-3561fe393494.202549f964e4cb550d05dec58365a47f.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Aero"),
				new Product("Coffee Crisp", "Coffee-flavoured soft candy", "candy bar", "Nestle", 45, 1.99,
						"https://i5.walmartimages.com/asr/3feb064c-ec0b-44f8-98e4-a92e2c678679.ed0d108a47fccdab3db77c1d4e457bff.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Coffee Crisp"),
				new Product("Big Turk", "Delicious chewiness of Turkish delight", "candy bar", "Nestle", 24, 3.50,
						"https://i5.walmartimages.com/asr/46d50a8f-ab1b-41f8-9330-82bb74fb8123.893c5ade7096021c4e3dc1d3aa5baffa.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Big Turk"),
				new Product("KitKat", "Chocolate-covered wafer bar", "candy bar", "Nestle", 14, 0.99,
						"https://i5.walmartimages.com/asr/7e9ac5d0-27af-4b6a-b430-1215fe2516b8.e22c9a492b9766d210e113d5ad0408b0.jpeg",
						"KitKat"),
				new Product("Mr.Big", "A layered vanilla wafer coated in caramel", "candy bar", "Cadbury", 34, 3.99,
						"https://i5.walmartimages.com/asr/e37df9fc-f4a4-4194-8b11-9203d0fd1423.0189b0942531ac79ef9339f6b0dd090e.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Mr.Big"),
				new Product("Wunderbar", "Canadain Chocolate bar, chocolate coating, filled with golden", "drink",
						"Cadbury", 45, 1.99,
						"https://i5.walmartimages.com/asr/70c998da-3e50-47f0-8b2e-3f58bcd90857.859b92167bec41fe81de6b9f4ed16ebd.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Wunderbar"),
				new Product("Mars Bar", "Chcolated with caramel and nougat", "candy bar", "Mars", 11, 1.50,
						"https://i5.walmartimages.com/asr/d9105b3c-42f9-45c1-b549-0f0973efe162.d32695e6bdf04b2b02ae4f9a8bb3ceb6.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Mars Bar"),
				new Product("Crispy Crunch Chocolate Milkshake", "Neilson Chocolate Crispy Crunch Milkshake",
						"beverage",
						"Cadbury", 7, 5.99,
						"https://i5.walmartimages.com/asr/f105fe6d-7917-4ceb-acc1-b98012dce90a.107f8bfbb3e3ab50b47da66f7a27a438.jpeg?odnHeight=180&odnWidth=180&odnBg=ffffff",
						"Crispy Crunch"),
				new Product("Hubba Bubba", "Outrageous Original Flavoured Bubble Gum", "gum", "Wrigley", 80, 6.99,
						"https://i5.walmartimages.com/asr/dae76491-fcfa-49f4-ba23-103ff501a00f.e4a7f779b015c4fa4e4aa5f8cf10f234.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Hubba Bubba"),
				new Product("Juicy Fruit", "Flavor of combination of banana and pineapple", "gum", "Wrigley", 53, 1.99,
						"https://i5.walmartimages.com/asr/2272787f-a86f-4c1a-a2be-66b28e5dcecd.65b1033b50e1296a503cd610a3a5c539.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Juicy Fruit"),
				new Product("Excel", "Chewing gum and mints", "gum", "Wrigley", 25, 3.50,
						"https://i5.walmartimages.com/asr/fe8e4168-988c-4205-b935-1a836c15238a.50e16299471f7f6305c11703ab68fb86.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Excel"),
				new Product("Dentyne", "Gum to aid in oral hygiene", "gum", "Cadbury", 4, 1.50,
						"https://i5.walmartimages.com/asr/abbfa4b2-ce43-4844-8a74-c8351b473d5f.43c5a049e37f48c9e45328177f2d74ca.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Dentyne"),
				new Product("Haribo Goldbears", "Flavors ranging from pineapple to lemon", "candy", "Haribo", 8, 2.50,
						"https://i5.walmartimages.com/asr/993fa2b9-d0d6-4b5a-ab96-50742016a50f.7348373a3037036f920f95af1edf3013.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Haribo Goldbears"),
				new Product("Skittles", "Multicolored fruit-flavored button-shaped candies", "jelly bean", "Wrigley",
						18, 3.99,
						"https://i5.walmartimages.com/asr/4dce7ce5-001a-450f-8f0f-4583eaef1ba3.3be09f4137285cd27f7ac6d4eaa5cbb5.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Skittles"),
				new Product("Starburst", "A box-shaped, fruit-flavoured soft taffy candy", "jelly bean", "Wrigley", 27,
						3.99,
						"https://i5.walmartimages.com/asr/961fc7fa-7d5d-4850-a9e4-9348ef98ab3a.6d4e48fd2352f5ea3e76ed3dd5cc878e.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Starburst"),
				new Product("Riesen", "A confectionery of chocolate and chocolate-flavored caramel", "candy",
						"August Storck KG", 42, 1.99,
						"https://i5.walmartimages.com/asr/3610fdfc-19dd-40c8-abd1-dc1c9cb89e97.4ee98e3122326f6f99913433f0c76170.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Riesen"),
				new Product("Jolly Rancher", "Sweet hard candy", "candy", "Hershey", 66, 1.99,
						"https://i5.walmartimages.com/asr/802e1a84-fd48-41b5-9544-ec13f26e09e7.5683c78c6cb4710550d74655ec8433e9.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"Jolly Rancher"),
				new Product("TWIZZLERS", "A chewy licorice type candy with a fruity flavor", "candy", "Hershey", 11,
						4.99,
						"https://i5.walmartimages.com/asr/0760f7f0-8fa4-45ab-80ef-fa6cc4b232cf.e9656302cdb242167fdc2a27cc769553.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
						"TWIZZLERS")
		};
		return list;
	}

	private User[] genUsers() {
		User[] list = {
				new User("abc@google.com", "StrongPassword1!", "User", "Test", 0, 0),
				new User("abcde@google.com", "StrongPassword1!", "Test", "User", 0, 0),
				new User("admin@google.com", "StrongPassword1!", "Admin", "Admin", 1, 0),
		};
		return list;
	}

	private Address[] genAddresses(List<User> uList) {
		Address[] list = {
				new Address("Joe", "Shmoe", "11 York Ave", null, "York", "ON", "Canada", "a1a1v1", "123-233-1122",
						uList.get(0).getEmail())
		};
		return list;
	}

	private CartPK[] genCartPKs(List<User> uList, List<Product> pList) {
		CartPK[] list = {
				new CartPK(uList.get(0).getEmail(), pList.get(0).getProductId()),
				new CartPK(uList.get(2).getEmail(), pList.get(0).getProductId()),
				new CartPK(uList.get(2).getEmail(), pList.get(1).getProductId()),
				new CartPK(uList.get(2).getEmail(), pList.get(2).getProductId()),

		};
		return list;
	}

	private Cart[] genCarts(List<CartPK> cpList) {
		Cart[] list = {
				new Cart(cpList.get(0).getEmail(), cpList.get(0).getProductId(), 2),
				new Cart(cpList.get(1).getEmail(), cpList.get(1).getProductId(), 2),
				new Cart(cpList.get(2).getEmail(), cpList.get(2).getProductId(), 1),
				new Cart(cpList.get(3).getEmail(), cpList.get(3).getProductId(), 3),
		};
		return list;
	}

	private Order[] genOrders(List<User> uList, List<Address> aList) {
		Order[] list = {
				new Order("2022-11-28 11:03:45", uList.get(0).getEmail(), aList.get(0).getAddressId()),
		};
		return list;
	}

	private OrderedProductPK[] genOrderProductPKs(List<Order> oList, List<Product> pList) {
		OrderedProductPK[] list = {
				new OrderedProductPK(oList.get(0).getOrderId(), pList.get(0).getProductId()),
				new OrderedProductPK(oList.get(0).getOrderId(), pList.get(1).getProductId()),
		};
		return list;
	}

	private OrderedProduct[] genOrderProducts(List<OrderedProductPK> oppkList) {
		OrderedProduct[] list = {
				new OrderedProduct(oppkList.get(0).getOrderId(), oppkList.get(0).getProductId(), 3),
				new OrderedProduct(oppkList.get(1).getOrderId(), oppkList.get(1).getProductId(), 1),
		};
		return list;
	}

	private Review[] genReviews(List<User> uList, List<Product> pList) {
		Review[] list = {
				new Review(uList.get(0).getEmail(), pList.get(0).getProductId(), 4, "It's tasty"),
		};
		return list;
	}

	private VisitEvent[] genVisitEvents(List<Product> pList) {
		String ip = "111.22.33.44";
		LocalDate date = LocalDate.of(2022, Month.DECEMBER, 12);
		VisitEvent[] list = {
				new VisitEvent(ip, date, pList.get(0).getProductId(),
						EventType.VIEW.toString()),
				new VisitEvent(ip, date, pList.get(0).getProductId(),
						EventType.CART.toString()),
				new VisitEvent(ip, date, pList.get(0).getProductId(),
						EventType.PURCHASE.toString()), };
		return list;
	}
}
