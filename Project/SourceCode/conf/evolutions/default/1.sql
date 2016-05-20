# --- !Ups

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(45) NOT NULL,
  `customer_email` varchar(45) NOT NULL,
  `customer_phone` LONG DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `price` LONG NOT NULL,
  `unitInStock` int(11) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `grand_total` LONG NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `fk_customer` (`customer_id`),
  CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cart_item` (
  `cart_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `fk_product` (`product_id`),
  KEY `fk_cart` (`cart_id`),
  CONSTRAINT `fk_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `customer` VALUES ('1','Canh','canh@gmail.com','0987263122','104 Nguyễn Văn Cừ, Phường Nguyễn Cư Trinh, Quận 1, Tp. HCM','canh','123');
INSERT INTO `customer` VALUES ('2','Dan','canh@gmail.com','0978266222','499A Phạm Văn Chiêu, P.13, Quận Gò Vấp, TP.Hồ Chí Minh','dan','321');
INSERT INTO `customer` VALUES ('3','admin','admin@gmail.com','0912381233','846 Đường Bình Giã, Phường Rạch Dừa. TP Vũng Tàu, Bà Rịa - Vũng Tàu','admin','admin');


INSERT INTO `product` VALUES ('1','iPhone 6S Plus','iPhone','Màn hình: Retina HD, 5.5 inches; HĐH: iOS 9; CPU: Chipset Apple A9; Camera: 12 MP, 1 SIM; Pin: Li-Po 2915 mAh;','21790000','10');
INSERT INTO `product` VALUES ('2','SAMSUNG GALAXY S7','Samsung','Màn hình : Quad HD, 5.1 inches; HĐH: Android 6.0 (Marshmallow); CPU: 8 nhân, Ram 4 GB; Camera: 12MP, 5MP; Pin: 3000 mAH','15990000','20');
INSERT INTO `product` VALUES ('3','HTC ONE A9','HTC','Màn hình: Full HD, 5.0 inches; HĐH: Android 6.0; CPU: Octa-core 1.5 GHz; Camera: 13 MP; Pin: 2150 mAh','8990000','30');
INSERT INTO `product` VALUES ('4','OPPO F1 PLUS','OPPO','Màn hình: 5.5 inches; HĐH: Android 5.1; CPU: Octa core 2.0 GHz; Camera: 13.0 MP, 16 MP; Pin: 2850mAh','9990000','40');


INSERT INTO `cart` VALUES ('1','1','37780000');
INSERT INTO `cart` VALUES ('2','2','8990000');
INSERT INTO `cart` VALUES ('3','3','0');

INSERT INTO `cart_item` VALUES ('1','1','1');
INSERT INTO `cart_item` VALUES ('2','1','2');
INSERT INTO `cart_item` VALUES ('3','2','3');


# --- !Downs

# DROP TABLE "address";
# DROP TABLE "customer";
# DROP TABLE "category";
# DROP TABLE "cart_item";
# DROP TABLE "cart";
# DROP TABLE "product";
