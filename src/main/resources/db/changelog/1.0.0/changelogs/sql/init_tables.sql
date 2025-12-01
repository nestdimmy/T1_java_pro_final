CREATE TABLE IF NOT EXISTS limits
(
    id            uuid primary key,
    user_id       varchar(255),
    current       numeric
);

CREATE TABLE IF NOT EXISTS transactions
(
    id             uuid primary key,
    limit_id       uuid not null,
    transaction_id varchar(255),
    status         varchar(20),
    amount         numeric,
    created_at     timestamp
);
