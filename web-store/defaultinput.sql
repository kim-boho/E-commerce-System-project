INSERT INTO product (name, description, type, brand, instock, price, imgsrc, imgalt)
VALUES ('Smarties','The classic, colourful, and crunchy candy-coated sweets', 'chocolate', 'Nestle', 15, 1.99, 'imgpath','Smarties'),
('Caramilk','A golden white chocolate with a smooth, silky texture and delicious caramel taste.', 'candy bar','Cadbury', 20, 1.50, 'imgpath','Caramilk'),
('Crunchie','A honeycomb-style candy bar', 'candy bar', 'Cadbury', 10, 2.99, 'imgpath','Crunchie'),
('Kinder Surprise','KINDER chocolate, with a surprise and toy in every single egg', 'chocolate', 'Kinder Chocolate', 7, 2.99, 'imgpath','Kinder Surprise'),
('Aero','Smooth, creamy milk chocolate with the incredible lightness of bubbles','candy bar','Nestle', 50, 2.99, 'imgpath','Aero'),
('Coffee Crisp','Coffee-flavoured soft candy','candy bar','Nestle', 45, 1.99, 'imgpath', 'Coffee Crisp'),
('Big Turk','Delicious chewiness of Turkish delight','candy bar','Nestle', 24, 3.50, 'imgpath','Big Turk'),
('KitKat','Chocolate-covered wafer bar','candy bar','Nestle', 14, 0.99, 'imgpath','KitKat'),
('Mr.Big','A layered vanilla wafer coated in caramel','candy bar','Cadbury', 34, 3.99, 'imgpath','Mr.Big'),
('Wunderbar','Canadain Chocolate bar, chocolate coating, filled with golden','candy bar','Cadbury', 45, 1.99, 'imgpath','Wunderbar'),
('Mars Bar','Chcolated with caramel and nougat','candy bar','Mars', 11, 1.50, 'imgpath','Mars Bar'),
('Crispy Crunch','Hard chocolate bar with a crispy peanut butter flake','candy bar','Cadbury', 7, 5.99, 'imgpath','Crispy Crunch'),
('Hubba Bubba','Outrageous Original Flavoured Bubble Gum','gum','Wrigley', 80, 6.99, 'imgpath','Hubba Bubba'),
('Juicy Fruit','Flavor of combination of banana and pineapple','gum','Wrigley', 53, 1.99, 'imgpath','Juicy Fruit'),
('Excel','Chewing gum and mints','gum','Wrigley', 25, 3.50, 'imgpath','Excel'),
('Dentyne','Gum to aid in oral hygiene','gum','Cadbury', 4, 1.50, 'imgpath','Dentyne'),
('Haribo Goldbears','Flavors ranging from pineapple to lemon', 'candy','Haribo', 8, 2.50, 'imgpath','Haribo Goldbears'),
('Skittles','Multicolored fruit-flavored button-shaped candies','jelly bean','Wrigley', 18, 3.99, 'imgpath','Skittles'),
('Starburst','A box-shaped, fruit-flavoured soft taffy candy','jelly bean','Wrigley', 27, 3.99, 'imgpath','Starburst'),
('Riesen','A confectionery of chocolate and chocolate-flavored caramel','candy','August Storck KG', 42, 1.99, 'imgpath','Riesen'),
('Jolly Rancher','Sweet hard candy','candy','Hershey', 66, 1.99, 'imgpath','Jolly Rancher'),
('TWIZZLERS','A chewy licorice type candy with a fruity flavor','candy','Hershey', 11, 4.99, 'imgpath','TWIZZLERS');



Insert INTO users (email, password, fname, lname, admin, checkout_count)
VALUES ('tony111@google.com', '101010', 'Tony', 'Stark', 0, 30),
('peter111@google.com', '010101', 'Peter', 'Parker', 0, 3);

Insert INTO address (street, city, province, zip, phone, email)
VALUES ('4700 Keele St','North York', 'ON', 'M3J 1P3','111-222-3333','tony111@google.com')
,('2525 St Clair Ave W','Toronto','ON','M6N 4Z5','111-222-3333','tony111@google.com')
,('158 Sterling Rd #100','Toronto','ON','M6R 2B7','333-222-1111','peter111@google.com');

Insert INTO cart (email, product_no, qnty)
values ('tony111@google.com',3,3),
('tony111@google.com',5,5),
('tony111@google.com',10,1),
('tony111@google.com',15,4),
('peter111@google.com',3,2),
('peter111@google.com',11,4);
