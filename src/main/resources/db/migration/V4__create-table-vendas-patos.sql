create table vendas_patos(

    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    venda_id BIGINT NOT NULL,
    pato_id BIGINT NOT NULL UNIQUE

);