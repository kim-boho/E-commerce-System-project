import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import Cart from "./Cart";
import CheckOut from "./CheckOut";
import Home from "./Home";
import Login from "./Login";
import Signup from "./Signup";
import { QueryClient, QueryClientProvider } from "react-query";
import Analytics from "./Analytics";

import { useEffect, useState } from "react";
import ProductDetails from "./ProductDetails";

const queryClient = new QueryClient({
  // we can consider caching some things like products
  // instead of constantly loading from db
  /*defaultOptions: {
    queries: {
      cacheTime: 1000 * 60 * 60 * 24, // 24 hours
    },
  },*/
});

function App() {
  const getUser = () => {
    return localStorage.getItem("user");
  };

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <Router>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/cart" element={<Cart email={getUser()!} />} />
            <Route path="/checkout" element={<CheckOut email={getUser()!} />} />
            <Route path="/admin" element={<Analytics />} />
            <Route path="/product/:id" element={<ProductDetails />} />
          </Routes>
        </Router>
      </QueryClientProvider>
    </>
  );
}

export default App;
