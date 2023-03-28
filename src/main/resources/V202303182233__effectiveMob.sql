create table users
(
    id bigserial not null primary key,
    name text not null,
    email text,
    password text not null,
    balance numeric default 0 not null,
    role text,
    locked boolean default false
);

create table companies
(
    id bigserial not null primary key,
    name text,
    description text,
    logo text,
    aprooved boolean default false,
    user_id int references users,
    locked boolean default false
);

create table notifications
(
    id bigserial not null primary key,
    header text,
    description text,
    created_at timestamp with time zone,
    user_id int references users
);

create table discounts
(
    id bigserial not null primary key,
    discount_size bigint not null,
    expires_at timestamp with time zone not null
);

create table products
(
    id bigserial not null primary key,
    name text unique not null,
    description text not null,
    price bigint not null,
    in_stock bigint not null,
    company_id int not null references companies,
    discount_id int references discounts,
    rating int default 5,
    meta jsonb default '{}',
    aproove boolean default false
);

create table purchases
(
    id bigserial not null primary key,
    user_id int references users,
    product_id int references products,
    price decimal not null,
    created_at timestamp with time zone default now()
);

create table reviews
(
    id bigserial not null primary key,
    header text not null,
    body text not null,
    product_id int references products
);