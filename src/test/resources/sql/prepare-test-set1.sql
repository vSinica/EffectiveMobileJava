INSERT INTO public.users ( name, email, password, balance, role, locked) VALUES ( 'vadik', 'efewfw', 'ewgew', 50, 'USER', false);
INSERT INTO public.users ( name, email, password, balance, role, locked) VALUES ( 'dima', 'dgbsb', 'sdgbfsbg', 80, 'ADMIN', false);


INSERT INTO public.companies ( name, description, logo, aprooved, user_id, locked) VALUES ( 'companyName', 'companyDesc', 'logo', true, 2, false);


INSERT INTO public.discounts ( discount_size, expires_at) VALUES ( 22, '2023-02-01 17:00:00.000000 +00:00');
INSERT INTO public.discounts ( discount_size, expires_at) VALUES ( 22, '2023-02-01 17:00:00.000000 +00:00');
INSERT INTO public.discounts ( discount_size, expires_at) VALUES ( 22, '2023-02-01 17:00:00.000000 +00:00');

INSERT INTO public.products ( name, description, price, in_stock, company_id, discount_id, rating, meta, aproove) VALUES ( 'ProductName', 'ProductDesc', 11, 1, 1, null, 5, '{}', true);
INSERT INTO public.products ( name, description, price, in_stock, company_id, discount_id, rating, meta, aproove) VALUES ( 'ProductName2', 'ProductDesc', 11, 1, 1, null, 5, '{}', true);
INSERT INTO public.products ( name, description, price, in_stock, company_id, discount_id, rating, meta, aproove) VALUES ( 'ProductName3', 'ProductDesc', 11, 1, 1, null, 5, '{}', true);
INSERT INTO public.products ( name, description, price, in_stock, company_id, discount_id, rating, meta, aproove) VALUES ( 'ProductName6', 'ProductDesc', 11, 1, 1, null, 5, '{}', false);
INSERT INTO public.products ( name, description, price, in_stock, company_id, discount_id, rating, meta, aproove) VALUES ( 'ProductName7', 'ProductDesc', 11, 1, 1, null, 5, '{}', false);
INSERT INTO public.products ( name, description, price, in_stock, company_id, discount_id, rating, meta, aproove) VALUES ( 'ProductName8', 'ProductDesc', 11, 1, 1, null, 5, '{}', false);

INSERT INTO public.purchases ( user_id, product_id, price, created_at) VALUES ( 1, 3, 11, null);
INSERT INTO public.purchases ( user_id, product_id, price, created_at) VALUES ( 1, 3, 11, '2023-03-23 10:19:18.806818 +00:00');
INSERT INTO public.purchases ( user_id, product_id, price, created_at) VALUES ( 1, 3, 11, '2023-06-23 10:19:18.806000 +00:00');

INSERT INTO public.reviews ( header, body, product_id) VALUES ( '1', '3', 3);


