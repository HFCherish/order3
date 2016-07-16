CREATE TABLE orders (
  id VARCHAR(255) PRIMARY KEY,
  user_id VARCHAR(255),
  name VARCHAR(255),
  address VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  pay_state INTEGER DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE orderItems (
  order_id VARCHAR(255) UNIQUE,
  product_id VARCHAR (255),
  quantity INTEGER,
  amount DOUBLE,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

