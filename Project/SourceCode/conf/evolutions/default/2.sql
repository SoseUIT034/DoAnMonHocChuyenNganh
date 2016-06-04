# --- !Ups

INSERT INTO `customer` VALUES (1,'Canh','canhnvuit@gmail.com','123456789','Thu Duc - Ho Chi Minh','canhnguyenvan','1234'),(2,'Dan','tranngocdan2402@gmail.com','987456231','Ho Chi Minh','dantran','1234');
INSERT INTO `product` VALUES (1,'LG G2','LG','Pro phone 2013','3000000',3),(2,'Iphone 6','Apple','Pro phone 2015','12000000',6),(3,'Sumsung S7','Sumsung','Pro phone 2016','17000000',2);
INSERT INTO `cart` VALUES (1,1,'3000000'),(2,2,'17000000');
INSERT INTO `cart_item` VALUES (1,1,1),(2,2,3);