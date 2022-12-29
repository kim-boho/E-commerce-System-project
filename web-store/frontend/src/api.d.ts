//#region Product Models
export interface Product {
  productId: number;
  name: string;
  description: string;
  type: string;
  brand: string;
  instock: number;
  price: number;
  imgSrc: string;
  imgAlt: string;
  size?: string;
  color?: string;
}
//#endregion
//#region User Data
export interface User {
  email: string;
  password: string;
  fname?: string;
  lname?: string;
  checkout_count?: number;
  admin?: number;
}
export interface Cart {
  email: string;
  productId: number;
  quantity: number;
}

export interface PaymentInfo {
  cardNumber: string;
  expiry: string;
  cvc: string;
  nameOnCard: string;
}

export interface ShippingInfo {
  email: string;
  firstName: string;
  lastName: string;
  street: string;
  apartment?: string;
  country: string;
  region: string;
  city: string;
  postalCode: string;
  phone: string;
  addressId?: number;
}
export interface CheckoutRequest {
  cart: Cart[];
  paymentMethod: PaymentInfo;
  shippingInfo: ShippingInfo;
}
//#endregion

//#region Analytics Models
export interface VisitEvent {
  ip: string;
  productId: string;
  eventDate: string;
  eventType: string;
}

export interface Purchase {
  productId: string;
  productName: string;
  category: string;
  price: number;
  quantity: number;
}
//#endregion Analytics Models
