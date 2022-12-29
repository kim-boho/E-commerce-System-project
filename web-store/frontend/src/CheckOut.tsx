import { useEffect, useState } from "react";
import { AiFillDelete } from "react-icons/ai";
import { CartProduct, useProductsByListOfIds } from "./hooks/useProducts";
import Header from "./components/Header";
import ShippingInfoForm from "./components/forms/ShippingInfoForm";
import PaymentInfoForm from "./components/forms/PaymentInfoForm";
import DeliveryMethodSelection from "./components/forms/DeliveryMethodSelection";
import { postCheckout } from "./hooks/checkoutRequest";
import { Cart, CheckoutRequest, PaymentInfo, ShippingInfo } from "./api";
import { useNavigate } from "react-router-dom";

const validateShippingInfo = (shippingInfo: ShippingInfo) => {};
const validatePaymentInfo = (paymentInfo: PaymentInfo) => {};

const CheckOut = (props: { email: string }) => {
  const [productList, setProductList] = useState<CartProduct[] | null>();
  const navigate = useNavigate();
  const { data: products } = useProductsByListOfIds(props.email);

  useEffect(() => {
    setProductList(products);
    setTotalCartPrice(calculateSubtotal);
    setQuantitySelectors();
  }, [products, productList]);

  useEffect(() => {
    const getUser = () => {
      return localStorage.getItem("user");
    };

    if (getUser == null) {
      navigate("/login");
    }
  }, []);

  // calculates subtotal
  const calculateSubtotal = () => {
    return productList
      ? Math.round(
          productList
            .map((p) => p.price * p.quantity)
            .reduce((acc, p) => p + acc) * 100
        ) / 100
      : 0;
  };

  // used to set the right quantity selection on load
  const setQuantitySelectors = () => {
    productList?.forEach((p) => {
      const element = document.getElementById(
        `quantity-${p.productId}`
      ) as HTMLSelectElement;
      if (element) element.value = p.quantity.toString();
    });
  };
  let [totalCartPrice, setTotalCartPrice] = useState(calculateSubtotal);

  const handleChangeQuantity = (e: any, productId: number) => {
    const updatedCart = productList?.map((c) => {
      if (c.productId == productId) {
        c.quantity = e.target.value;
      }
      return c;
    });
    setTotalCartPrice(calculateSubtotal);
    setProductList(updatedCart);
  };

  const handleDelete = (productId: number) => {
    setProductList(productList?.filter((p) => p.productId != productId));
  };

  const handleSubmit = async (event: any) => {
    // prevent page reload
    event.preventDefault();
    // if user is logged in
    // get shipping info from form
    const shippingInfo = {
      email: props.email,
      firstName: event.target.firstname.value,
      lastName: event.target.lastname.value,
      street: event.target.address.value,
      apartment: event.target.apartment.value,
      city: event.target.city.value,
      country: event.target.country.value,
      region: event.target.region.value,
      postalCode: event.target.postalcode.value,
      phone: event.target.phone.value,
    } as ShippingInfo;

    // get payment info from form
    const paymentInfo = {
      cardNumber: event.target.cardnumber.value,
      expiry: event.target.expiry.value.replace("/", ""),
      cvc: event.target.cvc.value,
      nameOnCard: event.target.nameoncard.value,
    } as PaymentInfo;

    // validate form entries
    validatePaymentInfo(paymentInfo);
    validateShippingInfo(shippingInfo);

    // rebuild cart
    const cart = productList?.map((p) => {
      return {
        email: props.email,
        productId: p.productId,
        quantity: p.quantity,
      } as Cart;
    }) as Cart[];

    // build checkout request
    const checkoutRequest = {
      cart: cart,
      shippingInfo: shippingInfo,
      paymentMethod: paymentInfo,
    } as CheckoutRequest;
    console.log(checkoutRequest);
    // submit checkout
    const result = await postCheckout(checkoutRequest);
    if (validateResponse(result)) {
      // success!
    } else {
      // display errors
    }
  };

  const validateResponse = (result: any) => {
    return true;
  };
  return (
    <>
      <Header />

      <div className="bg-slate-200">
        <div className="max-w-2xl mx-auto pt-16 pb-24 px-4 sm:px-6 lg:max-w-7xl lg:px-8">
          <h2 className="sr-only">Checkout</h2>
          <form onSubmit={handleSubmit}>
            <div className="lg:grid lg:grid-cols-2 lg:gap-x-12 xl:gap-x-16">
              <div>
                <ShippingInfoForm />
                <PaymentInfoForm />
                <DeliveryMethodSelection />
              </div>

              {/* Order summary */}

              <div className="mt-10 lg:mt-0">
                <h2 className="text-lg font-large text-gray-900 ml-0">
                  Order summary
                </h2>

                <div className="mt-4 bg-white border border-gray-200 rounded-lg shadow-sm">
                  <h3 className="sr-only">Items in your cart</h3>
                  <ul role="list" className="divide-y divide-gray-200">
                    {productList != null && Array.isArray(productList) ? (
                      productList?.map((product) => (
                        <li
                          key={product.productId}
                          className="flex py-6 px-4 sm:px-6"
                        >
                          <div className="flex-shrink-0">
                            <img
                              src={product.imgSrc}
                              alt={product.imgAlt}
                              className="w-20 rounded-md"
                            />
                          </div>

                          <div className="ml-6 flex-1 flex flex-col">
                            <div className="flex">
                              <div className="min-w-0 flex-1">
                                <h4 className="text-sm">
                                  <a
                                    href={`/product/${product.productId}`}
                                    className="font-medium text-gray-700 hover:text-gray-800"
                                  >
                                    {product.name}
                                  </a>
                                </h4>
                                <p className="mt-1 text-sm text-gray-500">
                                  {product.color}
                                </p>
                                <p className="mt-1 text-sm text-gray-500">
                                  {product.size}
                                </p>
                              </div>

                              <div className="ml-4 flex-shrink-0 flow-root">
                                <button
                                  type="button"
                                  className="-m-2.5 bg-white p-2.5 flex items-center justify-center text-gray-400 hover:text-gray-500"
                                  onClick={() =>
                                    handleDelete(product.productId)
                                  }
                                >
                                  <span className="sr-only">Remove</span>
                                  <AiFillDelete
                                    className="h-5 w-5"
                                    aria-hidden="true"
                                  />
                                </button>
                              </div>
                            </div>

                            <div className="flex-1 pt-2 flex items-end justify-between">
                              <p className="mt-1 text-sm font-medium text-gray-900">
                                ${product.price * product.quantity}
                              </p>

                              <div className="ml-4">
                                <label htmlFor="quantity" className="sr-only">
                                  Quantity
                                </label>
                                <select
                                  onChange={(e) =>
                                    handleChangeQuantity(e, product.productId)
                                  }
                                  id={`quantity-${product.productId}`}
                                  name="quantity"
                                  className="rounded-md border border-gray-300 text-base font-medium text-gray-700 text-left shadow-sm focus:outline-none focus:ring-1 focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                                >
                                  <option value={1}>1</option>
                                  <option value={2}>2</option>
                                  <option value={3}>3</option>
                                  <option value={4}>4</option>
                                  <option value={5}>5</option>
                                  <option value={6}>6</option>
                                  <option value={7}>7</option>
                                  <option value={8}>8</option>
                                </select>
                              </div>
                            </div>
                          </div>
                        </li>
                      ))
                    ) : (
                      <div>Loading...</div>
                    )}
                  </ul>
                  <dl className="border-t border-gray-200 py-6 px-4 space-y-6 sm:px-6">
                    <div className="flex items-center justify-between">
                      <dt className="text-sm">Subtotal</dt>
                      <dd className="text-sm font-medium text-gray-900">
                        ${totalCartPrice}
                      </dd>
                    </div>
                    <div className="flex items-center justify-between">
                      <dt className="text-sm">Shipping</dt>
                      <dd className="text-sm font-medium text-gray-900">
                        $5.00
                      </dd>
                    </div>
                    <div className="flex items-center justify-between">
                      <dt className="text-sm">Taxes</dt>
                      <dd className="text-sm font-medium text-gray-900">
                        ${Math.round(totalCartPrice * 13) / 100}
                      </dd>
                    </div>
                    <div className="flex items-center justify-between border-t border-gray-200 pt-6">
                      <dt className="text-base font-medium">Total</dt>
                      <dd className="text-base font-medium text-gray-900">
                        $
                        {(
                          totalCartPrice +
                          (totalCartPrice * 13) / 100 +
                          5
                        ).toFixed(2)}
                      </dd>
                    </div>
                  </dl>

                  <div className="border-t border-gray-200 py-6 px-4 sm:px-6">
                    <button
                      type="submit"
                      className="w-full bg-indigo-600 border border-transparent rounded-md shadow-sm py-3 px-4 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-50 focus:ring-indigo-500"
                    >
                      Confirm order
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};
export default CheckOut;
