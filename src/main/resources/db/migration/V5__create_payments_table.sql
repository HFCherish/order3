create table payments (
  order_id VARCHAR(255) not null,
  type VARCHAR(255) not null,
  amount DOUBLE NOT NULL,
  pay_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);